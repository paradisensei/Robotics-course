import java.util.Arrays;

import lejos.ev3.tools.LCDDisplay;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Application {

	// consts in cm.
	private static final int FIELD_WIDTH = 20;
	private static final int FIELD_HEIGHT = 40;
	private static final int OFFSET = 1;
	private static final int BOX_WIDTH = 3;
	private static final int BOX_HEIGHT = 1;
	
	public static void main(String[] args) throws Exception {
//		int size = Integer.valueOf(args[0]).intValue();
		int size = 3;
		System.out.println(size);
		Thread.sleep(2000);
		Matchbox[][] field = new Matchbox[size][size];
		int rowInterval = (FIELD_WIDTH - 2 * OFFSET - size * BOX_WIDTH) / (size - 1);
		int colInterval = (FIELD_HEIGHT - 2 * OFFSET - size * BOX_HEIGHT) / (size - 1);
		
		// build field
		int posZ = 3; // above matchbox
		for (int i = 0; i < size; i++) {
			final int posX = OFFSET + i * (rowInterval + BOX_WIDTH) + BOX_WIDTH / 2;
			for (int j = 0; j < size; j++) {
				final int posY = OFFSET + j * (colInterval + BOX_HEIGHT) + BOX_HEIGHT / 2;
				field[i][j] = new Matchbox(new int[] {posX, posY, posZ});
			}
		}
		for (int i = 0; i < size; i++) {
//			System.out.println(Arrays.toString(field[i]));
		}
		
		// IK
		Matchbox[] nearest = field[0];
		Matchbox m = nearest[1];
		System.out.println(m);
		Thread.sleep(2000);
		final double[][] o = new double[3][3];
		o[0][2] = - Math.sqrt(3) / 2;
		o[1][2] = - 1 / 2;
		m.setOrientation(o);
		double theta1 = Math.atan2(o[0][2], o[1][2]);
		System.out.println(theta1);
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.theta1(- theta1 * 57 * 3.5);
		Delay.msDelay(2000);
		int L1 = 9;
		int[] pos = m.getPosition();
		double c2 = pos[0] / (Math.cos(theta1) * L1);
		double s2 = pos[2] / L1;
		double theta2 = Math.atan2(c2, s2);
		System.out.println(theta2);
		Thread.sleep(2000);
		robot.theta2(- theta2 * 57 * 3.5);
	}

}