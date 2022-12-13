package be.ccfun.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day12 {

	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day12/day12.txt"));
		List<Position> positions = new ArrayList<>();
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(0).length(); j++) {
				char height = lines.get(i).charAt(j);
				Position p = new Position(i, j, height);
				positions.add(p);
			}
		}
		List<ShortestPath> threads = new ArrayList<>();
		for (Position p : positions) {
			if (p.getHeight() == 'a') {
				ShortestPath s = new ShortestPath(copy(positions), new Position(p));
				s.start();
				threads.add(s);
			}
		}

		int shortest = Integer.MAX_VALUE;
		for(ShortestPath t : threads) {
			t.join();
			if (t.getShortestPath() < shortest) {
				shortest = t.getShortestPath();
			}
		}
		System.out.println(shortest);


	}

	private static List<Position> copy(List<Position> positions) {
		List<Position> copy = new ArrayList<>(positions.size());
		for (Position p : positions) {
			copy.add(new Position(p));
		}
		return copy;
	}

}
