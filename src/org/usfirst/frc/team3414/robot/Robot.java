
package org.usfirst.frc.team3414.robot;

import java.util.concurrent.Executors;

import org.usfirst.frc.team3414.robot.actuators.CANMotor;
//import org.usfirst.frc.team3414.robot.actuators.ActuatorConfig;
//import org.usfirst.frc.team3414.robot.autonomous.IAutonomousControl;
import org.usfirst.frc.team3414.robot.sensors.SensorConfig;
import org.usfirst.frc.team3414.robot.teleop.JuniorTeleop;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SerialPort;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
//	RobotStatus.checkIsAlpha();
//	SensorConfig.getInstance();
//	ActuatorConfig.getInstance();
	JuniorTeleop teleop = new JuniorTeleop();
//	executor = Executors.newFixedThreadPool(2);
	AHRS ahrs;
	RobotDrive myRobot;
    
public void robotInit()
{
    ahrs = new AHRS(SerialPort.Port.kMXP);
}

public void teleopPeriodic()
{
    operatorControl();
}


public void operatorControl()
{
	RobotStatus.setIsRunning(true);
	RobotStatus.setIsAuto(false);
	RobotStatus.setIsTeleop(true);
	System.out.println("hi I'm Kayleigh");
	

//	ActuatorConfig.getInstance().getDriveTrainAssist().driveTrainCoast(true);
	
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
