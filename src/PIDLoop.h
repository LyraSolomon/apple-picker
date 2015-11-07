/*
 * PIDLoop.h
 *
 *  Created on: Nov 7, 2015
 *      Author: Citrus CAD
 */

#ifndef SRC_PIDLOOP_H_
#define SRC_PIDLOOP_H_
#define ABS(a) (a>0?a:-a)
class PIDLoop {

	const int maxHistorySize = 5;
	Timer *PIDTimer;
	float integral;
	float lastErr;

	float ki;
	float kd;
	float kp;

	float kOutputCap;
	float kTolerance;
	float *difference;
	int historySize;

	/** Create loop with an output cap
	 *
	 * @param p Proportional coefficient
	 * @param i Integral coefficient
	 * @param d Derivative coefficient
	 * @param tolerance Allowed error
	 * @param cap Maximun value
	 */
public:
	PIDLoop(float p, float i, float d, float tolerance, float cap) {
		difference = new float[maxHistorySize];
		PIDTimer=new Timer();
		kp = p;
		ki = i;
		kd = d;
		kOutputCap = ABS(cap);
		kTolerance = tolerance;
		lastErr = 0;
		historySize = 1;
		integral = 0;
	}
	/** Create loop without an output cap
	 *
	 * @param p Proportional coefficient
	 * @param i Integral coefficient
	 * @param d Derivative coefficient
	 * @param tolerance Allowed error
	 */
	PIDLoop(float p, float i, float d, float tolerance){
		difference = new float[maxHistorySize];
		PIDTimer=new Timer();
		kp = p;
		ki = i;
		kd = d;
		kOutputCap = 100000;
		kTolerance = tolerance;
		lastErr = 0;
		historySize = 1;
		integral = 0;
	}
	/** Starts/resets PID loop */
	void StartLoop() {
		PIDTimer->Reset();
		PIDTimer->Start();
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
		if (historySize < maxHistorySize) {
			difference[historySize] = err;
			historySize++;
		} else {
			for (int i = 0; i < maxHistorySize - 1; i++) {
				difference[i] = difference[i + 1];
			}
			difference[maxHistorySize - 1] = err;
		}

//		if(IsTerminated()) return 0;

		float dt = (float) PIDTimer->Get();
		PIDTimer->Reset();
		integral += err * dt;
		float der = (err - lastErr) / dt;
		// Reset the error
		lastErr = err;

		float output = kp * err + ki * integral + kd * der;
		if (ABS(output) > kOutputCap) {
			output = output > 0 ? kOutputCap : -kOutputCap;
		}

		SmartDashboard::PutNumber("PID output", output);
		return output;
	}
	/** Find whether the loop should be stopped
	 *
	 * @return Whether the average of the last 5 values is within the tolerance
	 */
	bool IsTerminated() {
		if (historySize < maxHistorySize) {
			SmartDashboard::PutBoolean("is terminated2", false);
			return false;
		} else {
			float avg = 0;
			for (int i = 0; i < historySize; i++) {
				avg += ABS(difference[i]);
			}
			avg /= historySize;

			if (avg < kTolerance) {
				SmartDashboard::PutBoolean("is terminated2", true);
				return true;
			} else {
				SmartDashboard::PutBoolean("is terminated2", false);
				return false;
			}
		}
	}
};

#endif /* SRC_PIDLOOP_H_ */
