package be.ccfun.day14;

import be.ccfun.day9.Snake;
import be.ccfun.day9.Tile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day14 {

	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day14/day14.txt"));
		Cave cave = new Cave();
		for (String line : lines) {
			cave.addRock(new Rock(line));
		}
		cave.showCave();
		int step = 0;
		boolean addSand = true;
		while (addSand) {
			try {
				new Sand(cave);
				step++;
				if (step % 100 == 0) {
					System.out.println(step);
				}
				if (cave.isStable()) {
					addSand = false;
					System.out.println("Sand: " + cave.countSand());
				}
			} catch (CaveFullException e) {
				System.out.println("Stopped after step " + step);
				addSand = false;
			}
		}
		cave.showCave();
	}

}
