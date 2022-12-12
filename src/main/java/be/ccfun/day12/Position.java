package be.ccfun.day12;

import java.util.List;

public class Position {
	private int row;
	private int col;
	private boolean visited = false;
	private char height;
	private int distance;

	public Position(int row, int col, char height) {
		this.row = row;
		this.col = col;
		this.height = height;
	}

	public Position(Position p) {
		this(p.row, p.col, p.height);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public List<Position> getAdjacent(List<Position> all) {
		return all.stream().filter(this::isAdjacent).filter(p -> p.getHeight() - this.getHeight() <= 1).toList();
	}

	public boolean isAdjacent(Position p) {
		if (this.equals(p)) {
			return false;
		}
		if (this.row == p.row) {
			return Math.abs(p.col - this.col) <= 1;
		} else if (this.col == p.col) {
			return Math.abs(p.row - this.row) <= 1;
		}
		return false;
	}

	public char getHeight() {
		if (height == 'S') {
			return 'a';
		} else if (height == 'E') {
			return 'z';
		}
		return height;
	}

	public boolean isDestination() {
		return height == 'E';
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDistance() {
		return distance;
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

		if (row != position.row) {
			return false;
		}
		return col == position.col;
	}

	@Override
	public int hashCode() {
		int result = row;
		result = 31 * result + col;
		return result;
	}

	@Override
	public String toString() {
		return "Position{" +
				"row=" + row +
				", col=" + col +
				", visited=" + visited +
				", height=" + height +
				'}';
	}

}
