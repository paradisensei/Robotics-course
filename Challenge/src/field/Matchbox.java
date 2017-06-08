package field;

public class Matchbox {
	
	// position
	public final double x;
	public final double y;
	public final double z;

	// orientation
	private final double[][] transform;
	
	public Matchbox(double[][] transform) {
		this.transform = transform;
		this.x = transform[0][3];
		this.y = transform[1][3];
		this.z = transform[2][3];
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
	
	public double[][] getTransform() {
		return transform;
	}
	
	public String toString() {
		return "[" + x + " " + y + " " + z + "]";
	}
	
}
