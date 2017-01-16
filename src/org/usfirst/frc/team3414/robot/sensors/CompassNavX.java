package org.usfirst.frc.team3414.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

public class CompassNavX {
	private AHRS ahrs;

	public CompassNavX(AHRS ahrs)
	{
		this.ahrs = ahrs;

		if (!ahrs.isMagnetometerCalibrated())
		{
			try
			{
				throw new Exception();
			} catch (Exception e)
			{
				System.out.println("Magnetometer not Calibrated");
				e.printStackTrace();
			}
		}
	}

	public double getHeading()
	{
		while (ahrs.isMagneticDisturbance())
		{
			System.out.println("Magnetic Disturbance...");
		}

		return ahrs.getCompassHeading();
	}

	public boolean getDisturbance()
	{
		return ahrs.isMagneticDisturbance();
	}
}
