package task;

import kinematics.FK;
import kinematics.IK;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import robot.Robot;
import field.Field;
import field.Matchbox;
import field.MatchboxColor;

public class TaskBGreen {
	
	private static final int SIZE = 3;
	
	public static void main(String[] args) {
		// choose C color
		MatchboxColor inputColor = MatchboxColor.YELLOW;
		int previousColorCode = inputColor.ordinal();
		
		int[][][] field = Field.getBySize(SIZE);
		
		Robot robot = new Robot();
		
		// traverse rows
		for(int i = 0; i < SIZE; i++) {
			
			// relocation condition
			if (i == 2) {
				Delay.msDelay(30000);
			}
			
			if (i == 0) {
				// traverse first row
				for (int j = 0; j < SIZE; j++) {
					double[][] t = FK.getTransform(field[i][j]);
					double[] jointAngles = IK.solve(new Matchbox(t));
					
					robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
					
					MatchboxColor color = robot.getColor();
					System.out.println(color);

					if (color == inputColor || j == 2) {
						robot.dropBox(i, j, SIZE);
						robot.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
						break;
					}
					
					robot.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
				}
			} else {
				// traverse all other rows
				int j = previousColorCode % SIZE == 0 ? SIZE : previousColorCode % SIZE;
				
				double[][] t = FK.getTransform(field[i][j - 1]);
				double[] jointAngles = IK.solve(new Matchbox(t));
				
				robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
				
				MatchboxColor color = robot.getColor();
				System.out.println(color);
				
				if (color == inputColor) {
					robot.dropBox(i, j - 1, SIZE);
				}

				previousColorCode = color.ordinal();
				
				robot.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
			}
		}
	
		// finish execution
		double[][] t = FK.getTransform(field[SIZE - 1][SIZE - 1]);
		double[] jointAngles = IK.solve(new Matchbox(t));
		robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
		Sound.beep();
		robot.stop();
	}
	
}
