package org.usfirst.frc.team3414.robot.actuators;

import org.usfirst.frc.team3414.robot.PID.IPIDFeedbackDevice;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class CANMotor {
	private boolean invertDirection;
	private CANTalon canMotor;
	private IPIDFeedbackDevice device;

	public CANMotor(CANTalon motor, boolean invertDirection)
	{
		// Changes the motor controller to accept percentages expressed in
		// decimals of the voltage of the system.
		motor.changeControlMode(TalonControlMode.PercentVbus);
		// motor.setInverted(invertDirection);
		this.canMotor = motor;
		this.invertDirection = invertDirection;
	}

	/**
	 * CANMotor Constructor which uses PID
	 */
	public CANMotor(CANTalon motor, boolean invertDirection, IPIDFeedbackDevice device)
	{
		this(motor, invertDirection);
		this.device = device;

		canMotor.setFeedbackDevice(device.whatPIDDevice());
	}

	public void setP(double p)
	{
		canMotor.setP(p);
	}

	public void setI(double i)
	{
		canMotor.setI(i);
	}

	public void setD(double d)
	{
		canMotor.setD(d);
	}

	public void setPIDFeedbackDevice(IPIDFeedbackDevice device)
	{
		this.device = device;
	}

	public IPIDFeedbackDevice getPIDFeedbackDevice()
	{
		return device;
	}

	public void enablePID()
	{
		canMotor.reset();
		canMotor.enable();
	}

	public void disablePID()
	{
		canMotor.changeControlMode(TalonControlMode.PercentVbus);
	}

	public TalonControlMode getControlMode()
	{
		return canMotor.getControlMode();
	}

	public void setControlMode(TalonControlMode mode)
	{
		canMotor.changeControlMode(mode);
	}

	
	/**
	 * In PercentVbus and Follower modes: returns current applied throttle.
	 */
	public double getSpeed()
	{
		return canMotor.get();
	}

	public void setSpeed(double speed)
	{
		if (device != null)
		{
			if (invertDirection)
			{
				canMotor.setSetpoint(-speed);
			} else
			{
				canMotor.setSetpoint(speed);
			}
		} else
		{
			if (invertDirection)
			{
				canMotor.set(-speed);
			} else
			{
				canMotor.set(speed);
			}
		}
	}

	public void stop()
	{
		if (device != null)
		{
			canMotor.setSetpoint(0.0);
		} else
		{
			canMotor.set(0.0);
		}
	}

	protected void setMotorControlMode(CANTalon.TalonControlMode mode)
	{
		canMotor.changeControlMode(mode);
	}

	protected int getDeviceID()
	{
		return canMotor.getDeviceID();
	}

	protected void setFollowerDeviceID(int deviceID)
	{
		canMotor.set(deviceID);
	}
}
