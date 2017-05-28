public class Matchbox {
	
	private final double x;
	private final double y;
	private final double z;
	// necessary elements from rotation matrix
	private double r13;
	private double r23;
	
	public Matchbox(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public double getR13() {
		return r13;
	}

	public void setR13(double r13) {
		this.r13 = r13;
	}
	
	public double getR23() {
		return r23;
	}
	
	public void setR23(double r23) {
		this.r23 = r23;
	}
	
	public String toString() {
		return "[" + x + " " + y + " " + z + " , r13 = " + r13 + " ; r23 = " + r23 + "]";
	}
	
}
