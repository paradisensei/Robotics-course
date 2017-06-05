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
		field[0][0] = new int[] {22, -74, 153};
		field[0][1] = new int[] {-75, -70, 180};
		field[0][2] = new int[] {-90, -70, 160};
		field[1][0] = new int[] {10, -25, 55};
		field[1][1] = new int[] {-21, -25, 65};
		field[1][2] = new int[] {-50, -25, 55};
				
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
