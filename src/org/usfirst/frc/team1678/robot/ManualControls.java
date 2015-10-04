package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.*;s

public class ManualControls {
	static Joystick control;
	void run()
	{
		RobotParts.yAxisMotor1.set(control.getRawAxis('Y'));
		RobotParts.yAxisMotor2.set(control.getRawAxis('Y'));
		
		RobotParts.xAxisMotor.set(control.getRawAxis('X'));
		
		if(control.getRawButton(1))
		{//pemis
			RobotParts.grabberMotor1.set(.3);
			RobotParts.grabberMotor2.set(.3);
			RobotParts.grabberMotor3.set(.3);
		}
		else
		{
			RobotParts.grabberMotor1.set(0);
			RobotParts.grabberMotor2.set(0);
			RobotParts.grabberMotor3.set(0);
		}
		
		if(control.getRawButton(3))
		{
			RobotParts.extendMotor.set(.3);
		}
		else if(control.getRawButton(2))
		{
			RobotParts.extendMotor.set(-.3);
		}
		else
		{
			RobotParts.extendMotor.set(0);
		}
	}
}
