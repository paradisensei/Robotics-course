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
		
		field[0][0] = new int[] {19, -77, 153};
		field[0][1] = new int[] {-55, -77, 182};
		field[0][2] = new int[] {-80, -20, 155};
		field[1][0] = new int[] {7, -26, 54};
		field[1][1] = new int[] {-20, -28, 67};
		field[1][2] = new int[] {-40, -21, 55};
				
		// after relocation. joint values are mirrored.
		field[2][0] = field[0][2];
		field[2][1] = field[0][1];
		field[2][2] = field[0][0];
		
		return field;
	}
	
	private static int[][][] get4x4() {
		int[][][] field = new int[4][4][3];
		
		field[0][0] = new int[] {21, -72, 150};
		field[0][1] = new int[] {-26, -75, 175};
		field[0][2] = new int[] {-65, -75, 177};
		field[0][3] = new int[] {-82, -54, 147};
		
		field[1][0] = new int[] {12, -46, 95};
		field[1][1] = new int[] {-13, -45, 110};
		field[1][2] = new int[] {-33, -43, 109};
		field[1][3] = new int[] {-48, -40, 98};
		
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
		
		field[0][0] = new int[] {21, -71, 150};
		field[0][1] = new int[] {-26, -76, 176};
		field[0][2] = new int[] {-65, -76, 177};
		field[0][3] = new int[] {-81, -67, 157};

		field[1][0] = new int[] {11, -53, 116};
		field[1][1] = new int[] {-17, -53, 126};
		field[1][2] = new int[] {-40, -52, 128};
		field[1][3] = new int[] {-54, -47, 111};
		
		field[2][0] = new int[] {4, -25, 53};
		field[2][1] = new int[] {-14, -28, 67};
		field[2][2] = new int[] {-31, -25, 69};
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
