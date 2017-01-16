package org.usfirst.frc.team3414.robot.sensors;

import org.usfirst.frc.team3414.robot.PID.IPIDFeedbackDevice;
import com.kauailabs.navx.frc.AHRS;
import org.usfirst.frc.team3414.robot.sensors.BuiltInAccelerometer;
import org.usfirst.frc.team3414.robot.sensors.ClockTimer;
//import org.usfirst.frc.team3414.robot.sensors.Compass;
import org.usfirst.frc.team3414.robot.sensors.DualShockTwoController;
import org.usfirst.frc.team3414.robot.sensors.Gyroscope;
import org.usfirst.frc.team3414.robot.sensors.ICamera;
import org.usfirst.frc.team3414.robot.sensors.IGamepad;
import org.usfirst.frc.team3414.robot.sensors.IGyroscope;
import org.usfirst.frc.team3414.robot.sensors.IJoystick;
import org.usfirst.frc.team3414.robot.sensors.IPowerBoard;
import org.usfirst.frc.team3414.robot.sensors.ITimer;
import org.usfirst.frc.team3414.robot.sensors.Logitech3DJoystick;
import org.usfirst.frc.team3414.robot.sensors.MicrosoftLifeCam;
import org.usfirst.frc.team3414.robot.sensors.PowerDistributionPanel;
import org.usfirst.frc.team3414.robot.sensors.SensorConfig;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;

public class SensorConfig {

	private static SensorConfig singleton = null;

	// Camera
	private final String CAMERA_USB_PORT = "cam0";
	private ICamera cameraOne;

	// Joysticks
	private final int LEFT_JOY_CHANNEL = 0;
	private final int RIGHT_JOY_CHANNEL = 1;
	private final int GAMEPAD_CHANNEL = 2;
	private IJoystick leftJoystick;
	private IJoystick rightJoystick;
	private IGamepad easyButton;
	private IGamepad gamepad;

	// Cool stuff
//	private CompassNavX compassNavX;
	private BuiltInAccelerometer accelNavX;

	private ITimer timer;

	private IPowerBoard pdp;
	private AHRS ahrs;
	private IGyroscope gyro;

	private SensorConfig()
	{
		timer = new ClockTimer();

		try
		{
			leftJoystick = new Logitech3DJoystick(new Joystick(LEFT_JOY_CHANNEL));
			rightJoystick = new Logitech3DJoystick(new Joystick(RIGHT_JOY_CHANNEL));
			gamepad = new DualShockTwoController(new Joystick(GAMEPAD_CHANNEL));
		} catch (Exception e)
		{
			System.err.println("Joystick failed to initialize");
		}

		try
		{
			ahrs = new AHRS(SPI.Port.kMXP);
			gyro = new GyroscopeNavX(ahrs); // Resets on start up
		} catch (Exception e)
		{
			System.err.println("No NavX MXP board found, or plugged into the wrong spot");
		}

		pdp = new PowerDistributionPanel();

		cameraOne = new MicrosoftLifeCam(CAMERA_USB_PORT);

		cameraOne.enable();

		accelNavX = new AccelerometerNavX(ahrs);
		
		easyButton = new DualShockTwoController(new Joystick(3));
//		compassNavX = new CompassNavX(ahrs);
	}

	public static synchronized SensorConfig getInstance()
	{
		if (singleton == null)
		{
			singleton = new SensorConfig();
		}

		return singleton;
	}

	public IJoystick getLeftJoystick()
	{
		return leftJoystick;
	}

	public IJoystick getRightJoystick()
	{
		return rightJoystick;
	}

	public ITimer getTimer()
	{
		return timer;
	}

	public IGamepad getGamepad()
	{
		return gamepad;
	}

	public IPowerBoard getPdp()
	{
		return pdp;
	}

	public IGyroscope getGyro()
	{
		return gyro;
	}

	public AccelerometerNavX getAccelerometer()
	{
		return accelNavX;
	}

	public CompassNavX getCompass()
	{
		return compassNavX;
	}
	
	public IGamepad getEasyButton()
	{
		return easyButton;
	}

//	public ICamera getCameraOne()
//	{
//		return cameraOne;
//	}


}
