package org.usfirst.frc.team3414.robot.sensors;


public interface IGamepad {
	public double getAnalogStickValue(EAnalogStick axis);

	public boolean getButtonValue(EButtonJoystick button);
	
	public int getPOV();
}
