#include "WPILib.h"
#include "RobotParts.h"
#include "Elevator.h"
#include "XElev.h"
#include "YElev.h"
#include "Grabber.h"
#include "ManualControls.h"

class Robot: public IterativeRobot {
private:
	LiveWindow *lw;

	void RobotInit() {
		lw = LiveWindow::GetInstance();
		y = new YElev(RobotParts::yHallEffect, RobotParts::yAxisMotor1,
				RobotParts::yAxisMotor2, RobotParts::yAxisEnc);
		x = new XElev(RobotParts::xHallEffect, RobotParts::xAxisMotor,
				RobotParts::xAxisEnc);
		z = new Grabber(RobotParts::extendMotor, RobotParts::grabberMotor1,
				RobotParts::grabberMotor2, RobotParts::grabberMotor3,
				RobotParts::grabberProxSensor, RobotParts::extendHallEffect);
//		client = new TestClient();
	}

	ManualControls* controls;
	Elevator* y;
	Elevator* x;
	Grabber* z;
//	TestClient* client;

	void robotInit() {

	}

	void autonomousInit() {
		y->runCalibration();
		x->runCalibration();
	}

	void autonomousPeriodic() {
		//Let's ignore networking for now
//		y->moveToApple(client->data[1]);
//		x->moveToApple(client->data[0]);
		y->moveToApple(0);
		x->moveToApple(0);
		z->update();
		if (x->ready() && y->ready())
			z->grab();
	}

	void teleopInit() {
	}

	void teleopPeriodic() {
		controls->run();
	}

	void testInit() {
	}

	void testPeriodic() {
	}

	void disabledInit() {
	}

	void disabledPeriodic() {
		SmartDashboard::PutNumber("elev height", y->getHeight());
		SmartDashboard::PutNumber("elev x", x->getHeight());
	}

	void TestPeriodic() {
		lw->Run();
	}
};

START_ROBOT_CLASS(Robot);
