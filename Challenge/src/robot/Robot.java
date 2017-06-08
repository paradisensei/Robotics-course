package robot;

import field.MatchboxColor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Robot {
	
	public static final double L1 = 8;
	public static final double L2 = 11;
	public static final double D1 = 3;
	
	private static final double J1_ERR_COEF = -3.33;
	private static final double J2_ERR_COEF = -0.88;
	private static final double J3_ERR_COEF = 0.92;
	
	private final RegulatedMotor j1;
	private final RegulatedMotor j2;
	private final RegulatedMotor j3;
	private final EV3ColorSensor colorSensor;
	
	public Robot() {
		j1 = getMotor(MotorPort.D, 50);
		j2 = getMotor(MotorPort.C, 50);
		j3 = getMotor(MotorPort.B, 50);
		colorSensor = new EV3ColorSensor(SensorPort.S4);
	}
	
	public void move(double theta1, double theta2, double theta3) {
		rotate(j1, theta1 * J1_ERR_COEF);
		rotate(j2, theta2 * J2_ERR_COEF);
		rotate(j3, theta3 * J3_ERR_COEF);
		Delay.msDelay(1000);
	}
	
	public void moveBack(double theta1, double theta2, double theta3) {
		rotate(j3, -theta3 * J3_ERR_COEF);
		rotate(j2, -theta2 * J2_ERR_COEF);
		rotate(j1, -theta1 * J1_ERR_COEF);
	}
	
	public void dropBox(int i, int j, int size) {
		if(size == 3) {
			if(i == 0 && j <= 1 || i == 2 && j >= 1) {
				// get ready
				rotate(j3, -50 * J3_ERR_COEF);
				rotate(j2, -30);
				
				// drop
				rotate(j3, 50 * J3_ERR_COEF);
				
				// move back
				rotate(j2, 30);
			} else if(i == 0 && j == 2 || i == 2 && j == 0) {
				// get ready
				rotate(j3, -50 * J3_ERR_COEF);
				rotate(j1, 20 * J1_ERR_COEF);
				rotate(j2, 30 * J2_ERR_COEF);
				
				// drop
				rotate(j1, -20 * J1_ERR_COEF);
				
				// move back
				rotate(j3, 50 * J3_ERR_COEF);
				rotate(j2, -30 * J2_ERR_COEF);
			} else {
				// get ready
				rotate(j2, 20 * J2_ERR_COEF);
				
				// drop
				rotate(j3, -70 * J3_ERR_COEF);
				rotate(j2, -20 * J2_ERR_COEF);
				
				// move back				
				rotate(j3, 70 * J3_ERR_COEF);
			}
		} else if(size == 4 || size == 5) {
			if(i == 0 && j <= 1 || i == 3 && j >= 2) {
				// get ready
				rotate(j3, -50 * J3_ERR_COEF);
				rotate(j2, -30);
				
				// drop
				rotate(j3, 50 * J3_ERR_COEF);
				
				// move back
				rotate(j2, 30);
			} else if(i == 0 && j == 2 || i == 3 && j == 1) {
				// get ready
				rotate(j1, 40 * J1_ERR_COEF);
				rotate(j3, -30 * J3_ERR_COEF);
				rotate(j2, 35 * J2_ERR_COEF);
				
				// drop
				rotate(j1, -45 * J1_ERR_COEF);
				
				// move back
				rotate(j2, -35 * J2_ERR_COEF);
				rotate(j1, 5 * J1_ERR_COEF);
				rotate(j3, 30 * J3_ERR_COEF);
			} else if(i == 0 && j == 3 || i == 3 && j == 0) {
				// get ready
				rotate(j1, 30 * J1_ERR_COEF);
				rotate(j3, -30 * J3_ERR_COEF);
				rotate(j2, 45 * J2_ERR_COEF);
				
				// drop
				rotate(j1, -40 * J1_ERR_COEF);
				
				// move back
				rotate(j2, -45 * J2_ERR_COEF);
				rotate(j1, 10 * J1_ERR_COEF);
				rotate(j3, 30 * J3_ERR_COEF);
			} else {
				// get ready
				rotate(j2, 20 * J2_ERR_COEF);
				
				// drop
				rotate(j3, -70 * J3_ERR_COEF);
				rotate(j2, -20 * J2_ERR_COEF);
				
				// move back				
				rotate(j3, 70 * J3_ERR_COEF);
			}
		}
	}
	
	private void rotate(RegulatedMotor j, double theta) {
		j.rotate((int) Math.round(theta));
	}
	
	public MatchboxColor getColor() {
		int colorID = colorSensor.getColorID();
		
		if (colorID == 6) {
			return MatchboxColor.WHITE;
		}
		
		float[] rgb = new float[3];
		colorSensor.getRGBMode().fetchSample(rgb, 0);
		
		byte maxColorIndex = 0;
		for (byte i = 1; i < 3; i++) {
			if (rgb[i] > rgb[maxColorIndex]) {
				maxColorIndex = i;
			}
		}
		
		if (rgb[0] < 0.01 && rgb[1] < 0.01 && rgb[2] < 0.01) {
			return MatchboxColor.BLACK;
		}
		
		if (rgb[0] > rgb[2] && rgb[1] > rgb[2]) {
			float max = Math.max(rgb[0], rgb[1]);
			float min = Math.min(rgb[0], rgb[1]);
			if (rgb[0] / rgb[1] >= 1.75) {
				return MatchboxColor.BROWN;
			} else if (max / min <= 1.6) {
				return MatchboxColor.YELLOW;
			}
		}
		
		switch (maxColorIndex) {
			case 0:
				return MatchboxColor.RED;
			case 1:
				return MatchboxColor.GREEN;
			case 2:
				return MatchboxColor.BLUE;
		}
		
		// default color
		return MatchboxColor.BLACK;
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
