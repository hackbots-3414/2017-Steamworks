package org.usfirst.frc.team3414.robot.sensors;

public interface IAccelerometer {
	public double getX();

	public double getY();

	public double getZ();
	
	public double getResultant();

	public void reset();
}
