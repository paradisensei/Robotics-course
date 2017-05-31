public class IK {
	
	// Manipulator characteristics in cm.
	private static final double L1 = 8;
	private static final double L2 = 7;
	private static final double D1 = 3;
	
	// TODO: add base offset along X.
	public static double[] solve(Matchbox m) {
		double s1 = -m.getR13();
		double c1 = m.getR23();
		double theta1 = Math.toDegrees(Math.atan2(s1, c1));
		
//		double s2 = -m.getZ() / L1;
//		double c2 = (m.getY() - c1 * D1) / (s1 * L1);
		double s2 = -(- L1*L2*m.getR31()*Math.pow(m.getY(), 2) + L1*L2*m.getY()*m.getZ()*m.getR21() + L1*L2*m.getR32()*s1*m.getY()*m.getZ() - L1*m.getR31()*m.getY()*m.getR21() - L1*m.getR32()*m.getY()*m.getR22() - L1*L2*s1*Math.pow(m.getZ(), 2)*m.getR22() + L1*m.getZ()*Math.pow(m.getR21(), 2) + L1*m.getR32()*s1*m.getZ()*m.getR21() + L1*m.getZ()*Math.pow(m.getR22(), 2) - L1*m.getR31()*s1*m.getZ()*m.getR22())/(Math.pow(L2, 2)*Math.pow(m.getY(), 2) + Math.pow(L2, 2)*Math.pow(m.getZ(),2)*Math.pow(s1,2) + 2*L2*m.getY()*m.getR21() + 2*L2*m.getY()*m.getR32()*s1 - 2*L2*m.getZ()*m.getR22()*s1 + 2*L2*m.getZ()*m.getR31()*Math.pow(s1, 2) + Math.pow(m.getR21(), 2) + 2*m.getR21()*m.getR32()*s1 + Math.pow(m.getR22(), 2) - 2*m.getR22()*m.getR31()*s1 + Math.pow(m.getR31(), 2)*Math.pow(s1, 2) + Math.pow(m.getR32(),2)*Math.pow(s1,2));
		double c2 = (L1*L2*Math.pow(m.getY(),2)*m.getR32() + L1*L2*s1*m.getY()*m.getZ()*m.getR31() - L1*L2*m.getR22()*m.getY()*m.getZ() + L1*s1*m.getY()*Math.pow(m.getR31(), 2) - L1*m.getR22()*m.getY()*m.getR31() + L1*s1*m.getY()*Math.pow(m.getR32(), 2) + L1*m.getR21()*m.getY()*m.getR32() - L1*L2*m.getR21()*s1*Math.pow(m.getZ(), 2) - L1*m.getR21()*s1*m.getZ()*m.getR31() - L1*m.getR22()*s1*m.getZ()*m.getR32())/(Math.pow(L2, 2)*Math.pow(m.getY(), 2) + Math.pow(L2, 2)*Math.pow(m.getZ(), 2)*Math.pow(s1, 2) + 2*L2*m.getY()*m.getR21() + 2*L2*m.getY()*m.getR32()*s1 - 2*L2*m.getZ()*m.getR22()*s1 + 2*L2*m.getZ()*m.getR31()*Math.pow(s1, 2) + Math.pow(m.getR21(), 2) + 2*m.getR21()*m.getR32()*s1 + Math.pow(m.getR22(), 2) - 2*m.getR22()*m.getR31()*s1 + Math.pow(m.getR31(), 2)*Math.pow(s1, 2) + Math.pow(m.getR32(), 2)*Math.pow(s1, 2));
		double theta2 = Math.toDegrees(Math.atan2(s2, c2));
				
//		double c3 = c2*(m.getR21() + m.getY()*L2) - s1*s2*(m.getR31() + m.getZ()*L2);
//		double s3 = -(s2*c3 + m.getR31() + m.getZ()*L2) / c2;
		double s3 = (L1*(m.getY()*m.getR22() + m.getZ()*m.getR32()*Math.pow(s1, 2) - m.getY()*m.getR31()*s1 + m.getZ()*m.getR21()*s1))/(Math.pow(L2, 2)*Math.pow(m.getY(), 2) + Math.pow(L2, 2)*Math.pow(m.getZ(), 2)*Math.pow(s1, 2) + 2*L2*m.getY()*m.getR21() + 2*L2*m.getY()*m.getR32()*s1 - 2*L2*m.getZ()*m.getR22()*s1 + 2*L2*m.getZ()*m.getR31()*Math.pow(s1, 2) + Math.pow(m.getR21(), 2) + 2*m.getR21()*m.getR32()*s1 + Math.pow(m.getR22(), 2) - 2*m.getR22()*m.getR31()*s1 + Math.pow(m.getR31(), 2)*Math.pow(s1, 2) + Math.pow(m.getR32(), 2)*Math.pow(s1,2));
		double c3 = -(L1*(L2*Math.pow(m.getY(), 2) + m.getR32()*m.getY()*s1 + m.getR21()*m.getY() + L2*Math.pow(m.getZ(), 2)*Math.pow(s1, 2) + m.getR31()*m.getZ()*Math.pow(s1, 2) - m.getR22()*m.getZ()*s1))/(Math.pow(L2, 2)*Math.pow(m.getY(), 2) + Math.pow(L2, 2)*Math.pow(m.getZ(), 2)*Math.pow(s1, 2) + 2*L2*m.getY()*m.getR21() + 2*L2*m.getY()*m.getR32()*s1 - 2*L2*m.getZ()*m.getR22()*s1 + 2*L2*m.getZ()*m.getR31()*Math.pow(s1, 2) + Math.pow(m.getR21(), 2) + 2*m.getR21()*m.getR32()*s1 + Math.pow(m.getR22(), 2) - 2*m.getR22()*m.getR31()*s1 + Math.pow(m.getR31(), 2)*Math.pow(s1, 2) + Math.pow(m.getR32(), 2)*Math.pow(s1,2));
		double theta3 = Math.toDegrees(Math.atan2(s3, c3));
		
		return new double[] {theta1, theta2, theta3};
	}
}
