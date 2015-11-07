/*
 * RobotParts.h
 *
 *  Created on: Nov 7, 2015
 *      Author: Citrus CAD
 */

#ifndef SRC_ROBOTPARTS_H_
#define SRC_ROBOTPARTS_H_

class RobotParts {
static Encoder* yAxisEnc;
	static Encoder* xAxisEnc;

	static DigitalInput* xHallEffect;
	static DigitalInput* yHallEffect;
	static DigitalInput* extendHallEffect;

	static AnalogInput* grabberProxSensor;

	// Acutators
	static VictorSP *yAxisMotor1, *yAxisMotor2;
	static VictorSP *xAxisMotor;
	static VictorSP *extendMotor;
	static VictorSP *grabberMotor1, *grabberMotor2, *grabberMotor3;
};

VictorSP *RobotParts::yAxisMotor1 = new VictorSP(6);
VictorSP *RobotParts::yAxisMotor2 = new VictorSP(7);
VictorSP *RobotParts::xAxisMotor = new VictorSP(1);
VictorSP *RobotParts::extendMotor = new VictorSP(0);
VictorSP *RobotParts::grabberMotor1 = new VictorSP(3);
VictorSP *RobotParts::grabberMotor2 = new VictorSP(5);
VictorSP *RobotParts::grabberMotor3 = new VictorSP(8);

#endif /* SRC_ROBOTPARTS_H_ */
