#ifndef _ROBOT_PARTS_H_
#define _ROBOT_PARTS_H_

#include "WPILib.h"

class RobotParts {
public:
  // Sensors
  static Encoder* YAxisEnc_;
  static Encoder* XAxisEnc_;

  static DigitalInput* XHallEffect_;
  static DigitalInput* YHallEffect_;
  static DigitalInput* ExtendHallEffect_;

  static AnalogInput* GrabberProxSensor_;

  // Acutators
  static VictorSP *YAxisMotor1_, *YAxisMotor2_;
  static VictorSP *XAxisMotor_;
  static VictorSP *ExtendMotor_;
  static VictorSP *GrabberMotor1_, *GrabberMotor2_, *GrabberMotor3_;
	
  static void Initialize();
};


#endif
