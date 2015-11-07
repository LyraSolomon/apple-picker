/*
 * YElev.h
 *
 *  Created on: Nov 7, 2015
 *      Author: Citrus CAD
 */

#ifndef SRC_YELEV_H_
#define SRC_YELEV_H_
#include "Elevator.h"

class YElev : public Elevator {
	VictorSP *extraMotor;
public:
	YElev(DigitalInput *hallEffect,
			VictorSP *elevMotor1, VictorSP *elevMotor2, Encoder *elevEncoder)
		: Elevator(hallEffect, elevMotor1, elevEncoder)
	{
		extraMotor=elevMotor2;
		//FIXME get correct values
		clicksPerFoot=0;
		safetyLow=0;
		safetyHigh=0;
		clicksTolerance=1000;
		motorReverse=true;
	}
	void runCalibration()
	{
		while(hallsensor->Get()) {
			motor->Set(motorReverse?.3:-.3);
			extraMotor->Set(motorReverse?.3:-.3);
		}//go down
		encoderPosDown=encoder->Get();
		while(!hallsensor->Get()) {
			motor->Set(motorReverse?.3:-.3);
			extraMotor->Set(motorReverse?.3:-.3);
		}
		while(hallsensor->Get()) {
			motor->Set(motorReverse?-.3:.3);
			extraMotor->Set(motorReverse?-.3:.3);
		}
		encoderPosUp=encoder->Get();
		motor->Set(0);extraMotor->Set(0);
		encoderPosCenter=(encoderPosUp+encoderPosDown)/2;
	}
	void runWithSafety(float val)
	{
		if(((motorReverse?-val:val)<0 &&
				getHeight()*clicksPerFoot>safetyLow*ABS(clicksPerFoot)) ||
				((motorReverse?-val:val)>0 &&
				getHeight()*clicksPerFoot<safetyHigh*ABS(clicksPerFoot)))
		{
			motor->Set(0);
			extraMotor->Set(0);
		}
		else
		{
			motor->Set(val);
			extraMotor->Set(val);
		}
	}
};

#endif /* SRC_YELEV_H_ */
