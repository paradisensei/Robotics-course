public class IK {
	
	// Manipulator characteristics in cm.
	private static final double L1 = 8;
	private static final double L2 = 7;
	private static final double D1 = 3;
	
	public static double[] solve(Matchbox m) {
		double s1 = -m.getR13();
		double c1 = m.getR23();
		double theta1 = Math.toDegrees(Math.atan2(s1, c1)) * 3.27;
		double s2 = -m.getZ() / L1;
		double c2 = (m.getY() - c1 * D1) / (s1 * L1);
		double theta2 = Math.toDegrees(Math.atan2(s2, c2)) * -0.2;
		double c3 = -((s2 * (m.getR31() + m.getZ() * L2)) / (s2 * s2 + c2 * m.getR32() - c2 * c2));
		double s3 = (m.getR32() - c2 * c3) / s2;
		double theta3 = Math.toDegrees(Math.atan2(s3, c3)) * -0.88;
		return new double[] {theta1, theta2, theta3};
	}
}
