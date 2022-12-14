package be.ccfun.day14;

import java.util.ArrayList;
import java.util.List;

public class Cave {
	private List<Rock> rocks = new ArrayList<>();
	private List<Sand> sand = new ArrayList<>();
	private List<Position> occupied = new ArrayList<>();
	private int floor;

	public void addRock(Rock rock) {
		rocks.add(rock);
		floor = rocks.stream().mapToInt(t -> t.getToRow()).max().getAsInt() + 2;
		occupied.addAll(rock.all());
	}

	public boolean isRock(Position position) {
		for (Rock rock : rocks) {
			if (rock.hasRock(position)) {
				return true;
			}
		}
		return false;
	}

	public boolean isSand(Position position) {
		for (Sand s : sand) {
			if (s.isPosition(position)) {
				return true;
			}
		}
		return false;
	}

	public boolean isFree(Position position) {
		if (position.getRow() == floor) {
			return false;
		}
		// SLOW return !isRock(position) && !isSand(position);
		return !isOccupied(position);
	}

	public boolean isOccupied(Position position) {
		return occupied.contains(position);
	}

	public void addSand(Sand sand) {
		this.sand.add(sand);
		occupied.add(sand.getPosition());
	}

	public void showCave() {
		int minRow = rocks.stream().mapToInt(t -> t.getFromRow()).min().getAsInt();
		int maxRow = floor;
		int minCol = rocks.stream().mapToInt(t -> t.getFromCol()).min().getAsInt();
		int maxCol = rocks.stream().mapToInt(t -> t.getToCol()).max().getAsInt();
		for (int row = minRow; row <= maxRow; row++) {
			for (int col = minCol; col <= maxCol; col++) {
				Position p = new Position(col, row);
				if (isRock(p)) {
					System.out.print("#");
				} else if (isSand(p)) {
					System.out.print("o");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}

	public boolean isStable() {
		return isSand(new Position(500, 0));
	}

	public boolean isOut(Position position) {
		int maxRow = rocks.stream().mapToInt(t -> t.getToRow()).max().getAsInt();
		return position.getRow() > maxRow;
	}

	public int countSand() {
		return sand.size();
	}
}
