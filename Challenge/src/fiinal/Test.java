package fiinal;

public class Test {
	public static void main(String[] args) {
		// for [0][0]: 45 -40 100
		// for [1][0]: -45 -70 180
		// for [2][0]: -85 -40 110
		// for [1][1]: -25 -40 120
		Robot r = new Robot();
		
		double errCoef1 = -3.27;
		double theta1 = -25 * errCoef1;
		
		double errCoef2 = -0.88;
		double theta2 = -40 * errCoef2;
		
		double errCoef3 = 0.88;
		double theta3 = 120 * errCoef3;
		
		r.move(theta1, theta2, theta3);
		r.moveBack(theta1, theta2, theta3);
		
		r.stop();
	}
}
