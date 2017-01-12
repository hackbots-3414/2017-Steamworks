package org.usfirst.frc.team3414.robot.PID;

import edu.wpi.first.wpilibj.Talon;

public interface IPIDFeedbackDevice {
	public double getCount();

	public void enable();

	public void disable();

	public void resetCount();

	public double getError();

	public double getRate();

//	public Talon.FeedbackDevice whatPIDDevice();

	public double getDistance();
	
	public void reverseSensor(boolean reversed);
}
