package org.usfirst.frc.team3414.robot.actuators;

import org.usfirst.frc.team3414.robot.PID.IPIDFeedbackDevice;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class DoubleMotor {

	private Motor motorOne, motorTwo;
	private CANMotor CANMotorOne, CANMotorTwo;

	/**
	 * Used for motors that are not connected to Talon SRX motor controllers
	 * 
	 * @param motorOne
	 * @param motorTwo
	 */
	public DoubleMotor(Motor motorOne, Motor motorTwo)
	{
		this.motorOne = motorOne;
		this.motorTwo = motorTwo;
	}

	/**
	 * Used for motors that are connected to Talon SRX motor controllers
	 * 
	 * @param motorOne
	 * @param motorTwo
	 */
	public DoubleMotor(CANMotor motorOne, CANMotor motorTwo)
	{
		this.CANMotorOne = motorOne;
		this.CANMotorTwo = motorTwo;

		// Follower mode makes one CANTalon go the same speed as another
		// CANTalon without issuing them both separate commands.
		motorTwo.setControlMode(TalonControlMode.Follower);
		motorTwo.setFollowerDeviceID(motorOne.getDeviceID());
	}

	public double getSpeed()
	{
		if (CANMotorOne != null)
		{
			return CANMotorOne.getSpeed();
		} else
		{
			return ((motorOne.getSpeed() + motorTwo.getSpeed()) / (2));
		}

	}

	public void setSpeed(double speed)
	{
		if (CANMotorOne != null)
		{
			CANMotorOne.setSpeed(speed);
		} else
		{
			motorOne.setSpeed(speed);
			motorTwo.setSpeed(speed);
		}
	}

	public void stop()
	{
		if (CANMotorOne != null)
		{
			CANMotorOne.stop();
		} else
		{
			motorOne.stop();
			motorTwo.stop();
		}
	}

	public void setP(double p)
	{
		if (CANMotorOne != null)
		{
			CANMotorOne.setP(p);
		} else
		{
			System.out.println("CANNOT SET PID OF A NON-CAN MOTOR");
		}
	}

	public void setI(double i)
	{
		if (CANMotorOne != null)
		{
			CANMotorOne.setI(i);
		} else
		{
			System.out.println("CANNOT SET PID OF A NON-CAN MOTOR");
		}
	}

	public void setD(double d)
	{
		if (CANMotorOne != null)
		{
			CANMotorOne.setD(d);
		} else
		{
			System.out.println("CANNOT SET PID OF A NON-CAN MOTOR");
		}
	}

	public void setPIDFeedbackDevice(IPIDFeedbackDevice device)
	{
		if (CANMotorOne != null)
		{
			CANMotorOne.setPIDFeedbackDevice(device);
		} else
		{
			System.out.println("CANNOT SET PID OF A NON-CAN MOTOR");
		}
	}

	public IPIDFeedbackDevice getPIDFeedbackDevice()
	{
		if (CANMotorOne != null)
		{
			return CANMotorOne.getPIDFeedbackDevice();
		} else
		{
			System.out.println("CANNOT SET PID OF A NON-CAN MOTOR");
			return null;
		}

	}

	public void enablePID()
	{
		if (CANMotorOne != null)
		{
			//only enable one because we are in follower mode.
			CANMotorOne.enablePID();
		} else
		{
			System.out.println("CANNOT SET PID OF A NON-CAN MOTOR");
		}
	}

	public void disablePID()
	{
		if (CANMotorOne != null)
		{
			CANMotorOne.disablePID();
		} else
		{
			System.out.println("CANNOT SET PID OF A NON-CAN MOTOR");
		}
	}

	public TalonControlMode getControlMode()
	{
		if (CANMotorOne != null)
		{
			return CANMotorOne.getControlMode();
		} else
		{
			System.out.println("CANNOT SET PID OF A NON-CAN MOTOR");
			return null;
		}
	}

	public void setControlMode(TalonControlMode mode)
	{
		if (CANMotorOne != null)
		{
			CANMotorOne.setControlMode(mode);
		} else
		{
			System.out.println("CANNOT SET PID OF A NON-CAN MOTOR");
		}
	}

	public CANMotor getCANMotorOne()
	{
		return CANMotorOne;
	}

	public CANMotor getCANMotorTwo()
	{
		return CANMotorTwo;
	}

}
