package be.ccfun.day9;

import java.util.Arrays;
import java.util.List;

public class Tile {
	private int[] position;
	private boolean visited = false;

	public Tile(int row, int col) {
		position = new int[] {row, col};
	}

	public Tile(int[] position) {
		this.position = position;
	}

	public Tile move(Direction direction, List<Tile> all) {
		int[] move = direction.move(this.position);
		Tile nextTile = new Tile(move);
		Tile finalNextTile = nextTile;
		Tile existing = all.stream().filter(t -> t.equals(finalNextTile)).findFirst().orElse(null);
		if (existing != null) {
			nextTile = existing;
		} else {
			all.add(nextTile);
		}
		return nextTile;
	}

	public Tile move(int[] delta, List<Tile> all) {
		Tile nextTile = new Tile(position[0] + delta[0], position[1]+ delta[1]);
		Tile finalNextTile = nextTile;
		Tile existing = all.stream().filter(t -> t.equals(finalNextTile)).findFirst().orElse(null);
		if (existing != null) {
			nextTile = existing;
		} else {
			all.add(nextTile);
		}
		return nextTile;
	}

	public boolean touch(Tile otherTile) {
		return Math.abs(position[0] - otherTile.position[0]) <= 1 && Math.abs(position[1] - otherTile.position[1]) <= 1;
	}

	public void visit() {
		System.out.println("Tail visits " + position[0] + ", " + position[1]);
		visited = true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Tile tile = (Tile) o;

		return Arrays.equals(position, tile.position);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(position);
	}

	public boolean isVisited() {
		return visited;
	}

	public int getRow() {
		return position[0];
	}

	public int getCol() {
		return position[1];
	}

	@Override
	public String toString() {
		return "Tile{" +
				"position=" + Arrays.toString(position) +
				", visited=" + visited +
				'}';
	}
}
