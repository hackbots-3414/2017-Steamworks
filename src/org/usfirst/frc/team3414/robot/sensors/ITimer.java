package org.usfirst.frc.team3414.robot.sensors;

public interface ITimer {
	public void waitTimeInMillis(double milliseconds);

	public void waitTimeInSeconds(double seconds);

	public void waitTimeInMinutes(double minutes);
}
