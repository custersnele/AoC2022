package be.ccfun.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day9 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day9/day9.txt"));
		List<Tile> allTiles = new ArrayList<>();
		Tile start = new Tile(0, 0);
		allTiles.add(start);
		Tile headTile = start;
		Tile tailTile = start;
		tailTile.visit();
		for (String line: lines) {
			System.out.println(line);
			String[] s = line.split(" ");
			Direction direction = Direction.valueOf(s[0]);
			int times = Integer.parseInt(s[1]);
			for (int i = 0; i < times; i++) {
				Tile prevTail = headTile;
				headTile = headTile.getNeighbour(direction, allTiles);
				//printTiles(allTiles, headTile, tailTile);
				if (!headTile.touch(tailTile)) {
					tailTile = prevTail;
					tailTile.visit();
				}
			}
		}
		printTiles(allTiles, headTile, tailTile);
		System.out.println(allTiles.stream().distinct().filter(t -> t.isVisited()).count());

	}


	public static void printTiles(List<Tile> allTiles, Tile head, Tile tail) {
		int minRow = allTiles.stream().mapToInt(t -> t.getRow()).min().getAsInt();
		int maxRow = allTiles.stream().mapToInt(t -> t.getRow()).max().getAsInt();
		int minCol = allTiles.stream().mapToInt(t -> t.getCol()).min().getAsInt();
		int maxCol = allTiles.stream().mapToInt(t -> t.getCol()).max().getAsInt();
		for (int row = minRow; row <= maxRow; row++) {
			for (int col = minCol; col <= maxCol; col++) {
				Tile o = new Tile(row, col);
				Optional<Tile> first = allTiles.stream().filter(t -> t.equals(o)).findFirst();
				if (first.isEmpty()) {
					System.out.print(".");
				} else {
					if (first.get().equals(head)) {
						System.out.print("H");
					} else if (first.get().equals(tail)) {
						System.out.print("T");
					} else if (first.get().isVisited()) {
						System.out.print("#");
					} else {
						System.out.print("+");
					}
				}
			}
			System.out.println();
		}
	}

}
