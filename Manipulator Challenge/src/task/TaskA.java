package task;

import java.util.Arrays;

import kinematics.FK;
import kinematics.IK;
import robot.Robot;

import field.Field;
import field.Matchbox;
import field.MatchboxColor;
import lejos.hardware.Sound;
import lejos.utility.Delay;

/**
 * Assuming robot stands on the narrow side of the field.
 */
public class TaskA {
	
	private static final int SIZE = 3;
	
	public static void main(String[] args) {
		int[][][] field = Field.getBySize(SIZE);
		
		MatchboxColor[][] ans = new MatchboxColor[SIZE][SIZE];
		
		Robot robot = new Robot();
		
		for (int i = 0; i < SIZE; i++) {
			// relocation condition
			if (i == 2) {
				Delay.msDelay(30000);
			}
			
			for (int j = 0; j < SIZE; j++) {
				double[][] t = FK.getTransform(field[i][j]);
				double[] jointAngles = IK.solve(new Matchbox(t));
				
				robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
				
				MatchboxColor c = robot.getColor();
				System.out.println(c);
				ans[SIZE - 1 - i][j] = c;
				
				robot.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
			}
		}
		
		for (MatchboxColor[] c : ans) {
			System.out.println(Arrays.toString(c));
		}
		
		// finish execution
		double[][] t = FK.getTransform(field[SIZE - 1][SIZE - 1]);
		double[] jointAngles = IK.solve(new Matchbox(t));
		robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
		Sound.beep();
		Delay.msDelay(20000);
		robot.stop();
	}
	
}
