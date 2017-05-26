import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;

public class Robot {
	
	private final RegulatedMotor j1;
	private final RegulatedMotor j2;
	private final RegulatedMotor j3;
	
	public Robot() {
		j1 = getMotor(MotorPort.D, 200);
		j2 = getMotor(MotorPort.C, 200);
		j3 = getMotor(MotorPort.B, 200);
	}
	
	public void theta1(double val) {
		j1.rotate((int) val);
	}
	
	public void theta2(double val) {
		j2.rotate((int) val);
	}
	
	public void stopJ1() {
		stopMotor(j1);
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
