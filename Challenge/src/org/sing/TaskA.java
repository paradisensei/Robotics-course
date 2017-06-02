package org.sing;

import java.util.Arrays;

import org.sing.MatchboxColor;

import lejos.hardware.Sound;
import lejos.utility.Delay;

public class TaskA {
	public static void main(String[] args) {
		int size = 3;
		
		int[][][] field = new int[size][size][size];
		
		//TODO add 3-d column (inverse)
		field[0][0] = new int[] {45, -35, 93};
		field[0][1] = new int[] {30, 0, 25};
		field[1][0] = new int[] {-55, -60, 172};
		field[1][1] = new int[] {-25, -35, 105};
		field[2][0] = new int[] {-90, -33, 100};
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
				ans[i][j] = robot.getColor();
				robot.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
			}
		}
		for (MatchboxColor[] c : ans) {
			System.out.println(Arrays.toString(c));
		}
		Delay.msDelay(10000);
		Sound.beep();
		robot.stop();
	}
}
