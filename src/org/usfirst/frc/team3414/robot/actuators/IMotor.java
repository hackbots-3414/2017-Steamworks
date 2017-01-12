package org.usfirst.frc.team3414.robot.actuators;

public interface IMotor {

		public double getSpeed();

		public void setSpeed(double speed);

		public void stop();
		
		public void rampTo(double speed);
		
		public void rampDown();
		
		public void rampTo(double speed, int steps);

}
