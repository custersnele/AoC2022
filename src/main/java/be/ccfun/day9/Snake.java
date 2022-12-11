package be.ccfun.day9;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Snake {
	private int length = 10;
	private List<Tile> tiles = new ArrayList<>();

	public Snake(Tile start) {
		for (int i = 0; i < length; i++) {
			tiles.add(start);
		}
		tiles.get(length - 1).visit();
	}

	public void move(Direction direction, List<Tile> allTiles) {
		Tile prev = tiles.get(0);
		tiles.set(0, tiles.get(0).move(direction, allTiles));
		for (int i = 1; i < tiles.size(); i++) {
			Tile curPrev = tiles.get(i);
			if (!tiles.get(i).touch(tiles.get(i - 1))) {
				int difRow = Math.abs(tiles.get(i - 1).getRow() - tiles.get(i).getRow());
				int difCol = Math.abs(tiles.get(i - 1).getCol() - tiles.get(i).getCol());
				int deltaRow = 0;
				int deltaCol = 0;
				if (difRow > 0) {
					deltaRow += 1;
					if (tiles.get(i - 1).getRow() < tiles.get(i).getRow()) {
						deltaRow = -1;
					}
				}
				if (difCol > 0) {
					deltaCol += 1;
					if (tiles.get(i - 1).getCol() < tiles.get(i).getCol()) {
						deltaCol = -1;
					}
				}
				tiles.set(i, tiles.get(i).move(new int[] {deltaRow, deltaCol}, allTiles));
			}
			//Day9.printTiles(allTiles, this);
			prev = curPrev;
		}
		tiles.get(length - 1).visit();
	}

	public boolean contains(Tile tile) {
		return tiles.contains(tile);
	}
}
