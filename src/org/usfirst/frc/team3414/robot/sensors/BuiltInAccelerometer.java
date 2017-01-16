package org.usfirst.frc.team3414.robot.sensors;

import edu.wpi.first.wpilibj.interfaces.Accelerometer;

public class BuiltInAccelerometer implements IAccelerometer{

	private Accelerometer accel;

	public BuiltInAccelerometer(Accelerometer accel)
	{
		this.accel = accel;
	}

	@Override
	public double getX()
	{
		return accel.getX();
	}

	@Override
	public double getY()
	{
		return accel.getY();
	}

	@Override
	public double getZ()
	{
		return accel.getZ();
	}

	@Override
	public void reset()
	{
		// This does nothing
	}

	@Override
	public double getResultant() {
		// TODO Auto-generated method stub
		return 0;
	}
}
