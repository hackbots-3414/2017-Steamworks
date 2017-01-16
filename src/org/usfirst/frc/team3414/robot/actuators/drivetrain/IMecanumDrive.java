package org.usfirst.frc.team3414.robot.actuators.drivetrain;

public interface IMecanumDrive {
	public void driveLeft(double speed);
	public void driveRight(double speed);
	public void driveAtAngle(double speed, double angle);
}
