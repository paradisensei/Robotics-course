package org.sing;

import lejos.hardware.Sound;
import lejos.utility.Delay;

/**
 * Assuming robot stands on the narrow side of the field.
 */

public class TaskB {
	public static void main(String[] args) {
		int size = 3;
		
		MatchboxColor inputColor = MatchboxColor.BLACK; // C - black
		int[][][] field = Field.getBySize(size);
		Robot robot = new Robot();
		
		// for all rows except the first one
		int previousColorCode = inputColor.getCode();
		
		// traverse rows
		for(int i = 0; i < size; i++) {
			// relocation condition
			if ((size == 3 && i == 2) || (size == 4 && i == 2) || (size == 5 && i == 3)) {
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
