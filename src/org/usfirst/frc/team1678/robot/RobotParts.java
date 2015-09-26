package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

public class RobotParts {
	// All sensors are temporary values
	// TODO Change these when electrical wires things in
	public static Encoder yAxisEnc = new Encoder(0, 1);
	public static Encoder xAxisEnc = new Encoder(4, 5);
	
	public static DigitalInput xHallEffect = new DigitalInput(8);
	public static DigitalInput yHallEffect = new DigitalInput(9);
	public static DigitalInput extendHallEffect = new DigitalInput(11);
	
	public static AnalogInput grabberProxSensor = new AnalogInput(12);
	
	// Acutators
	public static VictorSP yAxisMotor1 = new VictorSP(3), yAxisMotor2;
	public static VictorSP xAxisMotor = new VictorSP(5);
	public static VictorSP extendMotor = new VictorSP(6);
	public static VictorSP grabberMotor1 = new VictorSP(0), grabberMotor2, grabberMotor3;
}
