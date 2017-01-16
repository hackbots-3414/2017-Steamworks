package org.usfirst.frc.team3414.robot.sensors;

public class PowerDistributionPanel {

	private edu.wpi.first.wpilibj.PowerDistributionPanel pdp;

	public PowerDistributionPanel()
	{
		pdp = new edu.wpi.first.wpilibj.PowerDistributionPanel();
	}

	public double getVoltage()
	{
		return pdp.getVoltage();
	}

	public double getCurrent(int channel)
	{
		return pdp.getCurrent(channel);
	}

	public double getPower(int channel)
	{
		return pdp.getCurrent(channel) * pdp.getVoltage();
	}

	public double getTemperature()
	{
		return pdp.getTemperature();
	}

}
