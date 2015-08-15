package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.*;

public class YElev extends Elevator {
	VictorSP extraMotor;
	Encoder extraEncoder;
	PIDLoop balanceSides=new PIDLoop(-.0012f, -.000046f, -.00002f, 10, .3f);
	public YElev(DigitalInput hallEffectL, DigitalInput hallEffectR, VictorSP elevMotor1, VictorSP elevMotor2,
			Encoder elevEncoder1, Encoder elevEncoder2) {
		super(hallEffectL, elevMotor1, elevEncoder1);
		extraMotor=elevMotor2;
		extraEncoder=elevEncoder2;
		balanceSides.StartLoop();
		//FIXME get correct values
		clicksPerFoot=0;
		safetyLow=0;
		safetyHigh=0;
		clicksTolerance=1000;
		motorReverse=true;
	}
	void runCalibration()
	{
		while(hallsensor.get()) {
			motor.set(motorReverse?.3:-.3);
			extraMotor.set(motorReverse?.3:-.3);
		}//go down
		encoderPosDown=encoder.get();
		while(!hallsensor.get()) {
			motor.set(motorReverse?.3:-.3);
			extraMotor.set(motorReverse?.3:-.3);
		}
		while(hallsensor.get()) {
			motor.set(motorReverse?-.3:.3);
			extraMotor.set(motorReverse?-.3:.3);
		}
		encoderPosUp=encoder.get();
		motor.set(0);extraMotor.set(0);
		encoderPosCenter=(encoderPosUp+encoderPosDown)/2;
	}
	void runWithSafety(float val)
	{
		float calib=balanceSides.CalibrateLoop(extraEncoder.get()-encoder.get());
		if((motorReverse?-val:val)<0 && 
				getHeight()*clicksPerFoot>safetyLow*Math.abs(clicksPerFoot) || 
				(motorReverse?-val:val)>0 && 
				getHeight()*clicksPerFoot<safetyHigh*Math.abs(clicksPerFoot))
		{
			motor.set(0);
			extraMotor.set(calib);
		}
		else 
		{
			motor.set(val);
			extraMotor.set(val+calib);
		}
	}
}
