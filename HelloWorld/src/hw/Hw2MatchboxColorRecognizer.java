package hw;

import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;

public class Hw2MatchboxColorRecognizer {

	public static void main(String[] args) {
				RegulatedMotor j1 = getMotor(MotorPort.D, 200);
				RegulatedMotor j2 = getMotor(MotorPort.B, 300);
			    EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
			    
				for (int i = 0; i < 20; i++) {
					j1.rotate(-30);
					int count = 0;
					while (count++ < 9) {
						j2.rotate(30);
						int color = colorSensor.getColorID();
					    if ((color >= 0 && color < 3) || color == 7) {
					    	j2.rotate(30);
					    	Sound.beep();
					    	count++;
							break;
					    }
					}
					j2.rotate(count * -30);
				}
				j1.rotate(20 * 30);
				Sound.beep();
				stopMotor(j1);
				stopMotor(j2);
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
