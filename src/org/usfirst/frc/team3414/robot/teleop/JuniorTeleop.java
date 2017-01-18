package org.usfirst.frc.team3414.robot.teleop;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.usfirst.frc.team3414.robot.actuators.CANMotor;
import org.usfirst.frc.team3414.robot.actuators.drivetrain.JuniorDrive;
import org.usfirst.frc.team3414.robot.RobotStatus;
import org.usfirst.frc.team3414.robot.sensors.EButtonJoystick;
import org.usfirst.frc.team3414.robot.sensors.IGamepad;
import org.usfirst.frc.team3414.robot.sensors.IJoystick;
import org.usfirst.frc.team3414.robot.sensors.SensorConfig;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JuniorTeleop {

	
	CANMotor CANMotor1 = new CANMotor(new CANTalon(1), true);
	CANMotor CANMotor2 = new CANMotor(new CANTalon(2), true);
	CANMotor CANMotor3 = new CANMotor(new CANTalon(3), true);
	CANMotor CANMotor4 = new CANMotor(new CANTalon(4), true);
	CANMotor CANMotor5 = new CANMotor(new CANTalon(5), true);
	CANMotor CANMotor6 = new CANMotor(new CANTalon(6), true);	
	Joystick joyleft = new Joystick(0);
	Joystick joyright = new Joystick(1);

	public JuniorTeleop()
	{
//		executor = Executors.newFixedThreadPool(4); // Maximum 4 concurrent
//													// tasks		
//		futureTable = new HashMap<>();
//		// Instances
//		gamepad = SensorConfig.getInstance().getGamepad();
//		sensors = SensorConfig.getInstance();


	}

	public void doTeleop()
	{
		while (RobotStatus.isRunning()) {
			CANMotor1.setSpeed(joyleft.getY());
			CANMotor2.setSpeed(joyleft.getY());
			CANMotor3.setSpeed(joyright.getY());
			CANMotor4.setSpeed(joyright.getY());
		}
//		driveTask.doTask();
//
//		while (RobotStatus.isTeleop())
//		{
//			if(gamepad.getButtonValue(EJoystickButtons.ONE))
//			{	
//				if((gamepad.getButtonValue(raiseAugerButton) || gamepad.getButtonValue(lowerAugerButton)) 
//						&& (futureTable.get("manualauger") == null || futureTable.get("manualauger").isDone()))
//				{
//					futureTable.put("manualauger", manualAugerTask.doTask());
//				}
//				
//				if((gamepad.getButtonValue(raiseShooterButton) || gamepad.getButtonValue(lowerShooterButton))
//						&& (futureTable.get("manualshooter") == null || futureTable.get("manualshooter").isDone()))
//				{
//					futureTable.put("manualshooter", manualShooterTask.doTask());
//				}
//			} 
//			
//			if(gamepad.getButtonValue(EJoystickButtons.TWO)
//					&& (futureTable.get("presetshooter") == null || futureTable.get("presetshooter").isDone()))
//			{
//				futureTable.put("presetshooter", presetShooterTask.doTask());
//			}
//			
//			if(gamepad.getButtonValue(intakeButton)
//					&& (futureTable.get("intake") == null || futureTable.get("intake").isDone()))
//			{
//				futureTable.put("intake", intakeTask.doTask());
//			}
//			
//			if((presetJoystick.getButtonValue(standardDefenseButton) || presetJoystick.getButtonValue(lowBarButton))
//					&& (futureTable.get("driverpreset") == null || futureTable.get("driverpreset").isDone()))
//			{
//				futureTable.put("driverpreset", driverPresetTask.doTask());
//			}
//			
//			if (gamepad.getButtonValue(EJoystickButtons.TWELVE))
//					&& (futuretable.put())
//			
//			// Abort button
//			if (rightJoystick.getButtonValue(EJoystickButtons.ELEVEN))
//			{
//				executor.submit(() ->
//				{
//					for(String id : futureTable.keySet())
//					{
//						futureTable.get(id).cancel(true);
//					}
//				});
//
//				while (rightJoystick.getButtonValue(EJoystickButtons.ELEVEN))
//				{
//
//				}
//			}
//
//			// SmartDashboard update
//			if (smartDashboardTask == null || smartDashboardTask.isDone())
//			{
//				smartDashboardTask = executor.submit(() ->
//				{
//					// Shooter Pot Value
//					SmartDashboard.putNumber("Shooter Pot", actuators.getShooterPotentiometer().getCount());
//
//					// Pressure sensor feedback
//					SmartDashboard.putBoolean("Pressure", sensors.getPressureSwitch().isHit());
//
//					// Auger Pot Value
//					SmartDashboard.putNumber("Auger Pot", actuators.getAugerPotentiometer().getCount());
//
//					// Compass or gyro
//					SmartDashboard.putNumber("Soft Count", sensors.getGyro().getSoftCount());
//					SmartDashboard.putNumber("Hard Count", sensors.getGyro().getHardCount());
//
//					if ((-10 < sensors.getGyro().getSoftCount()) && ((sensors.getGyro().getSoftCount() < 10)))
//					{
//						SmartDashboard.putBoolean("Are we Straight?", true);
//					} else
//					{
//						SmartDashboard.putBoolean("Are we Straight?", false);
//					}
//				});
//			}
//			
//			if((gamepad.getButtonValue(lifterExtendButton) || gamepad.getButtonValue(lifterRetractButton)) && 
//					(futureTable.get("liftertask") == null || futureTable.get("liftertask").isDone()))
//			{
//				futureTable.put("liftertask", lifterTask.doTask());
//			}
//			
//			try
//			{
//				Thread.sleep(30);
//			} catch (InterruptedException e)
//			{
//
//			}
//		}
	}

//	public void interruptTeleop()
//	{
//		for(String id : futureTable.keySet())
//		{
//			futureTable.get(id).cancel(true);
//		}
//	}
}
