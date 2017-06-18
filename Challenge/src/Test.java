import java.util.Arrays;

import field.Matchbox;
import robot.Robot;
import kinematics.FK;
import kinematics.IK;
import lejos.utility.Delay;

public class Test {
	public static void main(String[] args) {
		double[][] t = FK.getTransform(new int[]{-78, -68, 157});
		double[] jointAngles = IK.solve(new Matchbox(t));
		System.out.println(Arrays.toString(jointAngles));
		
//		Robot r = new Robot();
//		System.out.println(r.getColor());
//		
//		r.move(jointAngles[0], jointAngles[1], jointAngles[2]);
//		r.moveBack(jointAngles[0], jointAngles[1], jointAngles[2]);
//		
//		r.stop();
	}
}
