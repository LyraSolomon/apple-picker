#ifndef _MANUAL_CONTROLS_H_
#define _MANUAL_CONTROLS_H_

#include "RobotParts.h"
#include "WPILib.h"

class ManualControls {
private:
  Joystick *control_;
public:
  ManualControls();
  void Run();
};

#endif
