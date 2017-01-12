package org.usfirst.frc.team3414.robot.PID;

	import edu.wpi.first.wpilibj.Talon;

	/**
	 * Describes a generic device that can use PID, like a drive train or a shooter
	 * mechanism.
	 */
	public interface IPIDEnabledDevice
	{
		public void setP(double p);

		public void setI(double i);

		public void setD(double d);

		public void setPIDFeedbackDevice(IPIDFeedbackDevice device);

		public IPIDFeedbackDevice getPIDFeedbackDevice();

		public void enablePID();

		public void disablePID();

	//	public Talon.TalonControlMode getControlMode();

	//	public void setControlMode(Talon.TalonControlMode mode);
	}
