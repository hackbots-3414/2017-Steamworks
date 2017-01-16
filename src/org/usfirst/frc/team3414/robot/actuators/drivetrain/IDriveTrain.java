package org.usfirst.frc.team3414.robot.actuators.drivetrain;

public interface IDriveTrain {
	
	public void driveForward(double speed);
	public void driveBackward(double speed);
	public void turnLeft(double speed, double angle);
	public void turnRight(double speed, double angle);
	public void stop();
}
