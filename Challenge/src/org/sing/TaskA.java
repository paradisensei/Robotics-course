package org.sing;

import java.util.Arrays;

import org.sing.MatchboxColor;

import lejos.hardware.Sound;

public class TaskA {
	public static void main(String[] args) {
		int size = 3;
		
		int[][][] field = new int[size][size][size];
		
		//TODO add 3-d column (inverse)
		field[0][0] = new int[] {45, -40, 100};
		field[0][1] = new int[] {35, -30, 70};
		field[1][0] = new int[] {-45, -70, 180};
		field[1][1] = new int[] {-25, -40, 120};
		field[2][0] = new int[] {-85, -40, 110};
		field[2][1] = new int[] {-65, 0, 35};
		
		MatchboxColor[][] ans = new MatchboxColor[size][size];
		
		// iterate through boxes on one side
		Robot robot = new Robot();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size - 1; j++) {
				
				double[][] t = FK.getTransform(field[i][j]);
				FK.printMatrix(t);
				
				double[] jointAngles = IK.solve(new Matchbox(t));
				System.out.println(Arrays.toString(jointAngles));
				
				robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
				robot.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
			}
		}
		
		Sound.beep();
		robot.stop();
	}
}
