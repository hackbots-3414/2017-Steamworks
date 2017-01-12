package org.usfirst.frc.team3414.robot.sensors;

import edu.wpi.first.wpilibj.Joystick;

public class Logitech3DJoystick implements IJoystick{
	private Joystick joy;
	private static final int HORIZONTAL_AXIS = 0;
	private static final int VERTICAL_AXIS = 1;
	private static final int TWIST_AXIS = 2;
	private static final int SWITCH_AXIS = 3;
	private final static double TOLERANCE = 0.05;

	public Logitech3DJoystick(Joystick joy)
	{
		this.joy = joy;
	}

	@Override
	public double getX()
	{
		if (Math.abs(joy.getRawAxis(HORIZONTAL_AXIS)) < TOLERANCE)
		{
			return 0.0;
		} else
		{
			return joy.getRawAxis(HORIZONTAL_AXIS);
		}
	}

	@Override
	public double getY()
	{
		if (Math.abs(joy.getRawAxis(VERTICAL_AXIS)) < TOLERANCE)
		{
			return 0.0;
		} else
		{
			return joy.getRawAxis(VERTICAL_AXIS);
		}
	}

	@Override
	public double getTwist()
	{
		if (Math.abs(joy.getRawAxis(TWIST_AXIS)) < TOLERANCE)
		{
			return 0.0;
		} else
		{
			return joy.getRawAxis(TWIST_AXIS);
		}
	}

	@Override
	public double getSwitch()
	{
		if (Math.abs(joy.getRawAxis(SWITCH_AXIS)) < TOLERANCE)
		{
			return 0.0;
		} else
		{
			return joy.getRawAxis(SWITCH_AXIS);
		}
	}

	public boolean getButtonValue1(EButtonJoystick value)
	{
		switch (value)
		{
		case ONE:
			return joy.getRawButton(1);
		case TWO:
			return joy.getRawButton(2);
		case THREE:
			return joy.getRawButton(3);
		case FOUR:
			return joy.getRawButton(4);
		case FIVE:
			return joy.getRawButton(5);
		case SIX:
			return joy.getRawButton(6);
		case SEVEN:
			return joy.getRawButton(7);
		case EIGHT:
			return joy.getRawButton(8);
		case NINE:
			return joy.getRawButton(9);
		case TEN:
			return joy.getRawButton(10);
		case ELEVEN:
			return joy.getRawButton(11);
		case TWELVE:
			return joy.getRawButton(12);
		default:
			return false;
		}

	}

	@Override
	public boolean getButtonValue(EButtonJoystick value) {
		// TODO Auto-generated method stub
		return false;
	}
}
