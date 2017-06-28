package kinematics;

import static robot.Robot.L1;
import static robot.Robot.L2;
import static robot.Robot.D1;

public class FK {
	 
	public static double[][] getTransform(int[] theta) {
		final double theta1 = Math.toRadians(theta[0]);
		final double s1 = Math.sin(theta1);
		final double c1 = Math.cos(theta1);
		  
		final double theta2 = Math.toRadians(theta[1]);
		final double s2 = Math.sin(theta2);
		final double c2 = Math.cos(theta2);
		  
		final double theta3 = Math.toRadians(theta[2]);
		final double s3 = Math.sin(theta3);
		final double c3 = Math.cos(theta3);
		
		return new double[][] {
		    {c1*c2*c3 - c1*s2*s3, -c1*c2*s3 - c1*s2*c3, -s1, L2*(c1*c2*c3 - c1*s2*s3) + c1*c2*L1 - s1*D1},
		    {s1*c2*c3 - s1*s2*s3, -s1*c2*s3 - s1*s2*c3, c1, L2*(s1*c2*c3 - s1*s2*s3) + s1*c2*L1 + c1*D1},
		    {-s2*c3 - c2*s3, s2*s3 - c2*c3, 0, L2*(-s2*c3 - c2*s3) - s2*L1},
		    {0, 0, 0, 1}
		};
	}
	 
}