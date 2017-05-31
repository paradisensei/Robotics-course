package fiinal;

public class IK {
		
	// TODO: add base offset along X.
	public static double[] solve(Matchbox m) {
		double[][] t = m.getTransform();
		double s1 = -t[0][2];
		double c1 = t[1][2];
		double theta1 = Math.toDegrees(Math.atan2(s1, c1));
		
		return new double[] {theta1, 0, 0};
	}
		
}
