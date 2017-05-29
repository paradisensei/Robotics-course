import lejos.utility.Delay;

public class Test {

	public static void main(String[] args) {
		Robot r = new Robot();
		double errCoef1 = -3.27;
		double theta1 = -85 * errCoef1;
		double errCoef2 = -0.88;
		double theta2 = -40 * errCoef2;
		double errCoef3 = 0.88;
		double theta3 = 120 * errCoef3;
		r.move(theta1, theta2, theta3);
		r.moveBack(-theta1, -theta2, -theta3);
//		double[][] t = FK.getHomTrans(theta1, theta2, theta3);
//		FK.printMatrix(t);
		
	}

}