package org.sing;

public class Test {
	public static void main(String[] args) {
		// for [0][0]: 45, -40, 100
		// for [1][0]: -45, -70, 180
		// for [2][0]: -85, -40, 110
		// for [0][1]: 35, -30, 70
		// for [1][1]: -25, -40, 120
		// for [2][1]: -65, 0, 35
		Robot r = new Robot();
		
		double theta1 = -85;
		double theta2 = -40;
		double theta3 = 110;
		
		r.move(theta1, theta2, theta3);
		r.moveBack(theta1, theta2, theta3);
		
		r.stop();
	}
}
