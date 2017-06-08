package field;

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
		
		field[0][0] = new int[] {17, -78, 153};
		field[0][1] = new int[] {-58, -78, 182};
		field[0][2] = new int[] {-80, -20, 155};
		field[1][0] = new int[] {5, -25, 55};
		field[1][1] = new int[] {-20, -27, 68};
		field[1][2] = new int[] {-42, -20, 58};
				
		// after relocation. joint values are mirrored.
		field[2][0] = field[0][2];
		field[2][1] = field[0][1];
		field[2][2] = field[0][0];
		
		return field;
	}
	
	private static int[][][] get4x4() {
		int[][][] field = new int[4][4][3];
		
		field[0][0] = new int[] {20, -72, 149};
		//TODO bad precision
		field[0][1] = new int[] {-26, -83, 173};
		field[0][2] = new int[] {-65, -76, 177};
		field[0][3] = new int[] {-80, -60, 160};
		
		field[1][0] = new int[] {11, -40, 90};
		field[1][1] = new int[] {-17, -47, 113};
		field[1][2] = new int[] {-34, -44, 113};
		field[1][3] = new int[] {-50, -40, 100};
		
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
		int[][][] field = new int[5][4][3];
		
		int[][][] field4x4 = get4x4();
		field[0] = field4x4[0];
		field[4] = field4x4[3];
		
		field[1][0] = new int[] {13, -56, 117};
		field[1][1] = new int[] {-20, -57, 117};
		field[1][2] = new int[] {-44, -51, 113};
		field[1][3] = new int[] {-54, -130, 103};
		
		field[2][0] = new int[] {5, -25, 55};
		field[2][1] = new int[] {-18, -45, 117};
		field[2][2] = new int[] {-38, -53, 111};
		field[2][3] = new int[] {-42, -20, 58};
		
		//after relocation, joint values are mirrored
		field[3][0] = field[1][3];
		field[3][1] = field[1][2];
		field[3][2] = field[1][1];
		field[3][3] = field[1][0];
		
		return field;
	}
	
}
