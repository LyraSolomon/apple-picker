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

	protected float kOutputCap;
	protected float kTolerance;
	protected float oldTermErr;
	protected float difference[]=new float[maxHistorySize];
	protected int historySize;
	
	/** Create loop with an output cap
	 * 
	 * @param p Proportional coefficient
	 * @param i Integral coefficient
	 * @param d Derivative coefficient
	 * @param tolerance Allowed error
	 * @param cap Maximun value
	 */
	public PIDLoop(float p, float i, float d, float tolerance, float cap)
	{
		kp=p; ki=i; kd=d;
		PIDTimer=new Timer();
		kOutputCap=Math.abs(cap);
		kTolerance=tolerance;
	}
	/** Create loop without an output cap
	 * 
	 * @param p Proportional coefficient
	 * @param i Integral coefficient
	 * @param d Derivative coefficient
	 * @param tolerance Allowed error
	 */
	public PIDLoop(float p, float i, float d, float tolerance)
	{
		kp=p; ki=i; kd=d;
		PIDTimer=new Timer();
		kOutputCap=100000;
		kTolerance=tolerance;
	}
	/** Starts/resets PID loop */
	public void StartLoop() {
		PIDTimer.reset();
		PIDTimer.start();
		historySize = 0;
		integral = 0;
		lastErr = 0;
	}
	/** Run loop
	 * 
	 * @param err Distance from target value
	 * @return Motor output
	 */
	float CalibrateLoop(float err) {
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
		
//		if(IsTerminated()) return 0;
		
		float dt = (float) PIDTimer.get();
		PIDTimer.reset();
		integral += err * dt;
		float der = (err - lastErr) / dt;
		// Reset the error
		lastErr = err;

		float output = kp * err + ki * integral + kd * der;
		if(Math.abs(output) > kOutputCap) {
			output = output>0 ? kOutputCap : -kOutputCap;
		}

		

		SmartDashboard.putNumber("PID output", output);
		return output;
	}
	/** Find whether the loop should be stopped
	 * 
	 * @return Whether the average of the last 5 values is within the tolerance 
	 */
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

			if(avg < kTolerance) {
				SmartDashboard.putBoolean("is terminated2", true);
				return true;
			} else {
				SmartDashboard.putBoolean("is terminated2", false);
				return false;
			}
		}
	}
}
