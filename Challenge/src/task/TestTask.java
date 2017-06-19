package task;

import kinematics.FK;
import kinematics.IK;
import lejos.utility.Delay;
import robot.Robot;
import field.Field;
import field.Matchbox;
import field.MatchboxColor;

public class TestTask {
	
	private static final int SIZE = 5;
	
	public static void main(String[] args) {
		int[][][] field = Field.getBySize(SIZE);
		
		Robot robot = new Robot();
		
		for (int i = 2; i < SIZE - 2; i++) {
			// relocation condition
			if (i == 3) {
				Delay.msDelay(30000);
			}
			
			for (int j = 0; j < SIZE - 1; j++) {
				double[][] t = FK.getTransform(field[i][j]);
				double[] jointAngles = IK.solve(new Matchbox(t));
				
				robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
				
				MatchboxColor c = robot.getColor();
				System.out.println(c);
				System.out.println("------------");
				
				robot.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
			}
		}
	}
	
}
