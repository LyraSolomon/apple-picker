/*
 * XElev.h
 *
 *  Created on: Nov 7, 2015
 *      Author: Citrus CAD
 */

#ifndef SRC_XELEV_H_
#define SRC_XELEV_H_
#include "Elevator.h"

class XElev : public Elevator {
public:
	XElev(DigitalInput *hallEffect, VictorSP *elevMotor, Encoder *elevEncoder)
		: Elevator(hallEffect, elevMotor, elevEncoder)
	{
		//FIXME get correct values
		clicksPerFoot=0;
		safetyLow=0;
		safetyHigh=0;
		clicksTolerance=1000;
		motorReverse=true;
	}
};



#endif /* SRC_XELEV_H_ */
