package org.usfirst.frc.team1678.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDLoop {

	final int maxHistorySize = 5;
	protected Timer PIDTimer;
	protected float integral;
	protected float lastErr;

	protected float ki;
	protected float kd;
	protected float kp;

	protected float outputCap;
	protected float oldTermErr;
	protected float difference[]=new float[maxHistorySize];
	protected int historySize;
	
	public PIDLoop(float p, float i, float d, float cap)
	{
		kp=p; ki=i; kd=d;
		PIDTimer=new Timer();
		outputCap=Math.abs(cap);
	}
	public PIDLoop(float p, float i, float d)
	{
		kp=p; ki=i; kd=d;
		PIDTimer=new Timer();
		outputCap=100000;
	}
	public void StartLoop() {
		PIDTimer.reset();
		PIDTimer.start();
		historySize = 0;
		integral = 0;
		lastErr = 0;
	}
	float CalibrateLoop(float err) {
		// Find the difference between where you want to end and where you are
		// err = fabs(target - input);

		float dt = (float) PIDTimer.get();
		PIDTimer.reset();
		integral += err * dt;
		float der = (err - lastErr) / dt;
		// Reset the error
		lastErr = err;

		float output = kp * err + ki * integral + kd * der;
		if(Math.abs(output) > outputCap) {
			output = output>0 ? outputCap : -outputCap;
		}

		// difference calculation here and add to termination array
		if(historySize < maxHistorySize) {
			difference[historySize] = err;
			historySize++;
		} else {
			for(int i=0; i<maxHistorySize-1; i++)
			{
				difference[i]=difference[i+1];
			}
			difference[maxHistorySize-1] = err;
		}

		SmartDashboard.putNumber("PID output", output);
		return output;
	}
	boolean IsTerminated() {
		if(historySize < maxHistorySize) {
			SmartDashboard.putBoolean("is terminated2", false);
			return false;
		} else {
			float avg=0;
			for(int i = 0; i < historySize; i++){
				avg += Math.abs(difference[i]);
			}
			avg /= historySize;

			if(avg < 20) {
				SmartDashboard.putBoolean("is terminated2", true);
				return true;
			} else {
				SmartDashboard.putBoolean("is terminated2", false);
				return false;
			}
		}
	}
}
