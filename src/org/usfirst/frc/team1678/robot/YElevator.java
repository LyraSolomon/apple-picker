package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class YElevator {
	protected DigitalInput hallsensor;
	protected PIDLoop up, down;
	protected PIDLoop loop=new PIDLoop(0, 0, 0, 0);
	protected VictorSP motor;
	protected Encoder encoder;
	protected float encoderPosUp=0, encoderPosDown=0;
	protected float encoderPosCenter=0;
	final int clicksPerFoot=-950;
	final float clicksTolerance=20;
	final int safetyLow=1200, safetyHigh=-1400;
	protected int target=0;
	/** Initialize components */
	public YElevator()
	{
		up=new PIDLoop(-0.002f, -0.000069f, 0f, 20, 1);
		down=new PIDLoop(-.0026f, -.00008f, 0f, 20, 1);
		loop=new PIDLoop(-.0006f, -.000023f, -.00001f, 20, .3f);//safety for lag on vision
		hallsensor=new DigitalInput(0);
		motor=new VictorSP(5);
		encoder=new Encoder(14, 15);
		loop.StartLoop();
	}
	/** Calibrate encoder based on hall effect sensor */
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
	/** Run encoder-based PID loop*/
	void periodic()
	{
		SmartDashboard.putNumber("error", getHeight()-target);
		float output=loop.CalibrateLoop(getHeight()-target);
		if(output>0 && getHeight()>safetyLow || 
				output<0 && getHeight()<safetyHigh)
			motor.set(0);
		else motor.set(output);
	}
	/** Get height above hall effect sensor, in clicks */
	float getHeight(){return encoder.get()-encoderPosCenter;}
	
	/** Set target for encoder-based PID loop
	 * 
	 * @param feet Height above hall effect sensor, in feet
	 */
	void setTargetHeight(float feet)
	{
		target=(int) (clicksPerFoot*feet);
		if(target>getHeight()) loop=down;
		else loop=up;
//		loop=new PIDLoop(-.0006f, -.000023f, 0, 20, .3f);//safety for lag on vision
		loop.StartLoop();
	}
	/** Run vision-based PID */
	void moveToApple(double []location)
	{
//		float encError=(float) (clicksPerFoot*location[1]);
		float encError=(float) (clicksPerFoot*-location[0]);//because the camera is tilted
		SmartDashboard.putNumber("error", encError);
		float val=loop.CalibrateLoop(encError);
		SmartDashboard.putNumber("PID output", val);
		if(val>0 && getHeight()>safetyLow || 
				val<0 && getHeight()<safetyHigh)
			motor.set(0);
		else motor.set(val);
		SmartDashboard.putNumber("elevator height", encoder.get()-encoderPosCenter);
	}
}
