package org.usfirst.frc.team3414.robot.sensors;

public class ClockTimer implements ITimer {

	public ClockTimer()
	{

	}

	public void waitTimeInMillis(double milliseconds)
	{
		// Timer.delay(milliseconds / 1000);
		try
		{
			Thread.sleep((long) milliseconds);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public void waitTimeInSeconds(double seconds)
	{
		// Timer.delay(seconds);
		this.waitTimeInMillis(seconds * 60);
	}

	public void waitTimeInMinutes(double minutes)
	{
		// Timer.delay(minutes * 60);
		this.waitTimeInMillis(minutes * 3600);
	}

}
