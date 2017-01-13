package org.usfirst.frc.team3414.robot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotStatus {
	private static boolean isRunning = false;
	private static boolean isAuto = false;
	private static boolean isTeleop = false;
	private static boolean isAlpha = true;
	private static boolean alphaHasBeenChecked = false;

	public static boolean isRunning()
	{
		return isRunning;
	}

	public static boolean isAuto()
	{
		return isAuto;
	}

	public static boolean isTeleop()
	{
		return isTeleop;
	}

	public static boolean isAlpha()
	{
		return isAlpha;
	}

	protected static void setIsRunning(boolean isRunning)
	{
		RobotStatus.isRunning = isRunning;
	}

	protected static void setIsAuto(boolean isRunning)
	{
		RobotStatus.isAuto = isRunning;
	}

	protected static void setIsTeleop(boolean isRunning)
	{
		RobotStatus.isTeleop = isRunning;
	}

	protected static void checkIsAlpha()
	{
		if (!RobotStatus.alphaHasBeenChecked)
		{
			FileReader fileReader = null;
			try
			{
				fileReader = new FileReader("/home/lvuser/AlphaOrBeta.txt");
				// TODO: Make sure file exists at this exact path
			} catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}

			try
			{
				BufferedReader textReader = new BufferedReader(fileReader);

				String alphaOrBeta = textReader.readLine();

				if (alphaOrBeta.equals("alpha"))
				{
					isAlpha = true;
				} else if (alphaOrBeta.equals("beta"))
				{
					isAlpha = false;
				} else
				{
					System.err.println(
							"File is openable but doesn't specify alpha or beta on the first line, assuming alpha.");
				}
				SmartDashboard.putBoolean("isAlpha", isAlpha);
				textReader.close();

			} catch (Exception e)
			{
				System.err.println("Cannot determine if alpha or beta, assuming alpha");
				isAlpha = true;
			}
			RobotStatus.alphaHasBeenChecked = true;
		}

	}
}
