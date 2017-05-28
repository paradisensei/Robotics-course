import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Robot {
	
	private final RegulatedMotor j1;
	private final RegulatedMotor j2;
	private final RegulatedMotor j3;
	private final EV3ColorSensor colorSensor;
	
	public Robot() {
		j1 = getMotor(MotorPort.D, 100);
		j2 = getMotor(MotorPort.C, 100);
		j3 = getMotor(MotorPort.B, 100);
		colorSensor = new EV3ColorSensor(SensorPort.S1);
	}
	
	public void move(double theta1, double theta2, double theta3) {
		j1.rotate((int) Math.round(theta1));
		Delay.msDelay(1000);
		j2.rotate((int) Math.round(theta2));
		Delay.msDelay(1000);
		j3.rotate((int) Math.round(theta3));
		Delay.msDelay(1000);
	}
	
	public void moveBack(double theta1, double theta2, double theta3) {
		j3.rotate((int) Math.round(theta3));
		Delay.msDelay(1000);
		j2.rotate((int) Math.round(theta2));
		Delay.msDelay(1000);
		j1.rotate((int) Math.round(theta1));
		Delay.msDelay(1000);
	}
	
	public MatchboxColor getColor() {
		int color = colorSensor.getColorID();
		switch (color) {
			case 7:
				return MatchboxColor.BLACK;
			case 2:
				return MatchboxColor.BLUE;
			case 1:
				return MatchboxColor.GREEN;
			case 3:
				return MatchboxColor.YELLOW;
			case 0:
				return MatchboxColor.RED;
			case 6:
				return MatchboxColor.WHITE;
			case 13:
				return MatchboxColor.BROWN;
			default:
				return MatchboxColor.NO;
		}
	}
	
	public void stop() {
		stopMotor(j1);
		stopMotor(j2);
		stopMotor(j3);
		colorSensor.close();
	}
	
	private static RegulatedMotor getMotor(Port p, int speed) {
		RegulatedMotor m = new EV3LargeRegulatedMotor(p);
		m.setSpeed(speed);
		return m;
	}
	
	private static void stopMotor(RegulatedMotor m) {
		m.stop();
		m.close();
	}
	
}
