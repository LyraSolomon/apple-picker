
package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
    YElevator y;
    TestClient client;
    
    public void robotInit() {
    	y=new YElevator();
		SmartDashboard.putBoolean("is terminated2", false);
		client=new TestClient();
    }

    public void autonomousInit(){}
    public void autonomousPeriodic() {}
    
    public void teleopInit() {
    	y.runCalibration();
//    	y.setTargetHeight(5);
    }
    public void teleopPeriodic() {
//    	y.periodic();
    	y.moveToApple(client.data);
    	SmartDashboard.putNumber("elevator height", y.getHeight());
    }
    
    public void testInit(){}
    public void testPeriodic() {}
    
    public void disabledInit(){}
    public void disabledPeriodic(){
    }
}
