import java.util.Arrays;

public class FK {
 
 // Manipulator characteristics in cm.
 private static final double L1 = 8;
 private static final double L2 = 7;
 private static final double D1 = 3;
 
 public static void printMatrix(double[][] matrix) {
	 for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
	 }
 }
 
 public static double[][] getHomTrans(double theta1, double theta2, double theta3) {
  final double s1 = Math.sin(theta1);
  final double c1 = Math.cos(theta1);
  
  final double s2 = Math.sin(theta2);
  final double c2 = Math.cos(theta2);
  
  final double s3 = Math.sin(theta3);
  final double c3 = Math.cos(theta3);
  
  double[][] matrix = {
    {c1*c2*c3 - c1*s2*s3, -c1*c2*s3 - c1*s2*c3, -s1, L2*(c1*c2*c3 - c1*s2*s3) + c1*c2*L1 - s1*D1},
    {s1*c2*c3 - s1*s2*s3, -s1*c2*s3 - s1*s2*c3, c1, L2*(s1*c2*c3 - s1*s2*s3) + s1*c2*L1 + c1*D1},
    {-s2*c3 - c2*s3, s2*s3 - c2*c3, 0, L2*(-s2*c3 - c2*s3) - s2*L1},
    {0, 0, 0, 1}
   };
  
  return matrix;
 }
}