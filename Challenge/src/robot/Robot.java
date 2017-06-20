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
		} else if(size == 4) {
			if(i == 0 && j <= 1 || i == 3 && j >= 2) {
				int theta2;
				if(j == 0) {
					theta2 = 20;
				} else {
					theta2 = 30;
				}
				
				// get ready
				rotate(j3, -50 * J3_ERR_COEF);
				rotate(j2, -theta2);
				
				// drop
				rotate(j3, 50 * J3_ERR_COEF);
				
				// move back
				rotate(j2, theta2);
			} else if(i == 0 && j == 2 || i == 3 && j == 1) {
				// get ready
				rotate(j1, 40 * J1_ERR_COEF);
				rotate(j3, -55 * J3_ERR_COEF);
				rotate(j2, 40 * J2_ERR_COEF);
				
				// drop
				rotate(j1, -30 * J1_ERR_COEF);
				rotate(j3, 40 * J3_ERR_COEF);
				
				// move back
				rotate(j2, -40 * J2_ERR_COEF);
				rotate(j1, -10 * J1_ERR_COEF);
				rotate(j3, 15 * J3_ERR_COEF);
			} else if(i == 0 && j == 3 || i == 3 && j == 0) {
				// get ready
				rotate(j1, 30 * J1_ERR_COEF);
				rotate(j3, -35 * J3_ERR_COEF);
				rotate(j2, 30 * J2_ERR_COEF);
				
				// drop
				rotate(j1, -40 * J1_ERR_COEF);
				
				// move back
				rotate(j2, -30 * J2_ERR_COEF);
				rotate(j1, 10 * J1_ERR_COEF);
				rotate(j3, 35 * J3_ERR_COEF);
			} else {
				// get ready
				rotate(j2, 20 * J2_ERR_COEF);
				
				// drop
				rotate(j3, -70 * J3_ERR_COEF);
				rotate(j2, -20 * J2_ERR_COEF);
				
				// move back				
				rotate(j3, 70 * J3_ERR_COEF);
			}
		} else {
			if(i == 0 && j <= 1 || i == 4 && j >= 2) {
				int theta2;
				if(j == 0) {
					theta2 = 20;
				} else {
					theta2 = 30;
				}
				
				// get ready
				rotate(j3, -35 * J3_ERR_COEF);
				rotate(j2, -theta2);
				
				// drop
				rotate(j3, 35 * J3_ERR_COEF);
				
				// move back
				rotate(j2, theta2);
			} else if(i == 0 && j == 2 || i == 4 && j == 1) {
				// get ready
				rotate(j1, 40 * J1_ERR_COEF);
				rotate(j3, -35 * J3_ERR_COEF);
				rotate(j2, 35 * J2_ERR_COEF);
				
				// drop
				rotate(j1, -30 * J1_ERR_COEF);
				rotate(j3, 20 * J3_ERR_COEF);
				
				// move back
				rotate(j2, -35 * J2_ERR_COEF);
				rotate(j1, -10 * J1_ERR_COEF);
				rotate(j3, 15 * J3_ERR_COEF);
			} else if(i == 0 && j == 3 || i == 4 && j == 0) {
				// get ready
				rotate(j1, 30 * J1_ERR_COEF);
				rotate(j3, -35 * J3_ERR_COEF);
				rotate(j2, 30 * J2_ERR_COEF);
				
				// drop
				rotate(j1, -40 * J1_ERR_COEF);
				
				// move back
				rotate(j2, -30 * J2_ERR_COEF);
				rotate(j1, 10 * J1_ERR_COEF);
				rotate(j3, 35 * J3_ERR_COEF);
			} else if(i == 1 || i == 3) {
				if(i == 1 && j == 3 || i == 3 && j == 0) {
					rotate(j2, -10 * J2_ERR_COEF);
					rotate(j3, 20 * J3_ERR_COEF);
					rotate(j2, 20 * J2_ERR_COEF);
					rotate(j3, -30 * J3_ERR_COEF);
					
					rotate(j2, -10 * J2_ERR_COEF);
					rotate(j3, 10 * J3_ERR_COEF);
				} else {
					// get ready
					rotate(j2, 10 * J2_ERR_COEF);
					
					// drop
					rotate(j3, -20 * J3_ERR_COEF);
					rotate(j2, -10 * J2_ERR_COEF);
					
					// move back				
					rotate(j3, 20 * J3_ERR_COEF);
				}
			} else {
				
			}
		}
	}
	
	private void rotate(RegulatedMotor j, double theta) {
		j.rotate((int) Math.round(theta));
	}
	
	public MatchboxColor getColor() {
		// get rgb
		float[] rgb = new float[3];
		colorSensor.getRGBMode().fetchSample(rgb, 0);
		System.out.print("[");
		for (float c : rgb) {
			System.out.printf("%.3f", c);
			System.out.printf(" ");
		}
		System.out.println("]");
		
		// adjust to the light
		if (rgb[0] < 0.017 && rgb[1] < 0.017 && rgb[2] < 0.017) {
			return MatchboxColor.BLACK;
		}
		
		// adjust to the light
		if (rgb[0] > 0.045 && rgb[1] > 0.045 && rgb[2] > 0.045) {
			return MatchboxColor.WHITE;
		}
		
		if (rgb[0] > rgb[2] && rgb[1] > rgb[2]) {
			float div1 = rgb[0] / rgb[1];
			if (div1 >= 1.65 && div1 <= 2.5) {
				return MatchboxColor.BROWN;
			}
			float max = Math.max(rgb[0], rgb[1]);
			float min = Math.min(rgb[0], rgb[1]);
			float div2 = max / min;
			if (div2 >= 1 && div2 <= 1.65) {
				return MatchboxColor.YELLOW;
			}
//			if (div2 >= 1 && div2 <= 2) {
//				return MatchboxColor.YELLOW;
//			}
		}
		
		return getRGBColor();
	}
	
	private MatchboxColor getRGBColor() {
		float[] rgb = new float[3];
		colorSensor.getRGBMode().fetchSample(rgb, 0);
		
		byte maxColorIndex = 0;
		for (byte i = 1; i < 3; i++) {
			if (rgb[i] > rgb[maxColorIndex]) {
				maxColorIndex = i;
			}
		}
		
		switch (maxColorIndex) {
			case 0:
				return MatchboxColor.RED;
			case 1:
				return MatchboxColor.GREEN;
			default:
				return MatchboxColor.BLUE;
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
