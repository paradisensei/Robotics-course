package org.sing;

public class Test {
	public static void main(String[] args) {
		// for [0][0]: 45, -35, 93
		// for [1][0]: -55, -60, 172
		// for [2][0]: -90, -33, 100
		// for [0][1]: 30, 0, 25
		// for [1][1]: -25, -35, 105
		// for [2][1]: -65, 0, 35
		Robot r = new Robot();
		
		double theta1 = -65;
		double theta2 = 0;
		double theta3 = 35;
		
		r.move(theta1, theta2, theta3);
		r.moveBack(theta1, theta2, theta3);
		
		r.stop();
	}
}
