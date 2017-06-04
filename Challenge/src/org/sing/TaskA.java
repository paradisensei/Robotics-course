package org.sing;

import java.util.Arrays;

import org.sing.MatchboxColor;

import lejos.hardware.Sound;
import lejos.utility.Delay;

/**
 * Assuming robot stands on the narrow side of the field.
 */

public class TaskA {
	public static void main(String[] args) {
		int size = 3;
		
		int[][][] field = Field.getBySize(size);
		// joint values when robot stands on the wide side.
//		field[0][0] = new int[] {45, -35, 93};
//		field[0][1] = new int[] {30, 0, 25};
//		field[1][0] = new int[] {-55, -60, 172};
//		field[1][1] = new int[] {-25, -35, 105};
//		field[2][0] = new int[] {-90, -33, 100};
//		field[2][1] = new int[] {-65, 0, 35};
		
		MatchboxColor[][] ans = new MatchboxColor[size][size];
		
		Robot robot = new Robot();
		
		for (int i = 0; i < size; i++) {
			// relocation condition
			if ((size == 3 && i == 2) || (size == 4 && i == 2) || (size == 5 && i == 3)) {
				Delay.msDelay(15000);
			}
			
			for (int j = 0; j < size; j++) {
				
				double[][] t = FK.getTransform(field[i][j]);
				FK.printMatrix(t);
				
				double[] jointAngles = IK.solve(new Matchbox(t));
				System.out.println(Arrays.toString(jointAngles));
				
				robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
				ans[size - 1 - i][j] = robot.getColor();
				robot.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
			}
		}
		
		for (MatchboxColor[] c : ans) {
			System.out.println(Arrays.toString(c));
		}
		
		// finish execution
		double[][] t = FK.getTransform(field[size - 1][size - 1]);
		double[] jointAngles = IK.solve(new Matchbox(t));
		robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
		Sound.beep();
		Delay.msDelay(10000);
		robot.stop();
	}
}
