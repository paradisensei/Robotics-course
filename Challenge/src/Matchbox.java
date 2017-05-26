import java.util.Arrays;

public class Matchbox {
	
	private final int[] position;
	private double[][] orientation;
	
	public Matchbox(int[] position) {
		this.position = position;
		orientation = new double[3][3];
	}
	
	public int[] getPosition() {
		return position;
	}
	
	public double[][] getOrientation() {
		return orientation;
	}
	
	public void setOrientation(double[][] orientation) {
		this.orientation = orientation;
	}
	
	public String toString() {
		return Arrays.toString(position);
	}
	
}
