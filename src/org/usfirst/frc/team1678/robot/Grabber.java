package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.*;

public class Grabber {
	VictorSP extender, wheel1, wheel2, wheel3;
	AnalogInput proxySensor;
	DigitalInput hallSensor;
	boolean extending=false;
	boolean done=true;
	int cutoff;
	public Grabber(VictorSP extend, VictorSP intakes1, VictorSP intakes2, VictorSP intakes3,
			AnalogInput proximity, DigitalInput hallEffect)
	{
		extender=extend;
		wheel1=intakes1;
		wheel2=intakes2;
		wheel3=intakes3;
		proxySensor=proximity;
		hallSensor=hallEffect;
		//FIXME get actual values
		cutoff=1000;
	}
	void runIntake(double val){wheel1.set(val); wheel2.set(val); wheel3.set(val);}
	void grab(){extending=true;}
	boolean isDone(){return done;}
	void update()
	{
		if(done)
		{
			extender.set(0);
			runIntake(0);
		}
		else if(extending)
		{
			extender.set(.3);
			runIntake(.5);
			if(proxySensor.getValue()>cutoff) extending=false;
		}
		else
		{
			extender.set(-.3);
			runIntake(0);
			if(hallSensor.get()) done=true;
		}
	}
}
