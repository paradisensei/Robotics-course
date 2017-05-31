package old;
import java.util.Arrays;

import lejos.hardware.Sound;
import lejos.utility.Delay;

public class Application {

	// field characteristics in cm.
	private static final double FIELD_WIDTH = 21;
	private static final double FIELD_LENGTH = 29.6;
	private static final double OFFSET = 0.5;
	private static final double ROBOT_OFFSET = 4.5;
	private static final double BOX_WIDTH = 3.7;
	private static final double BOX_HEIGHT = 1.2;
	
	// Manipulator characteristics in cm.
	private static final double L1 = 8;
	private static final double L2 = 7;
	private static final double D1 = 3;
	
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
		
//		Robot robot = new Robot();
//		
//		Matchbox m = field[0][0];
//		double theta1 = 43;
//		double theta2 = -60;
//		double theta3 = 120;
//		double[][] t = FK.getHomTrans(theta1, theta2, theta3);
//		m.setR13(t[0][2]);
//		m.setR23(t[1][2]);
//		m.setR31(t[2][0]);
//		m.setR32(t[2][1]);
////		m.x = t[0][3];
////		m.y = t[1][3];
////		m.z = t[2][3];
//		
//		double[] theta = IK.solve(m);
//		
////		robot.move(theta[0], theta[1], theta[2]);
////		robot.moveBack(-theta[0], -theta[1], -theta[2]);
//		
//		Matchbox m1 = field[0][1];
//		theta1 = -35;
//		theta2 = 22;
//		theta3 = 45;
//		t = FK.getHomTrans(theta1, theta2, theta3);
//		m1.setR13(t[0][2]);
//		m1.setR23(t[1][2]);
//		m1.setR31(t[2][0]);
//		m1.setR32(t[2][1]);
////		m1.x = t[0][3];
////		m1.y = t[1][3];
////		m1.z = t[2][3];
//		
//		theta = IK.solve(m1);
//		
//		robot.move(theta[0], theta[1], theta[2]);
//		robot.moveBack(-theta[0], -theta[1], -theta[2]);
//		
//		Matchbox m2 = field[2][0];
//		theta1 = 85;
//		theta2 = -40;
//		theta3 = 120;
//		t = FK.getHomTrans(theta1, theta2, theta3);
//		m.setR13(t[0][2]);
//		m.setR23(t[1][2]);
//		m.setR31(t[2][0]);
//		m.setR32(t[2][1]);
//		theta = IK.solve(m);
//		
////		robot.move(theta[0], theta[1], theta[2]);
////		robot.moveBack(-theta[0], -theta[1], -theta[2]);
		
		
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
		
		// iterate through matchboxes from one side
		for (j = 0; j < size - 1; j++) {
			for (int i = 0; i < size; i++) {
				
				Matchbox m = field[i][j];
				
				 find joint angles
				double s1 = -m.getR13();
				double c1 = m.getR23();
				double theta1 = Math.toDegrees(Math.atan2(s1, c1)) * -3.27;
				double s2 = -m.getZ() / L1;
				double c2 = (m.getY() - c1 * D1) / (s1 * L1);
				double theta2 = Math.toDegrees(Math.atan2(s2, c2)) * 0.88;
				double c3 = -((s2 * (m.getR31() + m.getZ() * L2)) / (s2 * s2 + c2 * m.getR32() - c2 * c2));
				double s3 = (m.getR32() - c2 * c3) / s2;
				double theta3 = Math.toDegrees(Math.atan2(s3, c3)) * 0.88;
				
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
//		for (int i = 0; i < size; i++) {
//			System.out.println(Arrays.toString(ans[i]));
//		}
		
		robot.stop();
	}

}