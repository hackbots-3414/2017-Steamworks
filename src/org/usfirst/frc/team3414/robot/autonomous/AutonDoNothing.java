package org.usfirst.frc.team3414.robot.autonomous;

import org.usfirst.frc.team3414.robot.autonomous.EAutoPositions;

public class AutonDoNothing {
	public AutonDoNothing()
	{

	}

	public void doAuto(EAutoPositions position)
	{
		System.out.println("Didn't know how to do it -Raul");
		
		//ActuatorConfig.getInstance().getAutoShot().shoot(position); // TODO: Get shooting to work, do nothing doesn't actually do nothing
	}

}
