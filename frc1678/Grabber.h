/*
 * Grabber.h
 *
 *  Created on: Nov 7, 2015
 *      Author: Citrus CAD
 */

#ifndef SRC_GRABBER_H_
#define SRC_GRABBER_H_

class Grabber {
	VictorSP *extender, *wheel1, *wheel2, *wheel3;
	AnalogInput *proxySensor;
	DigitalInput *hallSensor;
	bool extending=false;
	bool done=true;
	int cutoff;
	void runIntake(double val){wheel1->Set(val); wheel2->Set(val); wheel3->Set(val);}
public:
	Grabber(VictorSP *extend, VictorSP *intakes1, VictorSP *intakes2, VictorSP *intakes3,
			AnalogInput *proximity, DigitalInput *hallEffect)
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
	void grab(){extending=true;}
	bool isDone(){return done;}
	void update()
	{
		if(done)
		{
			extender->Set(0);
			runIntake(0);
		}
		else if(extending)
		{
			extender->Set(.3);
			runIntake(.5);
			if(proxySensor->GetValue()>cutoff) extending=false;
		}
		else
		{
			extender->Set(-.3);
			runIntake(0);
			if(hallSensor->Get()) done=true;
		}
	}
};

#endif /* SRC_GRABBER_H_ */
