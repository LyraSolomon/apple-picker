package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	ManualControls controls;
	Elevator y;
	Elevator x;
	Grabber z;
	TestClient client;

	public void robotInit() {
		y = new YElev(RobotParts.yHallEffect, RobotParts.yAxisMotor1, RobotParts.yAxisMotor2,
				RobotParts.yAxisEnc);
		x = new XElev(RobotParts.xHallEffect, RobotParts.xAxisMotor, RobotParts.xAxisEnc);
		z = new Grabber(RobotParts.extendMotor, 
				RobotParts.grabberMotor1, RobotParts.grabberMotor2, RobotParts.grabberMotor3,
				RobotParts.grabberProxSensor, RobotParts.extendHallEffect);
		client = new TestClient();
	}

	public void autonomousInit() {
		y.runCalibration();
		x.runCalibration();
	}

	public void autonomousPeriodic() {
		 y.moveToApple(client.data[1]);
		 x.moveToApple(client.data[0]);
		 z.update();
		 if(x.ready()&&y.ready()) z.grab();
	}

	public void teleopInit() {
	}

	public void teleopPeriodic() {
		controls.run();
	}

	public void testInit() {
	}

	public void testPeriodic() {
	}

	public void disabledInit() {
	}

	public void disabledPeriodic() {
		SmartDashboard.putNumber("elev height", y.getHeight());
		SmartDashboard.putNumber("elev x", x.getHeight());
	}
}
