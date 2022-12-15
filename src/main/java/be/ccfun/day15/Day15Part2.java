package be.ccfun.day15;

import javafx.geometry.Pos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day15Part2 {

	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day15/day15.txt"));
		HashMap<Position, Position> closestBeacon = new HashMap<>();
		HashMap<Position, Set<Position>> regions = new HashMap<>();
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int max = 4000000;
		Set<Position> positions = new HashSet<>();
		Set<Position> excludedRegion = new HashSet<>();
		Set<Position> possiblePositions = new HashSet<>();
		for (String line : lines) {
			System.out.println(line);
			String[] split = line.split(":");
			int sx = getX(split[0]);
			int sy = getY(split[0]);
			Position sensor = new Position(sx, sy);
			int bx = getX(split[1]);
			int by = getY(split[1]);
			Position beacon = new Position(bx, by);
			closestBeacon.put(sensor, beacon);
			Set<Position> region = DistanceUtil.createPossiblePositions(sensor, beacon, 0, max);
			possiblePositions.addAll(region);
		}

		for (Position position : possiblePositions) {
			if (!isInArea(position, closestBeacon)) {
				System.out.println(position);
				System.out.println(position.getX() * 4000000L + position.getY());
			}
		}
	}

	public static boolean isInArea(Position position, Map<Position, Position> closestBeacon) {
		for (Map.Entry<Position, Position> entry : closestBeacon.entrySet()) {
			if (DistanceUtil.isIn(position, entry.getKey(), entry.getValue())) {
				return true;
			}
		}
		return false;
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
