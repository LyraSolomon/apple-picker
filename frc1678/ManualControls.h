/*
 * ManualControls.h
 *
 *  Created on: Nov 7, 2015
 *      Author: Citrus CAD
 */
 
#ifndef SRC_MANUALCONTROLS_H_
#define SRC_MANUALCONTROLS_H_
#include <iostream>
class ManualControls {
public:
	Joystick *control=new Joystick(1);
	void run()
	{
		std::cout<<control->GetX()<<std::endl;
		RobotParts::yAxisMotor1->Set(-control->GetY());
		RobotParts::yAxisMotor2->Set(control->GetY());

		RobotParts::xAxisMotor->Set(control->GetX());

		if(control->GetRawButton(1))
		{
			RobotParts::grabberMotor1->Set(1);
			RobotParts::grabberMotor2->Set(-1);
			RobotParts::grabberMotor3->Set(1);
		}
		else
		{
			RobotParts::grabberMotor1->Set(0);
			RobotParts::grabberMotor2->Set(0);
			RobotParts::grabberMotor3->Set(0);
		}

		if(control->GetRawButton(3))
		{
			RobotParts::extendMotor->Set(1);
		}
		else if(control->GetRawButton(2))
		{
			RobotParts::extendMotor->Set(-1);
		}
		else
		{
			RobotParts::extendMotor->Set(0);
		}
	}
};

#endif /* SRC_MANUALCONTROLS_H_ */
