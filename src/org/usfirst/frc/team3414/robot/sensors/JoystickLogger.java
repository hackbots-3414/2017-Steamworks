package org.usfirst.frc.team3414.robot.sensors;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import org.usfirst.frc.team3414.robot.sensors.EOutputDevice;
import org.usfirst.frc.team3414.robot.sensors.IGamepad;
import org.usfirst.frc.team3414.robot.sensors.IJoystick;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickLogger {

	private IJoystick joyLeft;
	private IJoystick joyRight;
	private IGamepad gamepad;
	private PrintWriter bw;

	public JoystickLogger(IJoystick joyLeft, IJoystick joyRight, IGamepad gamepad)
	{
		this.joyLeft = joyLeft;
		this.joyRight = joyRight;
		this.gamepad = gamepad;

		try
		{
			bw = new PrintWriter("joystickLog_" + getLoggerTimeStamp() + "_.txt", "UTF-8");
		} catch (FileNotFoundException e)
		{
			System.out.println("Write File Not Found");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e)
		{
			System.out.println("UTF-8 Not Found");
			e.printStackTrace();
		}

	}

	public void reportInformation(EOutputDevice device)
	{
		if (device == EOutputDevice.SMARTDASHBOARD)
		{
			SmartDashboard.putNumber("X Value Left", joyLeft.getX());
			SmartDashboard.putNumber("Y Value Left", joyLeft.getY());
			SmartDashboard.putNumber("Twist Value Left", joyLeft.getTwist());

			SmartDashboard.putNumber("X Value Right", joyRight.getX());
			SmartDashboard.putNumber("Y Value Right", joyRight.getY());
			SmartDashboard.putNumber("Twist Value Right", joyRight.getTwist());

		} else if (device == EOutputDevice.FILE)
		{

			bw.println("X Value Left " + joyLeft.getX());
			bw.println("X Value Right " + joyRight.getX());
			bw.println("Y Value Left " + joyLeft.getY());
			bw.println("Y Value Right " + joyRight.getY());
			bw.println("Twist Value Left " + joyLeft.getTwist());
			bw.write("Twist Value Right " + joyRight.getTwist());

		}
	}

	private String getLoggerTimeStamp()
	{
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int minute = Calendar.getInstance().get(Calendar.MINUTE);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return "_" + minute + "_:_" + hour + "_:_" + month + "_:_" + day + "_:_" + year + "_";
	}
}
