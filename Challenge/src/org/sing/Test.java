package org.sing;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		// for [0][0]: 22, -74, 153
		// for [0][1]: -75, -70, 180
		// for [0][2]: -90, -70, 160
		// for [1][0]: 10, -25, 55
		// for [1][1]: -21, -25, 65
		// for [1][2]: -50, -25, 55
		Robot r = new Robot();
		
		double[][] t = FK.getTransform(new int[]{-50, -25, 55});
		double[] jointAngles = IK.solve(new Matchbox(t));
		System.out.println(Arrays.toString(jointAngles));
		
		r.move(jointAngles[0], jointAngles[1], jointAngles[2]);
		r.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
		
		r.stop();
	}
}
