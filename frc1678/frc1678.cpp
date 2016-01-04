#include "WPILib.h"
#include "RobotParts.h"
//#include "Elevator.h"
//#include "XElev.h"
//#include "YElev.h"
//#include "Grabber.h"
#include "ManualControls.h"
#include <iostream>

class Robot: public IterativeRobot {
private:
	LiveWindow *lw;

	void RobotInit() {
		lw = LiveWindow::GetInstance();
		//y = new YElev(RobotParts::yHallEffect, RobotParts::yAxisMotor1,
		//		RobotParts::yAxisMotor2, RobotParts::yAxisEnc);
		//x = new XElev(RobotParts::xHallEffect, RobotParts::xAxisMotor,
		//		RobotParts::xAxisEnc);
		//z = new Grabber(RobotParts::extendMotor, RobotParts::grabberMotor1,
		//		RobotParts::grabberMotor2, RobotParts::grabberMotor3,
		//		RobotParts::grabberProxSensor, RobotParts::extendHallEffect);
//		//client = new TestClient();
		initialize();
	}

	ManualControls controls;
	//Elevator* y;
	//Elevator* x;
	//Grabber* z;
//	//TestClient* client;

	void AutonomousInit() {
		//y->runCalibration();
		//x->runCalibration();
	}

	void AutonomousPeriodic() {
		//Let's ignore networking for now
//		y->moveToApple(client->data[1]);
//		x->moveToApple(client->data[0]);
		//y->moveToApple(0);
		//x->moveToApple(0);
		//z->update();
		//if (x->ready() && y->ready())
		//	z->grab();
	}

	void TeleopInit() {
		std::cout<<"init"<<std::endl;
	}

	void TeleopPeriodic() {
		//yHallEffect..
		std::cout<<RobotParts::yAxisEnc->Get()<<std::endl;
		controls.run();
	}

	void DisabledInit() {
	}

	void DisabledPeriodic() {
		//SmartDashboard::PutNumber("elev height", y->getHeight());
		//SmartDashboard::PutNumber("elev x", x->getHeight());
	}

	void TestPeriodic() {
		lw->Run();
	}
};

START_ROBOT_CLASS(Robot);
