package fiinal;
import java.util.Arrays;

public class Application {
	public static void main(String[] args) {
		double[][] leftTr = FK.getTransform(55, -40, 120);
		Matchbox leftMatchbox = new Matchbox(leftTr);
		
		double[] jointAngles = IK.solve(leftMatchbox);
		System.out.println(Arrays.toString(jointAngles));
		
		Robot robot = new Robot();
		robot.move(jointAngles[0] * -3.27, jointAngles[1] * -0.88, jointAngles[2] * 0.88);
		robot.moveBack(jointAngles[0] * -3.27, jointAngles[1] * -0.88, jointAngles[2] * 0.88);
		
		robot.stop();
	}
}
