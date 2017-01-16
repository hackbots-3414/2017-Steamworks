package org.usfirst.frc.team3414.robot.teleop;

public class PIDOverride {
	private static PIDOverride singleton = null;

	private boolean isDisablePID = false;

	private PIDOverride()
	{

	}

	public static synchronized PIDOverride getInstance()
	{
		if (singleton == null)
		{
			singleton = new PIDOverride();
		}
		return singleton;
	}

	public void setTeleopDisablePID(boolean isDisablePID)
	{
		this.isDisablePID = isDisablePID;
	}

	public boolean isTeleopDisablePID()
	{
		return this.isDisablePID;
	}
}
