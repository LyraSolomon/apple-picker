
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
    	y.setTargetHeight(1);
    }
    public void teleopPeriodic() {
    	y.moveToApple(client.data);
    	
		if(client.data[2]==0) SmartDashboard.putString("apple status", "No apple in view.");
		if(client.data[2]==-1) SmartDashboard.putString("apple status", "Error occured.");
		if(client.data[2]==1) SmartDashboard.putString("apple status",
				"X: "+client.data[0]+" Y: "+client.data[1]);
    }
    
    public void testInit(){}
    public void testPeriodic() {}
    
    public void disabledInit(){}
    public void disabledPeriodic(){
    }
}
