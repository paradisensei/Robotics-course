package org.sing;

public class Field {
	public static int[][][] getBySize(int size) {
		switch(size) {
			case 3: return get3x3();
			case 4: return get4x4();
			case 5: return get5x5();
			default: return null;
		}
	}
	
	private static int[][][] get3x3() {
		int[][][] field = new int[3][3][3];
		
		// values are approximate. Hardware testing required.
		field[0][0] = new int[] {25, -35, 93};
		field[0][1] = new int[] {0, -60, 172};
		field[0][2] = new int[] {-45, -35, 93};
		field[1][0] = new int[] {15, -35, 105};
		field[1][1] = new int[] {0, -40, 100};
		field[1][2] = new int[] {-35, -30, 80};
				
		// after relocation. joint values are mirrored.
		field[2][0] = field[0][2];
		field[2][1] = field[0][1];
		field[2][2] = field[0][0];
		
		return field;
	}
	
	private static int[][][] get4x4() {
		// hardware testing required.
		return null;
	}
	
	private static int[][][] get5x5() {
		// hardware testing required.
		return null;
	}
}
