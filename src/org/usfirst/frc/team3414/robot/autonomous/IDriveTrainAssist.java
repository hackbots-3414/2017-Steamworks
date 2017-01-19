package org.usfirst.frc.team3414.robot.autonomous;

public interface IDriveTrainAssist {
	public void centerDriveTrain(double speed);
	
	public void turnToAngle(double desiredDegrees, double speed);
	
	public boolean isTilt();
	
	public boolean isTiltGyro();
	
	public void driveTrainCoast(boolean coast);
}
