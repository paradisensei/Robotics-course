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
		
		MatchboxColor[][] ans = new MatchboxColor[size][size];
		
		Robot robot = new Robot();
		
		for (int i = 0; i < size - 1; i++) {
			// relocation condition
			if ((size <= 4 && i == 2) || (size == 5 && i == 3)) {
				Delay.msDelay(25000);
			}
			
			for (int j = 0; j < size; j++) {
				double[][] t = FK.getTransform(field[i][j]);
				double[] jointAngles = IK.solve(new Matchbox(t));
				System.out.println(Arrays.toString(jointAngles));
				robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
				
				MatchboxColor c = robot.getColor();
				System.out.println(c);
				ans[size - 1 - i][j] = c;
				
				robot.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
			}
		}
		
//		for (MatchboxColor[] c : ans) {
//			System.out.println(Arrays.toString(c));
//		}
		
		// finish execution
//		double[][] t = FK.getTransform(field[size - 1][0]);
//		double[] jointAngles = IK.solve(new Matchbox(t));
//		robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
//		Sound.beep();
//		Delay.msDelay(15000);
//		robot.stop();
	}
}
