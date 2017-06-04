package org.sing;

import java.util.Arrays;

import lejos.hardware.Sound;
import lejos.utility.Delay;

/**
 * Assuming robot stands on the narrow side of the field.
 */

public class TaskB {
	public static void main(String[] args) {
		int size = 3;
		MatchboxColor inputColor = MatchboxColor.BLACK; // C - black
		
		// TODO: separate fields of different sizes to a separate file.
		int[][][] field = new int[size][size][size];
		
		// values are approximate. Hardware testing required.
		field[0][0] = new int[] {25, -35, 93};
		field[0][1] = new int[] {0, -60, 172};
		field[0][2] = new int[] {-45, -35, 93};
		field[1][0] = new int[] {15, -35, 105};
		field[1][1] = new int[] {0, -40, 100};
		field[1][2] = new int[] {-35, -30, 80};
		
		// after relocation. joint values are mirrored.
		field[2][0] = field[0][2];
		field[2][1] = field[0][1];
		field[2][2] = field[0][0];
		
		Robot robot = new Robot();
		
		// for all rows except the first one
		int previousColorCode = inputColor.getCode();
		
		// traverse rows
		for(int i = 0; i < size; i++) {
			if ((size == 3 && i == 2) || (size == 4 && i == 2) || (size == 5 && i == 3)) {
				// waiting for relocation
				Delay.msDelay(15000);
			}
			
			if(i == 0) {
				// traverse first row
				for(int j = 0; j < size; j++) {
					double[][] t = FK.getTransform(field[i][j]);
					double[] jointAngles = IK.solve(new Matchbox(t));
					
					robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
					
					MatchboxColor color = robot.getColor();

					if(color == inputColor) {
						drop();
						robot.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
						break;
					}
					
					robot.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
				}
			} else {
				// traverse all other rows.
				int dropColorPosition = previousColorCode % size == 0 ? size : previousColorCode % size;
				
				double[][] t = FK.getTransform(field[i][dropColorPosition - 1]);
				double[] jointAngles = IK.solve(new Matchbox(t));
				
				robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
				
				MatchboxColor color = robot.getColor();
				
				if(color == inputColor)
					drop();

				previousColorCode = color.getCode();
				
				robot.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
			}
		}
	
		// finish execution
		double[][] t = FK.getTransform(field[size - 1][size - 1]);
		double[] jointAngles = IK.solve(new Matchbox(t));
		robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
		Sound.beep();
		Delay.msDelay(10000);
		robot.stop();
	}
	
	private static void drop() {
		// dropping procedure here...
	}
}
