package org.usfirst.frc.team3414.robot.teleop;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.usfirst.frc.team3414.robot.actuators.CANMotor;
import org.usfirst.frc.team3414.robot.actuators.drivetrain.JuniorDrive;
import org.usfirst.frc.team3414.robot.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.robot.actuators.IDriveTrain;
import org.usfirst.frc.team3414.robot.sensors.EButtonJoystick;
import org.usfirst.frc.team3414.robot.teleop.PIDOverride;
import org.usfirst.frc.team3414.robot.RobotStatus;
import org.usfirst.frc.team3414.robot.sensors.EButtonJoystick;
import org.usfirst.frc.team3414.robot.sensors.IGamepad;
import org.usfirst.frc.team3414.robot.sensors.IJoystick;
import org.usfirst.frc.team3414.robot.sensors.SensorConfig;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JuniorTeleop implements ITeleopControl {
	private ExecutorService executor;

	// Driver Functions
	private double correctedYLeft, correctedYRight;
	private boolean pidOn = false;
	private boolean deadZoned = false;
	private double speedMultiplier = 1.0;
	private boolean toggleLockL5A = false;
	private boolean toggleLockL5B = true;

	private boolean autoGyroDriveActivated = false;
	// Instances
	private IGamepad gamepad;
	private IGamepad easyButton;
	private IDriveTrain driveTrain;
	private IJoystick leftJoystick;
	private IJoystick rightJoystick;

	public JuniorTeleop()
	{
		executor = Executors.newFixedThreadPool(4);

		// Instances
		gamepad = SensorConfig.getInstance().getGamepad();
		easyButton = SensorConfig.getInstance().getEasyButton();
		driveTrain = ActuatorConfig.getInstance().getDriveTrain();
		leftJoystick = SensorConfig.getInstance().getLeftJoystick();
		rightJoystick = SensorConfig.getInstance().getRightJoystick();
	}

	@Override
	public void doTeleop()
	{

		// DRIVE THREAD
		executor.submit(() ->
		{
			// Default PID To Off
			driveTrain.enablePID();
			driveTrain.setControlMode(TalonControlMode.Speed);
			driveTrain.disablePID();
			PIDOverride.getInstance().setTeleopDisablePID(true);

			while (RobotStatus.isRunning())
			{
				if (RobotStatus.isTeleop())
				{
					// Drive Train Loop
					doDriverFunctions();
				}
				// PRINT SMARTDASHBAORD VALUES
				printToSmartDashboard();

				SensorConfig.getInstance().getTimer().waitTimeInMillis(50);
			}
		});

		// MANUAL COMMANDS
		executor.submit(() ->
		{
			while (RobotStatus.isRunning())
			{
				if (RobotStatus.isTeleop())
				{
					manualCommands();
				}
				SensorConfig.getInstance().getTimer().waitTimeInMillis(50);
			}

		});

		// AUTOMATIC COMMANDS
		executor.submit(() ->
		{

			while (RobotStatus.isRunning())
			{
				if (RobotStatus.isTeleop())
				{

				}
				
				SensorConfig.getInstance().getTimer().waitTimeInMillis(50);
			}
		});

	}

	private void doDriverFunctions()
	{
		// TOGGLE PID
		if ((SensorConfig.getInstance().getLeftJoystick().getButtonValue(EButtonJoystick.FIVE)) && !toggleLockL5A
				&& !toggleLockL5B)
		{
			toggleLockL5A = true;
			// DO 1 - On Click
			ActuatorConfig.getInstance().getDriveTrain().disablePID();
			PIDOverride.getInstance().setTeleopDisablePID(true);
			pidOn = false;
		}
		if ((!SensorConfig.getInstance().getLeftJoystick().getButtonValue(EButtonJoystick.FIVE)) && toggleLockL5A
				&& !toggleLockL5B)
		{
			toggleLockL5A = false;
			toggleLockL5B = true;
			// DO 2 - On Release
		}
		if ((SensorConfig.getInstance().getLeftJoystick().getButtonValue(EButtonJoystick.FIVE)) && !toggleLockL5A
				&& toggleLockL5B)
		{
			toggleLockL5A = true;
			// DO 3 - On Click
			ActuatorConfig.getInstance().getDriveTrain().enablePID();
			ActuatorConfig.getInstance().getDriveTrain().setControlMode(TalonControlMode.Speed);
			PIDOverride.getInstance().setTeleopDisablePID(false);
			pidOn = true;
		}
		if ((!SensorConfig.getInstance().getLeftJoystick().getButtonValue(EButtonJoystick.FIVE)) && toggleLockL5A
				&& toggleLockL5B)
		{
			toggleLockL5A = false;
			toggleLockL5B = false;
			// DO 4 - On Release
		}

		SmartDashboard.putBoolean("PID", pidOn);

		// WITH PID
		if (pidOn)
		{
			correctedYLeft = Math.atan(SensorConfig.getInstance().getLeftJoystick().getY()) * (4 / Math.PI) * 400;
			correctedYRight = Math.atan(SensorConfig.getInstance().getRightJoystick().getY()) * (4 / Math.PI) * 400;

			if (correctedYLeft > 25 || correctedYLeft < -25 || correctedYRight > 25 || correctedYRight < -25)
			{

				if (SensorConfig.getInstance().getRightJoystick().getButtonValue(EButtonJoystick.ONE))
				{
					if (rightJoystick.getButtonValue(EButtonJoystick.TWO))
					{
						if (SensorConfig.getInstance().getRightJoystick().getY() > 0)
						{
							ActuatorConfig.getInstance().getDriveTrain().driveStraight((4 / Math.PI) * 400); // Go
																												// Forwards
						} else
						{
							ActuatorConfig.getInstance().getDriveTrain().driveStraight(-(4 / Math.PI) * 400); // Go
																												// Backwards
						}
					} else
					{
						ActuatorConfig.getInstance().getDriveTrain().setSpeed(correctedYRight * speedMultiplier);
					}
					SmartDashboard.putBoolean("DRIVE TOGETHER", true);
				} else
				{
					ActuatorConfig.getInstance().getDriveTrain().setSpeed(correctedYLeft * speedMultiplier,
							correctedYRight * speedMultiplier);
					SmartDashboard.putBoolean("DRIVE TOGETHER", false);
				}

				deadZoned = false;
			} else
			{
				if (!deadZoned)
				{
					ActuatorConfig.getInstance().getDriveTrain().stopDrive();
					deadZoned = true;
				} else
				{
					// don't do anything
				}
			}

			// WITHOUT PID
		} else
		{

			if (SensorConfig.getInstance().getRightJoystick().getButtonValue(EButtonJoystick.ONE))
			{
				if (!autoGyroDriveActivated)
				{
					ActuatorConfig.getInstance().getDriveTrain().disablePID();

					SensorConfig.getInstance().getGyro().softResetCount();

					autoGyroDriveActivated = true;
				}

				if (rightJoystick.getButtonValue(EButtonJoystick.TWO))
				{
					if (SensorConfig.getInstance().getRightJoystick().getY() > 0)
					{
						ActuatorConfig.getInstance().getDriveTrain().driveStraight(1.0); // Go Forwards
					} else
					{
						ActuatorConfig.getInstance().getDriveTrain().driveStraight(-1.0); // Go Backwards
					}
				} else
				{
					ActuatorConfig.getInstance().getDriveTrain()
							.driveStraight(SensorConfig.getInstance().getRightJoystick().getY());
				}
				SmartDashboard.putBoolean("DRIVE TOGETHER", true);
			} else
			{
				autoGyroDriveActivated = false;

				ActuatorConfig.getInstance().getDriveTrain().setSpeed(
						SensorConfig.getInstance().getLeftJoystick().getY() * speedMultiplier,
						SensorConfig.getInstance().getRightJoystick().getY() * speedMultiplier);
				SmartDashboard.putBoolean("DRIVE TOGETHER", false);
			}
		}

		if (SensorConfig.getInstance().getLeftJoystick().getButtonValue(EButtonJoystick.ONE))
		{
			speedMultiplier = 0.5;
			SmartDashboard.putBoolean("DRIVE BY HALF", true);
		} else
		{
			speedMultiplier = 1.0;
			SmartDashboard.putBoolean("DRIVE BY HALF", false);
		}
	}

	public void manualCommands()
	{
		}
	

	@Override
	public void printToSmartDashboard()
	{

		// Compass
		SmartDashboard.putNumber("Yaw", SensorConfig.getInstance().getGyro().getSoftCount());


		if ((-10 < SensorConfig.getInstance().getGyro().getSoftCount())
				&& ((SensorConfig.getInstance().getGyro().getSoftCount() < 10)))
		{
			SmartDashboard.putBoolean("Are we Straight?", true);
		} else
		{
			SmartDashboard.putBoolean("Are we Straight?", false);
		}
	}

}
