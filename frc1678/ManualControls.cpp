#include "ManualControls.h"
#include "WPILib.h"

ManualControls::ManualControls() {
  control_ = new Joystick(1);
}

void ManualControls::Run() {
  // One of the Y motors is backwards
  RobotParts::YAxisMotor1_->Set(-control_->GetY());
  RobotParts::YAxisMotor2_->Set(control_->GetY());

  RobotParts::XAxisMotor_->Set(control_->GetX());

  if(control_->GetRawButton(1)) {
    // One of the grabber motors is backwards
    RobotParts::GrabberMotor1_->Set(1);
    RobotParts::GrabberMotor2_->Set(-1);
    RobotParts::GrabberMotor3_->Set(1);
  } else {
    RobotParts::GrabberMotor1_->Set(0);
    RobotParts::GrabberMotor2_->Set(0);
    RobotParts::GrabberMotor3_->Set(0);
  }

  if(control_->GetRawButton(3)) {
    RobotParts::ExtendMotor_->Set(1);
  }
  else if(control_->GetRawButton(2)) {
    RobotParts::ExtendMotor_->Set(-1);
  } else {
    RobotParts::ExtendMotor_->Set(0);
  }
}
