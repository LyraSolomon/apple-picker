/*
 * Elevator.h
 *
 *  Created on: Nov 7, 2015
 *      Author: Citrus CAD
 */
#include "WPILib.h"
#define ABS(a) (a>0?a:-a)

#ifndef SRC_ELEVATOR_H_
#define SRC_ELEVATOR_H_
#include "PIDLoop.h"

class Elevator {
protected:
	DigitalInput *hallsensor;
	// PIDLoop up, down;
	PIDLoop *loop = new PIDLoop(0, 0, 0, 0);
	VictorSP *motor;
	Encoder *encoder;
	float encoderPosUp = 0, encoderPosDown = 0;
	float encoderPosCenter = 0;
	int clicksPerFoot = -950;
	float clicksTolerance = 20;
	int safetyLow = 1200, safetyHigh = -1400;
	int target = 0;
	bool motorReverse = true;
public:
	/** Initialize components */
	Elevator(DigitalInput *hallEffect, VictorSP *elevMotor, Encoder *elevEncoder) {
		// up=new PIDLoop(-0.002f, -0.000069f, 0f, 20, 1);
		// down=new PIDLoop(-.0026f, -.00008f, 0f, 20, 1);
		loop = new PIDLoop(-.0006f, -.000023f, -.00001f, 20, .3f);// safety for
																	// lag on
																	// vision
		hallsensor = hallEffect;
		motor = elevMotor;
		encoder = elevEncoder;
		loop->StartLoop();
	}
	virtual ~Elevator(){}
	/**
	 * Calibrate encoder based on hall effect sensor. Elevator should start in
	 * the positive direction from the hall sensor. TODO Should this function be
	 * made non - blocking?
	 */
	virtual void runCalibration() {
		// Move the elevator into sensor range and capture the position of the
		// edge
		while (hallsensor->Get())
			motor->Set(motorReverse ? .3 : -.3);
		encoderPosDown = encoder->Get();

		// Move elevator past sensor range
		while (!hallsensor->Get())
			motor->Set(motorReverse ? .3 : -.3);

		// Move elevator back into sensor range and capture the position of the
		// other edge
		while (hallsensor->Get())
			motor->Set(motorReverse ? -.3 : .3);
		encoderPosUp = encoder->Get();

		// Stop the elevator
		motor->Set(0);

		// Average the two edges together to calibrate the encoder
		encoderPosCenter = (encoderPosUp + encoderPosDown) / 2;
	}

	/** Run encoder-based PID loop */
	void periodic() {
		SmartDashboard::PutNumber("error", getHeight() - target);

		// Calibrate the loop based on error
		float output = loop->CalibrateLoop(getHeight() - target);

		// Run the elevator with the values given.
		runWithSafety(output);
	}

	/** Get height above hall effect sensor, in clicks */
	float getHeight() {
		return encoder->Get() - encoderPosCenter;
	}

	/**
	 * Set target for encoder-based PID loop
	 *
	 * @param feet
	 *            Height above hall effect sensor, in feet
	 */
	void setTargetHeight(float feet) {
		// target=(int) (clicksPerFoot*feet);
		// if(target>getHeight()) loop=down;
		// else loop=up;
		// loop=new PIDLoop(-.0006f, -.000023f, 0, 20, .3f);//safety for lag on
		// vision
		loop->StartLoop();
	}

	/** Run vision-based PID */
	void moveToApple(double location) {
		float encError = (float) (clicksPerFoot * location);
		SmartDashboard::PutNumber("error", encError);
		float val = loop->CalibrateLoop(encError);
		SmartDashboard::PutNumber("PID output", val);
		runWithSafety(val);
		SmartDashboard::PutNumber("elevator height", encoder->Get() - encoderPosCenter);
	}

	/**
	 * @return Whether or not the loop is done
	 */
	bool ready() {
		return loop->IsTerminated();
	}

	/**
	 * Do not run the motor farther if it is already past a cutoff point
	 * @param val the motor value to send to the motor
	 */
	virtual void runWithSafety(float val) {
		if (((motorReverse ? -val : val) < 0 && getHeight() * clicksPerFoot > safetyLow * ABS(clicksPerFoot))
				|| ((motorReverse ? -val : val) > 0
				&& getHeight() * clicksPerFoot < safetyHigh * ABS(clicksPerFoot)))
			motor->Set(0);
		else
			motor->Set(val);
	}
};

#endif /* SRC_ELEVATOR_H_ */
