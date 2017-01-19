package org.usfirst.frc.team3414.robot.autonomous;

import org.usfirst.frc.team3414.robot.actuators.IDriveTrain;
import org.usfirst.frc.team3414.robot.sensors.IGyroscope;
import org.usfirst.frc.team3414.robot.sensors.SensorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrainAssist {
	private IDriveTrain driveTrain;
	private IGyroscope gyro;

	public DriveTrainAssist(IDriveTrain driveTrain, IGyroscope gyro)
	{
		this.driveTrain = driveTrain;
		this.gyro = gyro;
	}

	public void centerDriveTrain(double speed)
	{
		turnToAngle(0, speed);
	}

	public void turnToAngle(double desiredDegrees, double speed)
	{
		if ((-180 <= desiredDegrees) && (desiredDegrees <= 180))
		{
			driveTrain.disablePID();

			if (gyro.getHardCount() > desiredDegrees)
			{
				driveTrain.setSpeed(speed, -speed);
				while ((gyro.getHardCount() > desiredDegrees) && RobotStatus.isRunning())
					;
			} else if (gyro.getHardCount() < desiredDegrees)
			{

				driveTrain.setSpeed(-speed, speed);
				while ((gyro.getHardCount() < desiredDegrees) && RobotStatus.isRunning())
					;
			}

			driveTrain.stopDrive();
		}
	}

	public boolean isTilt()
	{
		SmartDashboard.putNumber("Y Rate", SensorConfig.getInstance().getAccelerometer().getY());

		if (SensorConfig.getInstance().getAccelerometer().getY() > 6)
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * Doesn't work too well
	 * 
	 * @return isTiltGyro
	 */
	public boolean isTiltGyro()
	{
		if (SensorConfig.getInstance().getGyro().getPitchRate() > 20) // From 25
		{
			return true;
		} else
		{
			return false;
		}
	}
}
