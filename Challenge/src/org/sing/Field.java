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
		
		field[0][0] = new int[] {20, -66, 142};
		field[0][1] = new int[] {-60, -73, 180};
		field[0][2] = new int[] {-90, -60, 160};
		field[1][0] = new int[] {3, -11, 54};
		field[1][1] = new int[] {-26, -15, 66};
		field[1][2] = new int[] {-46, -8, 57};
				
		// after relocation. joint values are mirrored.
		field[2][0] = field[0][2];
		field[2][1] = field[0][1];
		field[2][2] = field[0][0];
		
		return field;
	}
	
	private static int[][][] get4x4() {
		int[][][] field = new int[4][4][3];
		
		field[0][0] = new int[] {25, -75, 145};
		field[0][1] = new int[] {-20, -80, 175};
		field[0][2] = new int[] {-70, -80, 180};
		field[0][3] = new int[] {-90, -60, 160};
		
		field[1][0] = new int[] {3, -50, 90};
		field[1][1] = new int[] {-20, -105, 115};
		field[1][2] = new int[] {-35, -130, 100};
		field[1][3] = new int[] {-55, -130, 100};
		
		//after relocation, joint values are mirrored
		field[2][0] = field[1][3];
		field[2][1] = field[1][2];
		field[2][2] = field[1][1];
		field[2][3] = field[1][0];
		
		field[3][0] = field[0][3];
		field[3][1] = field[0][2];
		field[3][2] = field[0][1];
		field[3][3] = field[0][0];
		
		return field;
	}
	
	private static int[][][] get5x5() {
		// hardware testing required.
		return null;
	}
	
}
