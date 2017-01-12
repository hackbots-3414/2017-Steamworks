package org.usfirst.frc.team3414.robot.sensors;

public interface IJoystick {
	public double getX();

	public double getY();

	public double getTwist();

	public double getSwitch();

	public boolean getButtonValue(EButtonJoystick value);
}
