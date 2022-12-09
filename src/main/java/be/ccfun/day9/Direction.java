package be.ccfun.day9;

public enum Direction {
	R,
	U,
	L,
	D;

	public Direction getOpponent() {
		switch (this) {
			case R -> {return L;}
			case U -> {return D;}
			case L -> {return R;}
			case D -> {return U;}
		}
		return null;
	}

	public int[] move(int[] position) {
		int row = position[0];
		int col = position[1];
		switch (this) {
			case R -> {return new int[] {row, col+1};}
			case U -> {return new int[] {row-1, col};}
			case L -> {return new int[] {row, col-1};}
			case D -> {return new int[] {row+1, col};}
		}
		return null;
	}


}
