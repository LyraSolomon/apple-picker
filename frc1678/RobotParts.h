/*
 * RobotParts.h
 *
 *  Created on: Nov 7, 2015
 *      Author: Citrus CAD
 */

#ifndef SRC_ROBOTPARTS_H_
#define SRC_ROBOTPARTS_H_

class RobotParts {
public:
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

Encoder *RobotParts::yAxisEnc;
Encoder *RobotParts::xAxisEnc;

DigitalInput *RobotParts::xHallEffect;
DigitalInput *RobotParts::yHallEffect;
DigitalInput *RobotParts::extendHallEffect;

AnalogInput *RobotParts::grabberProxSensor;

VictorSP *RobotParts::yAxisMotor1;
VictorSP *RobotParts::yAxisMotor2;
VictorSP *RobotParts::xAxisMotor;
VictorSP *RobotParts::extendMotor;
VictorSP *RobotParts::grabberMotor1;
VictorSP *RobotParts::grabberMotor2;
VictorSP *RobotParts::grabberMotor3;

void initialize() {
	RobotParts::yAxisEnc = nullptr;
	RobotParts::xAxisEnc = nullptr;

	RobotParts::xHallEffect = nullptr;
	RobotParts::yHallEffect = nullptr;
	RobotParts::extendHallEffect = nullptr;

	RobotParts::grabberProxSensor = nullptr;

	RobotParts::yAxisMotor1 = new VictorSP(6);
	RobotParts::yAxisMotor2 = new VictorSP(7);
	RobotParts::xAxisMotor = new VictorSP(1);
	RobotParts::extendMotor = new VictorSP(0);
	RobotParts::grabberMotor1 = new VictorSP(3);
	RobotParts::grabberMotor2 = new VictorSP(5);
	RobotParts::grabberMotor3 = new VictorSP(8);
}
#endif /* SRC_ROBOTPARTS_H_ */
