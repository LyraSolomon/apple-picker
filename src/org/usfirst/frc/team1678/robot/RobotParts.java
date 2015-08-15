package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

public class RobotParts {
	// All sensors are temporary values
	// TODO Change these when electrical wires things in
	public static Encoder yAxisLEnc = new Encoder(0, 1), yAxisREnc = new Encoder(2, 3);
	public static Encoder xAxisEnc = new Encoder(4, 5);
	public static Encoder extendEnc = new Encoder(6, 7);
	
	public static DigitalInput xHallEffect = new DigitalInput(8);
	public static DigitalInput yHallEffectL = new DigitalInput(9), yHallEffectR = new DigitalInput(10);
	public static DigitalInput extendHallEffect = new DigitalInput(11);
	
	public static AnalogInput grabberProxSensor = new AnalogInput(12);
	
	// Acutators
	public static VictorSP yAxisLSC = new VictorSP(3), yAxisRSC = new VictorSP(4);
	public static VictorSP xAxisSC = new VictorSP(5);
	public static VictorSP extendSC = new VictorSP(6);
	public static VictorSP grabberSC = new VictorSP(0);
}
