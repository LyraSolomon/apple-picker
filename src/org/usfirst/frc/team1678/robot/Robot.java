package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	Joystick controller = new Joystick(0);
	Elevator y;
	Elevator x;
	Grabber z;
	TestClient client;

	public void robotInit() {
		y = new YElev(RobotParts.yHallEffectL, RobotParts.yHallEffectR, RobotParts.yAxisLSC, RobotParts.yAxisRSC,
				RobotParts.yAxisLEnc, RobotParts.yAxisREnc);
		x = new XElev(RobotParts.xHallEffect, RobotParts.xAxisSC, RobotParts.xAxisEnc);
		z = new Grabber(RobotParts.extendSC, RobotParts.grabberSC, RobotParts.grabberProxSensor,
				RobotParts.extendHallEffect);
		client = new TestClient();
	}

	public void autonomousInit() {
		y.runCalibration();
		x.runCalibration();
	}

	public void autonomousPeriodic() {
		// y.moveToApple(client.data[1]);
		// x.moveToApple(client.data[0]);
		// z.update();
		// if(x.ready()&&y.ready()) z.grab();
	}

	public void teleopInit() {
	}

	public void teleopPeriodic() {
		y.runWithSafety((float) controller.getY());
		x.runWithSafety((float) controller.getX());
		if (controller.getRawButton(4))
			z.setExtender(.3f);
		if (controller.getRawButton(5))
			z.setExtender(-.3f);
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
