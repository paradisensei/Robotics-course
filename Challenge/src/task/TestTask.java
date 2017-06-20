package task;

import kinematics.FK;
import kinematics.IK;
import lejos.utility.Delay;
import robot.Robot;
import field.Field;
import field.Matchbox;
import field.MatchboxColor;

public class TestTask {
	
	private static final int SIZE = 4;
	
	public static void main(String[] args) {
		int[][][] field = Field.getBySize(SIZE);
		
		Robot robot = new Robot();
		
		for (int i = 1; i < SIZE - 2; i++) {
			// relocation condition
			if (i == 2) {
				Delay.msDelay(30000);
			}
			
			for (int j = 0; j < SIZE; j++) {
				double[][] t = FK.getTransform(field[i][j]);
				double[] jointAngles = IK.solve(new Matchbox(t));
				
				robot.move(jointAngles[0], jointAngles[1], jointAngles[2]);
				
//				MatchboxColor c = robot.getColor();
//				System.out.println(c);
//				System.out.println("------------");
//				robot.dropBox(i, j, SIZE);
				
				robot.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
			}
		}
	}
	
}
