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
		
		field[0][0] = new int[] {21, -72, 149};
		field[0][1] = new int[] {-26, -76, 177};
		field[0][2] = new int[] {-65, -76, 177};
		field[0][3] = new int[] {-75, -71, 158};
		
		field[1][0] = new int[] {12, -41, 90};
		field[1][1] = new int[] {-13, -45, 110};
		field[1][2] = new int[] {-32, -43, 109};
		field[1][3] = new int[] {-46, -40, 98};
		
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
		
		field[0][0] = new int[] {21, -71, 149};
		field[0][1] = new int[] {-26, -76, 177};
		field[0][2] = new int[] {-65, -76, 177};
		field[0][3] = new int[] {-78, -68, 157};
		
		field[1][0] = new int[] {13, -50, 114};
		field[1][1] = new int[] {-16, -52, 122};
		field[1][2] = new int[] {-38, -50, 120};
		field[1][3] = new int[] {-54, -50, 111};
		
		field[2][0] = new int[] {9, -25, 53};
		field[2][1] = new int[] {-14, -30, 65};
		field[2][2] = new int[] {-34, -30, 65};
		field[2][3] = new int[] {-42, -20, 58};
		
		//after relocation, joint values are mirrored
		field[3][0] = field[1][3];
		field[3][1] = field[1][2];
		field[3][2] = field[1][1];
		field[3][3] = field[1][0];
		
		field[4][0] = field[0][3];
		field[4][1] = field[0][2];
		field[4][2] = field[0][1];
		field[4][3] = field[0][0];
		
		return field;
	}
	
}
