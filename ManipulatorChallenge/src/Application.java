import java.util.Arrays;

public class Application {
	public static void main(String[] args) {
		// left matchbox
		double[][] leftTr = FK.getHomTrans(-30, -40, 120);
		Matchbox leftMatchbox = new Matchbox(leftTr[0][3], leftTr[1][3], leftTr[2][3]);
		leftMatchbox.setR13(leftTr[0][2]);
		leftMatchbox.setR21(leftTr[1][0]);
		leftMatchbox.setR22(leftTr[1][1]);
		leftMatchbox.setR23(leftTr[1][2]);
		leftMatchbox.setR31(leftTr[2][0]);
		leftMatchbox.setR32(leftTr[2][1]);
		
		double[] jointAngles = IK.solve(leftMatchbox);
		System.out.println(Arrays.toString(jointAngles));
		
//		Robot robot = new Robot();
//		robot.move(jointAngles[0] * -3.27, jointAngles[1] * -0.88, jointAngles[2] * 0.88);
//		
//		robot.stop();
	}
}
