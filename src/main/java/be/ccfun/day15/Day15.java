package be.ccfun.day15;

import javafx.geometry.Pos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Day15 {

	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day15/day15.txt"));
		HashMap<Position, Position> closestBeacon = new HashMap<>();
		HashMap<Position, Set<Position>> regions = new HashMap<>();
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int y = 2000000;
		Set<Position> positions = new HashSet<>();
		Set<Position> excludedRegion = new HashSet<>();
		for (String line : lines) {
			String[] split = line.split(":");
			int sx = getX(split[0]);
			int sy = getY(split[0]);
			Position sensor = new Position(sx, sy);
			int bx = getX(split[1]);
			int by = getY(split[1]);
			Position beacon = new Position(bx, by);
			closestBeacon.put(sensor, beacon);
			Set<Position> region = DistanceUtil.createRegion(sensor, beacon, y);
			regions.put(sensor, region);
			excludedRegion.addAll(region);
			minX = Math.min(minX, Math.min(sx, bx));
			maxX = Math.max(maxX, Math.max(sx, bx));
			if (sy == y) {
				positions.add(sensor);
			}
			if (by == y) {
				positions.add(beacon);
			}
		}

		System.out.println(excludedRegion.size());
		System.out.println(positions.size());
		System.out.println(excludedRegion.size() - positions.size());

	}

	public static int getX(String s) {
		int idx_x = s.indexOf("x=");
		int end_x = s.indexOf(",");
		return Integer.parseInt(s.substring(idx_x + 2, end_x));
	}

	public static int getY(String s) {
		int idx_y = s.indexOf("y=");
		return Integer.parseInt(s.substring(idx_y + 2));
	}
}
