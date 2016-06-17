#include "RobotParts.h"
#include "WPILib.h"

Encoder *RobotParts::YAxisEnc_;
Encoder *RobotParts::XAxisEnc_;

DigitalInput *RobotParts::XHallEffect_;
DigitalInput *RobotParts::YHallEffect_;
DigitalInput *RobotParts::ExtendHallEffect_;

AnalogInput *RobotParts::GrabberProxSensor_;

VictorSP *RobotParts::YAxisMotor1_;
VictorSP *RobotParts::YAxisMotor2_;
VictorSP *RobotParts::XAxisMotor_;
VictorSP *RobotParts::ExtendMotor_;
VictorSP *RobotParts::GrabberMotor1_;
VictorSP *RobotParts::GrabberMotor2_;
VictorSP *RobotParts::GrabberMotor3_;

void RobotParts::Initialize() {
  RobotParts::YAxisEnc_ = new Encoder(1, 2);
  RobotParts::XAxisEnc_ = new Encoder(8, 9);

  RobotParts::XHallEffect_ = new DigitalInput(6);
  RobotParts::YHallEffect_ = new DigitalInput(7);
  RobotParts::ExtendHallEffect_ = nullptr;

  RobotParts::GrabberProxSensor_ = new AnalogInput(0);

  RobotParts::YAxisMotor1_ = new VictorSP(6);
  RobotParts::YAxisMotor2_ = new VictorSP(7);
  RobotParts::XAxisMotor_ = new VictorSP(1);
  RobotParts::ExtendMotor_ = new VictorSP(0);
  RobotParts::GrabberMotor1_ = new VictorSP(3);
  RobotParts::GrabberMotor2_ = new VictorSP(5);
  RobotParts::GrabberMotor3_ = new VictorSP(8);
}
