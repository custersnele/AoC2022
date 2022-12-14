package be.ccfun.day14;

public class Position {

	private int col;
	private int row;

	public Position(int col, int row) {
		this.col = col;
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Position position = (Position) o;

		if (col != position.col) {
			return false;
		}
		return row == position.row;
	}

	@Override
	public int hashCode() {
		int result = col;
		result = 31 * result + row;
		return result;
	}

	public Position down() {
		return new Position(col, row+1);
	}

	public Position leftDown() {
		return new Position(col-1, row+1);
	}

	public Position rightDown() {
		return new Position(col+1, row+1);
	}

	@Override
	public String toString() {
		return "Position{" +
				"col=" + col +
				", row=" + row +
				'}';
	}
}
