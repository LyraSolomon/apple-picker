/*
 * ManualControls.h
 *
 *  Created on: Nov 7, 2015
 *      Author: Citrus CAD
 */

#ifndef SRC_MANUALCONTROLS_H_
#define SRC_MANUALCONTROLS_H_

class ManualControls {
	Joystick *control;
	void run()
	{
		RobotParts::yAxisMotor1->Set(control->GetRawAxis('Y'));
		RobotParts::yAxisMotor2->Set(control->GetRawAxis('Y'));

		RobotParts::xAxisMotor->Set(control->GetRawAxis('X'));

		if(control->GetRawButton(1))
		{
//			RobotParts::grabberMotor1->Set(.3);
//			RobotParts::grabberMotor2->Set(.3);
//			RobotParts::grabberMotor3->Set(.3);
		}
		else
		{
//			RobotParts::grabberMotor1->Set(0);
//			RobotParts::grabberMotor2->Set(0);
//			RobotParts::grabberMotor3->Set(0);
		}

		if(control->GetRawButton(3))
		{
			RobotParts::extendMotor->Set(.3);
		}
		else if(control->GetRawButton(2))
		{
			RobotParts::extendMotor->Set(-.3);
		}
		else
		{
			RobotParts::extendMotor->Set(0);
		}
	}
};

#endif /* SRC_MANUALCONTROLS_H_ */
