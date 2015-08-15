package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.*;

public class Grabber {
	VictorSP extender, wheels;
	AnalogInput proxySensor;
	DigitalInput hallSensor;
	boolean extending=false;
	boolean done=true;
	int cutoff;
	public Grabber(VictorSP extend, VictorSP intakes, AnalogInput proximity, DigitalInput hallEffect)
	{
		extender=extend;
		wheels=intakes;
		proxySensor=proximity;
		hallSensor=hallEffect;
		//FIXME get actual values
		cutoff=1000;
	}
	void setExtender(float val){extender.set(val);}
	void runIntake(float val){wheels.set(val);}
	void grab(){extending=true;}
	boolean isDone(){return done;}
	void update()
	{
		if(done)
		{
			extender.set(0);
		}
		else if(extending)
		{
			extender.set(.3);
			if(proxySensor.getValue()>cutoff) extending=false;
		}
		else
		{
			extender.set(-.3);
			if(hallSensor.get()) done=true;
		}
	}
}
