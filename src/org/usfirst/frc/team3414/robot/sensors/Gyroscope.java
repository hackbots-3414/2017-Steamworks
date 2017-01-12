package org.usfirst.frc.team3414.robot.sensors;

import edu.wpi.first.wpilibj.AnalogGyro;

public class Gyroscope {
	private AnalogGyro gyro;
	private boolean isEnabled;

	private double pastAngle = 0;
	private double currentAngle = 0;

	public Gyroscope(AnalogGyro gyro)
	{
		this.gyro = gyro;

		gyro.initGyro();

		isEnabled = true;
	}

	
	/**
	 * Returns values in between 0 and 0
	 */
	public double getHardCount()
	{
		if (isEnabled)
		{
			return gyro.getAngle();
		} else
		{
			return 0;
		}
	}

	public double getSoftCount()
	{
		currentAngle = pastAngle + gyro.getAngle();
		if (currentAngle > 180)
		{
			currentAngle -= 360;
		}
		if (currentAngle < -180)
		{
			currentAngle += 360;
		}
		return currentAngle;
	}

	public void enable()
	{
		isEnabled = true;
	}

	public void disable()
	{
		isEnabled = false;
	}

	public void softResetCount()
	{
		pastAngle += gyro.getAngle();
		gyro.reset();
	}
	
	public void hardResetCount()
	{
//		pastAngle = gyro.getAngle();
		pastAngle = 0;
		currentAngle = 0;
		gyro.reset();
	}

	public double getAttitude()
	{
		return 0.0;
	}

	public double getPitch()
	{
		return 0;
	}

	public double getRate()
	{
		return 0;
	}

	public double getPitchRate()
	{
		return 0;
	}
}
