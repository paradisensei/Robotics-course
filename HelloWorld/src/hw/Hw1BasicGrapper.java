package hw;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;

public class Hw1BasicGrapper {

	public static void main(String[] args) {
		// initialize motors
		RegulatedMotor j1 = getMotor(MotorPort.C, 200);
		RegulatedMotor j2 = getMotor(MotorPort.B, 200);
		RegulatedMotor j3 = getMotor(MotorPort.A, 200);
		
		// grab smth
		j1.rotate(300);
		j2.rotate(250);
		j3.rotate(90);
		
		// come back and give object
		j2.rotate(-250);
		j1.rotate(-300);
		j3.rotate(-90);
		
		// stop motors
		stopMotor(j1);
		stopMotor(j2);
		stopMotor(j3);
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