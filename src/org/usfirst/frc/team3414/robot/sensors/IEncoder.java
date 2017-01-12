package org.usfirst.frc.team3414.robot.sensors;

import org.usfirst.frc.team3414.robot.PID.IPIDFeedbackDevice;

public interface IEncoder extends IPIDFeedbackDevice {
	
	public double getAcceleration();
	
	public double getJerk();
}
