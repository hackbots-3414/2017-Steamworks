package org.usfirst.frc.team3414.robot.sensors;

public interface IGyroscope {
	/**
	 * Returns values in between -180 and 180 Use hard count for finding the value based on the last reset
	 */
	public double getHardCount();

	/**
	 * Use Soft Count for finding the value based on the initial position of the robot
	 */
	public double getSoftCount();

	public void enable();

	public void disable();

	/**
	 * Use soft reset for making the gyro reset without changing the initial position of the robot
	 */
	public void softResetCount();

	/**
	 * Use hard reset for making the gyro reset AND change the initial position of the robot (Soft Count becomes the
	 * same as Hard Count = 0)
	 */
	public void hardResetCount();

	public double getPitch();

	public double getRate();

	public double getPitchRate();
}
