
package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
    YElevator y;
    
    public void robotInit() {
    	y=new YElevator();
		SmartDashboard.putBoolean("is terminated2", false);
    }

    public void autonomousInit(){}
    public void autonomousPeriodic() {}
    
    public void teleopInit() {
    	y.runCalibration();
    	y.setTargetHeight(1);
    }
    public void teleopPeriodic() {
    	y.periodic();
    }
    
    public void testInit(){}
    public void testPeriodic() {}
    
    public void disabledInit(){}
    public void disabledPeriodic(){
    }
}
