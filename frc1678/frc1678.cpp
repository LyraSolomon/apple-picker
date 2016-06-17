#include "WPILib.h"
#include "RobotParts.h"
#include "ManualControls.h"
#include <iostream>

class Robot: public IterativeRobot {
private:
  LiveWindow *lw_;
  ManualControls controls_;

  void RobotInit() {
    lw_ = LiveWindow::GetInstance();
    RobotParts::Initialize();
  }

  void AutonomousInit() {
  }

  void AutonomousPeriodic() {
  }

  void TeleopInit() {
  }

  void TeleopPeriodic() {
    controls_.Run();
  }

  void DisabledInit() {
  }

  void DisabledPeriodic() {
  }

  void TestPeriodic() {
    lw_->Run();
  }
};

START_ROBOT_CLASS(Robot);
