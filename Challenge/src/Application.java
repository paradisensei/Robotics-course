import java.util.Arrays;

import lejos.hardware.Sound;
import lejos.utility.Delay;

public class Application {

	// field characteristics in cm.
	private static final double FIELD_WIDTH = 21;
	private static final double FIELD_LENGTH = 29.6;
	private static final double OFFSET = 0.5;
	private static final double ROBOT_OFFSET = 4.5;
	private static final double BOX_WIDTH = 3.5;
	private static final double BOX_HEIGHT = 1.5;
	
	// Manipulator characteristics in cm.
	private static final double L1 = 9;
	private static final double L2 = 9.5;
	private static final double D1 = 5;
	
	public static void main(String[] args) {
		int size = 3;
		Matchbox[][] field = new Matchbox[size][size];
		
		double colInterval = (FIELD_WIDTH - 2 * OFFSET - size * BOX_WIDTH) / (size - 1);
		double rowInterval = (FIELD_LENGTH - 2 * OFFSET - size * BOX_HEIGHT) / (size - 1);
		
		// calculate positions of matchboxes
		for (int j = 0; j < size; j++) {
			final double posX = ROBOT_OFFSET + OFFSET + (j % 2) * (colInterval + BOX_WIDTH) + BOX_WIDTH / 2;
			for (int i = 0; i < size; i++) {
				final double posY = FIELD_LENGTH / 2 - (OFFSET + i * (rowInterval + BOX_HEIGHT) + BOX_HEIGHT / 2);
				field[i][j] = new Matchbox(posX, posY, 0);
			}
		}
		
		// calculate necessary orientations of matchboxes
		double r13 = -1 / (double) 2;
		double r23 = Math.sqrt(3) / 2;
		field[1][0].setR13(r13);
		field[1][0].setR23(r23);
		field[1][1].setR13(r13 * 0.8);
		field[1][1].setR23(r23 * 0.8);
		
		// set theta3
		for (int j = 0; j < size - 1; j++) {
			for (int i = 0; i < size; i++) {
				field[i][j].setR31(-Math.sqrt(3) / 2);
				field[i][j].setR32(-Math.sqrt(2) / 2);
			}
		}
		
		//TODO calculate on robot
		r13 = -Math.sqrt(2) / 2;
		r23 = Math.sqrt(2) / 2;
		int j = 0;
		for (int i = 0; i < size; i += 2) {
			field[i][j].setR13(i == 0 ? -r13 : r13);
//			field[i][j + 2].setR13(i == 0 ? r13 : -r13);
			field[i][j].setR23(r23);
//			field[i][j + 2].setR23(r23);
		}
		
		//TODO calculate on robot
		j++;
		r13 *= 0.8;
		r23 *= 0.8;
		for (int i = 0; i < size; i += 2) {
			field[i][j].setR13(i == 0 ? -r13 : r13);
			field[i][j].setR23(r23);
		}
		
		// check field
		for (int i = 0; i < size; i++) {
			System.out.println(Arrays.toString(field[i]));
		}
		
		// 3x3 matrix with matchbox colors
		MatchboxColor[][] ans = new MatchboxColor[size][size];
		
		// IK
		Robot robot = new Robot();
		
		// iterate through matchboxes from one side
		for (j = 0; j < size - 1; j++) {
			for (int i = 0; i < size; i++) {
				
				Matchbox m = field[i][j];
				
				// find joint angles
				double s1 = -m.getR13();
				double c1 = m.getR23();
				double theta1 = Math.toDegrees(Math.atan2(s1, c1)) * 3.6;
				double s2 = -m.getZ() / L1;
				double c2 = (m.getY() - c1 * D1) / (s1 * L1);
				double theta2 = Math.toDegrees(Math.atan2(s2, c2)) * 0.3;
				double c3 = -((s2 * (m.getR31() + m.getZ() * L2)) / (s2 * s2 + c2 * m.getR32() - c2 * c2));
				double s3 = (m.getR32() - c2 * c3) / s2;
				double theta3 = Math.toDegrees(Math.atan2(s3, c3));
				
				// move robot
				robot.move(theta1, theta2, theta3);
				robot.moveBack(-theta1, -theta2, -theta3);
				
				// get color and add to matrix
				ans[i][j] = robot.getColor();
			}
		}
		
		// time to relocate the robot
		Sound.beep();
//		Delay.msDelay(5000);
		
		// iterate through matchboxes from the other side
//		theta1 = 0;
//		theta2 = Math.toDegrees(-Math.PI / 2);
//		theta3 = 0;
		
//		for (int i = size - 1; i >= 0; i--) {
//			
//			Matchbox m = field[i][2];
//			
//			// find joint angles
//			double s1 = -m.getR13();
//			double c1 = m.getR23();
//			double theta1 = Math.toDegrees(Math.atan2(s1, c1)) * 3.6;
////			double s2 = -m.getZ() / L1;
////			double c2 = (-m.getY() - c1 * D1) / (s1 * L1);
////			double theta2 = theta2Init + Math.toDegrees(Math.atan2(s2, c2));
////			double s3 = 0;
////			double c3 = 0;
////			theta3 -= Math.toDegrees(Math.atan2(s3, c3)) - theta3;
//			
//			// move robot
//			robot.move(theta1, theta2, theta3);
//			robot.move(-theta1, -theta2, -theta3);
//			
//			// get color and add to matrix
//			ans[i][j] = robot.getColor();
//		}
		
		// check color matrix
		for (int i = 0; i < size; i++) {
			System.out.println(Arrays.toString(ans[i]));
		}
		
		robot.stop();
	}

}