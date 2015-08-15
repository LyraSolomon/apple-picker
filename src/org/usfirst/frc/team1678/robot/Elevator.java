package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator {
	protected DigitalInput hallsensor;
	// protected PIDLoop up, down;
	protected PIDLoop loop = new PIDLoop(0, 0, 0, 0);
	protected VictorSP motor;
	protected Encoder encoder;
	protected float encoderPosUp = 0, encoderPosDown = 0;
	protected float encoderPosCenter = 0;
	protected int clicksPerFoot = -950;
	protected float clicksTolerance = 20;
	protected int safetyLow = 1200, safetyHigh = -1400;
	protected int target = 0;
	protected boolean motorReverse = true;

	/** Initialize components */
	public Elevator(DigitalInput hallEffect, VictorSP elevMotor, Encoder elevEncoder) {
		// up=new PIDLoop(-0.002f, -0.000069f, 0f, 20, 1);
		// down=new PIDLoop(-.0026f, -.00008f, 0f, 20, 1);
		loop = new PIDLoop(-.0006f, -.000023f, -.00001f, 20, .3f);// safety for
																	// lag on
																	// vision
		hallsensor = hallEffect;
		motor = elevMotor;
		encoder = elevEncoder;
		loop.StartLoop();
	}

	/**
	 * Calibrate encoder based on hall effect sensor. Elevator should start in
	 * the positive direction from the hall sensor. TODO Should this function be
	 * made non - blocking?
	 */
	void runCalibration() {
		// Move the elevator into sensor range and capture the position of the
		// edge
		while (hallsensor.get())
			motor.set(motorReverse ? .3 : -.3);
		encoderPosDown = encoder.get();

		// Move elevator past sensor range
		while (!hallsensor.get())
			motor.set(motorReverse ? .3 : -.3);

		// Move elevator back into sensor range and capture the position of the
		// other edge
		while (hallsensor.get())
			motor.set(motorReverse ? -.3 : .3);
		encoderPosUp = encoder.get();

		// Stop the elevator
		motor.set(0);

		// Average the two edges together to calibrate the encoder
		encoderPosCenter = (encoderPosUp + encoderPosDown) / 2;
	}

	/** Run encoder-based PID loop */
	void periodic() {
		SmartDashboard.putNumber("error", getHeight() - target);
		
		// Calibrate the loop based on error
		float output = loop.CalibrateLoop(getHeight() - target);
		
		// Run the elevator with the values given.
		runWithSafety(output);
	}

	/** Get height above hall effect sensor, in clicks */
	float getHeight() {
		return encoder.get() - encoderPosCenter;
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
		loop.StartLoop();
	}

	/** Run vision-based PID */
	void moveToApple(double location) {
		float encError = (float) (clicksPerFoot * location);
		SmartDashboard.putNumber("error", encError);
		float val = loop.CalibrateLoop(encError);
		SmartDashboard.putNumber("PID output", val);
		runWithSafety(val);
		SmartDashboard.putNumber("elevator height", encoder.get() - encoderPosCenter);
	}

	/**
	 * @return Whether or not the loop is done
	 */
	boolean ready() {
		return loop.IsTerminated();
	}

	/**
	 * Do not run the motor farther if it is already past a cutoff point
	 * @param val the motor value to send to the motor
	 */
	void runWithSafety(float val) {
		if ((motorReverse ? -val : val) < 0 && getHeight() * clicksPerFoot > safetyLow * Math.abs(clicksPerFoot)
				|| (motorReverse ? -val : val) > 0
				&& getHeight() * clicksPerFoot < safetyHigh * Math.abs(clicksPerFoot))
			motor.set(0);
		else
			motor.set(val);
	}
}