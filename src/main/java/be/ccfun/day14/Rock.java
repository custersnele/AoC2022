package be.ccfun.day14;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rock {

	private Set<Position> allRocks = new HashSet<>();

	public Rock(String description) {
		String[] split = description.split(" -> ");
		for (int i = 0; i < split.length - 1; i++) {
			int[] from = Arrays.stream(split[i].split(",")).mapToInt(Integer::parseInt).toArray();
			int[] to = Arrays.stream(split[i + 1].split(",")).mapToInt(Integer::parseInt).toArray();
			for (int col = Math.min(from[0], to[0]); col <= Math.max(from[0], to[0]); col++) {
				for (int row = Math.min(from[1], to[1]); row <= Math.max(from[1], to[1]); row++) {
					allRocks.add(new Position(col, row));
				}
			}
		}
	}

	public boolean hasRock(Position position) {
		return allRocks.contains(position);
	}

	public Set<Position> all() {
		return allRocks;
	}

	public int getFromRow() {
		return allRocks.stream().mapToInt(t -> t.getRow()).min().getAsInt();
	}

	public int getToRow() {
		return allRocks.stream().mapToInt(t -> t.getRow()).max().getAsInt();
	}

	public int getFromCol() {
		return allRocks.stream().mapToInt(t -> t.getCol()).min().getAsInt();
	}

	public int getToCol() {
		return allRocks.stream().mapToInt(t -> t.getCol()).max().getAsInt();
	}



}
