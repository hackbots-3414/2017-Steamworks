package org.usfirst.frc.team3414.robot.actuators;

import org.usfirst.frc.team3414.robot.PID.IPIDFeedbackDevice;
import org.usfirst.frc.team3414.robot.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.robot.actuators.CANMotor;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import org.usfirst.frc.team3414.robot.actuators.DoubleMotor;

import org.usfirst.frc.team3414.robot.actuators.IDriveTrain;
import org.usfirst.frc.team3414.robot.actuators.drivetrain.TankDrive;
//import org.usfirst.frc.team3414.robot.actuators.TankDrive;
import org.usfirst.frc.team3414.robot.sensors.SensorConfig;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;

public class ActuatorConfig {
	private static ActuatorConfig singleton = null;

	//TODO: Set PID in the silverlight dashboard on the RoboRIO

	// Port definitions
	private final int MOTOR_LEFT_FRONT = 3;
	private final int MOTOR_LEFT_REAR = 4;
	private final int MOTOR_RIGHT_FRONT = 1;
	private final int MOTOR_RIGHT_REAR = 2;

	private final int LEFT_SHOOTER_MOTOR = 5;
	private final int RIGHT_SHOOTER_MOTOR = 6;

	private final int SHOOTER_SOLENOID_PORT = 0;
	private final int LIFTER_SOLENOID_PORT_A = 1;
	private final int LIFTER_SOLENOID_PORT_B = 2;

	// Drive base classes
	private CANTalon leftFrontMotor;
	private CANTalon leftRearMotor;
	private CANTalon rightFrontMotor;
	private CANTalon rightRearMotor;

	// Drive CAN Motors
	private CANMotor leftFrontCANMotor;
	private CANMotor leftRearCANMotor;
	private CANMotor rightFrontCANMotor;
	private CANMotor rightRearCANMotor;

	// Drive double motors
	private DoubleMotor leftDoubleMotor;
	private DoubleMotor rightDoubleMotor;

	// Drive encoders
	private IPIDFeedbackDevice leftDriveEncoder;
	private IPIDFeedbackDevice rightDriveEncoder;

	// Auger encoder
	private IPIDFeedbackDevice augerPotentiometer;

	// Drive train
	private IDriveTrain driveTrain;

	// Shooter motors
	private CANTalon leftShooterMotor;
	private CANTalon rightShooterMotor;

	// Auger motors
	private CANTalon augerIntakeMotor;
	private CANTalon augerLifterMotor;

	// Shooter lifter motor
	private CANTalon linearActuator;

	private ActuatorConfig()
	{
		try
		{
			// Instantiate CANTalons
			leftFrontMotor = new CANTalon(MOTOR_LEFT_FRONT);
			leftRearMotor = new CANTalon(MOTOR_LEFT_REAR);
			rightFrontMotor = new CANTalon(MOTOR_RIGHT_FRONT);
			rightRearMotor = new CANTalon(MOTOR_RIGHT_REAR);
		} catch (Exception e)
		{
			System.err.println("Drive Train CANTalons failed to initialize");
		}

		try
		{
			// Instantiate encoders
//			leftDriveEncoder = new BuiltInCANTalonEncoder(leftFrontMotor);
//			rightDriveEncoder = new BuiltInCANTalonEncoder(rightFrontMotor);
		} catch (Exception e)
		{
			System.err.println("Drive encoders failed to initialize");
		}

		// Instantiate CANMotors
		leftFrontCANMotor = new CANMotor(leftFrontMotor, true, leftDriveEncoder);
		leftRearCANMotor = new CANMotor(leftRearMotor, true);

		rightFrontCANMotor = new CANMotor(rightFrontMotor, false, rightDriveEncoder);
		rightRearCANMotor = new CANMotor(rightRearMotor, false);

		// Reverse the encoders because they are installed backward relative to
		// our setSpeed methods
		rightFrontMotor.reverseSensor(true);
		leftFrontMotor.reverseSensor(true);

		// Create the double motors
		leftDoubleMotor = new DoubleMotor(leftFrontCANMotor, leftRearCANMotor);
		rightDoubleMotor = new DoubleMotor(rightFrontCANMotor, rightRearCANMotor);


		//rightDoubleMotor.getPIDFeedbackDevice().setDistancePerPulse(0.0012635); //0.0012635
		//leftDoubleMotor.getPIDFeedbackDevice().setDistancePerPulse(0.0012800); // 0.00128
		
		// Create the whole drivetrain
		driveTrain = new TankDrive(leftDoubleMotor, rightDoubleMotor, SensorConfig.getInstance().getGyro());
		// driveTrain = new TankDrive(leftDoubleMotor, rightDoubleMotor);

		// set it to use PID by default
		driveTrain.enablePID();
		driveTrain.setControlMode(TalonControlMode.Speed); // Speed means use
		// encoder rates to control how fast the robot is moving

		try
		{
			// Instantiate shooter motors
			leftShooterMotor = new CANTalon(LEFT_SHOOTER_MOTOR);
			rightShooterMotor = new CANTalon(RIGHT_SHOOTER_MOTOR);
		} catch (Exception e)
		{
			System.err.println("Shooter motor failed to initalize");
		}

	}

	public static synchronized ActuatorConfig getInstance()
	{
		if (singleton == null)
		{
			singleton = new ActuatorConfig();
		}

		return singleton;
	}

	public IDriveTrain getDriveTrain()
	{
		return driveTrain;
	}

	public CANTalon getFrontLeftDriveMotor()
	{
		return leftFrontMotor;
	}

	public CANTalon getFrontRightDriveMotor()
	{
		return rightFrontMotor;
	}
	
	public CANTalon getBackLeftDriveMotor()
	{
		return leftRearMotor;
	}
	
	public CANTalon getBackRightDriveMotor()
	{
		return rightRearMotor;
	}

	public IPIDFeedbackDevice getLeftDriveEncoder()
	{
		return leftDriveEncoder;
	}

	public IPIDFeedbackDevice getRightDriveEncoder()
	{
		return rightDriveEncoder;
	}

	public IPIDFeedbackDevice getAugerPotentiometer()
	{
		return augerPotentiometer;
	}
	

}
