package fiinal;

import static fiinal.Robot.L1;
import static fiinal.Robot.L2;

public class IK {
		
	// TODO: add base offset along X.
	public static double[] solve(Matchbox m) {
		double[][] t = m.getTransform();
		
		// solve for theta 1
		double s1 = -t[0][2];
		double c1 = t[1][2];
		double theta1 = Math.toDegrees(Math.atan2(s1, c1));
		
		// solve for theta 3
		double x = m.getX() / Math.cos(Math.toRadians(theta1));
		double y = m.getZ();
		double c2 = (x*x + y*y - L1*L1 - L2*L2) / (2*L1*L2);
		double s2 = Math.sqrt(1 - c2*c2);
		double theta3 = Math.toDegrees(Math.atan2(s2, c2));
		
		// solve for theta 2
		double phi = 180 - Math.acos(t[2][0]);
		double theta2 = Math.PI / 2 - (phi - theta3);
		
		return new double[] {theta1, theta2, theta3};
	}
		
}
