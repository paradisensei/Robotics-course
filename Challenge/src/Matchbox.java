public class Matchbox {
	
	public double x;
	public double y;
	public double z;
	// necessary elements from rotation matrix
	private double r13;
	private double r23;
	private double r31;
	private double r32;
	
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
	
	public double getR31() {
		return r31;
	}
	
	public void setR31(double r31) {
		this.r31 = r31;
	}
	
	public double getR32() {
		return r32;
	}
	
	public void setR32(double r32) {
		this.r32 = r32;
	}
	
	public String toString() {
		return "[" + x + " " + y + " " + z + " , r13 = " + r13 + " ; r23 = " + r23 + "]";
	}
	
}
