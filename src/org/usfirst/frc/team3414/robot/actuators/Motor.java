package org.usfirst.frc.team3414.robot.actuators;

import org.usfirst.frc.team3414.robot.PID.IPIDFeedbackDevice;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Motor implements IMotor{

/**
 * A generic motor class that does not use the CAN interface. This class would
 * be suitable to control the Jaguar, Victor, Talon and Talon SR motor
 * controllers.
 *
 */

	// private double p, i, d;
	private IPIDFeedbackDevice device;
	private SpeedController motor;
	private boolean isInverted;

	public Motor(SpeedController motor, boolean invertDirection)
	{
		this.motor = motor;
		// this.motor.setInverted(invertDirection);
		this.isInverted = invertDirection;
	}

	public Motor(SpeedController motor, boolean invertDirection, IPIDFeedbackDevice device)
	{
		this(motor, invertDirection);
		this.device = device;
	}

	@Override
	/**
	 * Returns the current applied throttle.
	 */
	public double getSpeed()
	{
		if (device == null)
		{
			if(isInverted)
			{
				return -motor.get();
			} else
			{
				return motor.get();
			}
		} else
		{
			if(isInverted)
			{
				return -device.getRate();
			} else
			{
				return device.getRate();
			}
		}
	}

	@Override
	public void setSpeed(double speed)
	{
		if (Thread.interrupted())
		{
			stop();
			return;
		}
		
		if (device == null)
		{
			if (isInverted)
			{
				motor.set(-speed);
			} else
			{
				motor.set(speed);
			}
		} else
		{
			// implement later
		}
	}

	@Override
	public void stop()
	{
		if (device == null)
		{
			motor.set(0.0);
		} else
		{
			// implement later
		}
	}

	@Override
	public void rampTo(double speed)
	{
		if (getSpeed() != speed)
		{
			double rampSpeed, initialSpeed = 0;

			rampSpeed = getSpeed();
			initialSpeed = rampSpeed;

			for (int i = 0; i < 100; i++)
			{
				rampSpeed += (speed - initialSpeed) / 100;

				setSpeed(speed);

				try
				{
					Thread.sleep(2); // wait at least 2 milliseconds
				} catch (InterruptedException e)
				{
					stop();
					return;
				}
			}
		} else
		{
			setSpeed(speed);
		}
	}

	@Override
	public void rampDown()
	{
		double rampSpeed, initialSpeed = 0;

		rampSpeed = getSpeed();
		initialSpeed = rampSpeed;

		for (int i = 0; i < 100; i++)
		{
			rampSpeed -= initialSpeed / 100;

			setSpeed(rampSpeed);

			try
			{
				Thread.sleep(2); // wait at least 2 milliseconds
			} catch (InterruptedException e)
			{

			}
		}
	}

	@Override
	public void rampTo(double speed, int steps)
	{
		if (getSpeed() != speed)
		{
			double rampSpeed, initialSpeed = 0;

			rampSpeed = getSpeed();
			initialSpeed = rampSpeed;

			for (int i = 0; i < 100; i++)
			{
				rampSpeed += (speed - initialSpeed) / 100;

				setSpeed(speed);

				try
				{
					Thread.sleep(2); // wait at least 2 milliseconds
				} catch (InterruptedException e)
				{
					stop();
					return;
				}
			}
		} else
		{
			setSpeed(speed);
		}
	}
}
