
package org.usfirst.frc.team3414.robot;

import java.util.concurrent.Executors;

//import org.usfirst.frc.team3414.robot.actuators.ActuatorConfig;
//import org.usfirst.frc.team3414.robot.autonomous.IAutonomousControl;
import org.usfirst.frc.team3414.robot.sensors.SensorConfig;
import org.usfirst.frc.team3414.robot.teleop.JuniorTeleop;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotStatus.checkIsAlpha();
	SensorConfig.getInstance();
	ActuatorConfig.getInstance();
	teleop = new JuniorTeleop();
	executor = Executors.newFixedThreadPool(2);
	AHRS ahrs;
}

public void robotInit()
{

	makeAutoChooser();
	makePositionChooser();
    ahrs = new AHRS(SPI.Port.kMXP);
}

private void makeAutoChooser()
{
	autoChooser = new SendableChooser();

//	autoChooser.addObject("Do Nothing", new AutonDoNothing());
//	autoChooser.addObject("Reach Defenses", new AutonReachDefenses());
//	autoChooser.addObject("Breach Standard Defenses", new AutonBreachDefenses());
////	autoChooser.addDefault("Low Bar NO SHOOT", new AutonLowBar());
//	autoChooser.addObject("Low Bar and Shoot Low", new AutonLowBarAndShootLow());
//	autoChooser.addObject("Low Bar and Shoot High-PICK THIS ALMOST ALWAYS", new AutonLowBarAndShootHigh());
//	autoChooser.addObject("Fourty Kai", new FourtyKai());
//	autoChooser.addObject("Cheval De Frise", new AutonChevalDeFrise());
//	autoChooser.addObject("Portcullis", new AutonPortcullis());
	
	SmartDashboard.putData("Autonomous Chooser", autoChooser);
}

private void makePositionChooser()
{
	positionChooser = new SendableChooser();
	
//	positionChooser.addObject("Position One (Low Bar)", EAutoPositions.ONE);
//	positionChooser.addObject("Position Two (Something Else)", EAutoPositions.TWO);
//	positionChooser.addObject("Position Three (Something Else)", EAutoPositions.THREE);
//	positionChooser.addObject("Position Four (Something Else)", EAutoPositions.FOUR);
//	positionChooser.addObject("Position Five (Something Else)", EAutoPositions.FIVE);
	
	SmartDashboard.putData("Autonomous Position Chooser", positionChooser);
}

public void autonomous()
{
	RobotStatus.setIsRunning(true);
	RobotStatus.setIsAuto(true);
	RobotStatus.setIsTeleop(false);

	SensorConfig.getInstance().getGyro().hardResetCount();
	System.out.println("Autonomous Mode" + positionChooser.getSelected());


	executor.submit(() ->
	{
		while (RobotStatus.isAuto())
		{
			teleop.printToSmartDashboard();
			
			SensorConfig.getInstance().getTimer().waitTimeInMillis(50);
		}
	});
	executor.submit(() ->
	{
		System.out.println("Auto Running");

		ActuatorConfig.getInstance().getDriveTrainAssist().driveTrainCoast(false); // Should do the same thing
		
//		ActuatorConfig.getInstance().getFrontLeftDriveMotor().enableBrakeMode(true);
//		ActuatorConfig.getInstance().getFrontRightDriveMotor().enableBrakeMode(true);
//		ActuatorConfig.getInstance().getBackLeftDriveMotor().enableBrakeMode(true);
//		ActuatorConfig.getInstance().getBackRightDriveMotor().enableBrakeMode(true);
		
		((IAutonomousControl) autoChooser.getSelected()).doAuto((EAutoPositions)positionChooser.getSelected());
	});
}

public void operatorControl()
{
	RobotStatus.setIsRunning(true);
	RobotStatus.setIsAuto(false);
	RobotStatus.setIsTeleop(true);
	
	ActuatorConfig.getInstance().getDriveTrainAssist().driveTrainCoast(true);
	
	teleop.doTeleop();
}

public void disabled()
{
	RobotStatus.setIsRunning(false);
	RobotStatus.setIsAuto(false);
	RobotStatus.setIsTeleop(false);
}

public void test()
{
	RobotStatus.setIsRunning(true);
	RobotStatus.setIsAuto(false);
	RobotStatus.setIsTeleop(false);
}
}
