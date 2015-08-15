package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

public class XElev extends Elevator{
	public XElev(DigitalInput hallEffect, VictorSP elevMotor, Encoder elevEncoder)
	{
		super(hallEffect, elevMotor, elevEncoder);
		//FIXME get correct values
		clicksPerFoot=0;
		safetyLow=0;
		safetyHigh=0;
		clicksTolerance=1000;
		motorReverse=true;
	}
}
