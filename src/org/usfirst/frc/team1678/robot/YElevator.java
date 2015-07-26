package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class YElevator {
	private Solenoid brake;
	DigitalInput hallsensor;
	PIDLoop up, down;
	PIDLoop loop=new PIDLoop(0, 0, 0, 0);
	VictorSP motor;
	Encoder encoder;
	float encoderPosUp=0, encoderPosDown=0;
	float encoderPosCenter=0;
	int clicksPerFoot=950;
	int target=0;
	public YElevator()
	{
		brake=new Solenoid(6);
		up=new PIDLoop(-0.002f, -0.000069f, 0f, 1);
		down=new PIDLoop(-.0026f, -.00008f, 0f, 1);
		hallsensor=new DigitalInput(0);
		motor=new VictorSP(5);
		encoder=new Encoder(14, 15);
		brake.set(false);
	}
	void runCalibration()
	{
		while(hallsensor.get()) motor.set(.3);//move elevator down into sensor range
		encoderPosDown=encoder.get();
		while(!hallsensor.get()) motor.set(.3);//move elevator past sensor range
		while(hallsensor.get()) motor.set(-.3);//move elevator back into sensor range
		encoderPosUp=encoder.get();
		motor.set(0);
		encoderPosCenter=(encoderPosUp+encoderPosDown)/2;
	}
	void periodic()
	{
		SmartDashboard.putNumber("error", encoder.get()-encoderPosCenter-target);
		float val=loop.CalibrateLoop(encoder.get()-encoderPosCenter-target);
		motor.set(val);
		if(!loop.IsTerminated());
		else ;//motor.set(0);
		SmartDashboard.putNumber("elevator height", encoder.get()-encoderPosCenter);
	}
	void setTargetHeight(float feet)
	{
		target=(int) (-950*feet);
		if(target>encoder.get()-encoderPosCenter) loop=down;
		else loop=up;
		loop.StartLoop();
	}
}
