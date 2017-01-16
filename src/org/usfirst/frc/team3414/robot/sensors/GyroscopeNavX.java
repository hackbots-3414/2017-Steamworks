package org.usfirst.frc.team3414.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

public class GyroscopeNavX {
	private AHRS ahrs;
	private boolean isEnabled = true;
	
	private double pastYaw = 0;
	private double currentYaw = 0;

	public GyroscopeNavX(AHRS ahrs)
	{
		this.ahrs = ahrs;

		ahrs.reset();
	}

	
	/**
	 * Returns values in between -180 and 180
	 */
	public double getHardCount()
	{
		if (isEnabled)
		{
			return ahrs.getYaw();
		} else
		{
			return 0;
		}
	}
	
	public double getSoftCount()
	{
		currentYaw = pastYaw + ahrs.getYaw();
		if (currentYaw > 180)
		{
			currentYaw -= 360;
		}
		if (currentYaw < -180)
		{
			currentYaw += 360;
		}
		return currentYaw;
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
		pastYaw += ahrs.getYaw();
		ahrs.reset();
	}
	
	public void hardResetCount()
	{
		pastYaw = ahrs.getYaw();
		ahrs.reset();
	}

	public double getPitch()
	{
		return ahrs.getRoll();
	}

	public double getRate()
	{
		return ahrs.getRate();
	}
	
	public double getPitchRate()
	{
		return ahrs.getRawGyroY();
	}
}
