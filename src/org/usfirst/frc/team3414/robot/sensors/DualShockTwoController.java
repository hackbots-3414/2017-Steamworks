package org.usfirst.frc.team3414.robot.sensors;

import edu.wpi.first.wpilibj.Joystick;

public class DualShockTwoController implements IGamepad {

	private Joystick joy;
	private static final double TOLERANCE = 0.05;
	private static final int LEFT_HORIZONTAL = 1;
	private static final int LEFT_VERTICAL = 2;
	private static final int RIGHT_HORIZONTAL = 3;
	private static final int RIGHT_VERTICAL = 4;

	public DualShockTwoController(Joystick joy)
	{
		this.joy = joy;
	}

	@Override
	/**
	 * left horizontal 1; left vertical 2; right horizontal 3; right vertical 4
	 */
	public double getAnalogStickValue(EAnalogStick axis)
	{
		switch (axis)
		{
		case LEFT_HORIZONTAL:
			if (Math.abs(joy.getRawAxis(LEFT_HORIZONTAL)) < TOLERANCE)
			{
				return 0.0;
			} else
			{
				return joy.getRawAxis(LEFT_HORIZONTAL);
			}
		case LEFT_VERTICAL:
			if (Math.abs(joy.getRawAxis(LEFT_VERTICAL)) < TOLERANCE)
			{
				return 0.0;
			} else
			{
				return joy.getRawAxis(LEFT_VERTICAL);
			}
		case RIGHT_HORIZONTAL:
			if (Math.abs(joy.getRawAxis(RIGHT_HORIZONTAL)) < TOLERANCE)
			{
				return 0.0;
			} else
			{
				return joy.getRawAxis(RIGHT_HORIZONTAL);
			}
		case RIGHT_VERTICAL:
			if (Math.abs(joy.getRawAxis(RIGHT_VERTICAL)) < TOLERANCE)
			{
				return 0.0;
			} else
			{
				return joy.getRawAxis(RIGHT_VERTICAL);
			}
		default: 
			return 0.0;
		}
	}

	public boolean getButtonValue(EButtonJoystick button)
	{
		switch (button)
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
	
	
	/**
	 * Returns in Degrees
	 * NOTHING = -1
	 * UP = 0
	 * UP-RIGHT = 45
	 * RIGHT = 90
	 * DOWN = 180
	 * LEFT = 270
	 */
	public int getPOV()
	{
		return joy.getPOV();
	}
}
