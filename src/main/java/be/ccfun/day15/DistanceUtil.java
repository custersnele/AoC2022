package be.ccfun.day15;

import javafx.geometry.Pos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DistanceUtil {

	public static int distance(Position p1, Position p2) {
		return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
	}

	public static boolean isIn(Position newBeacon, Position sensor, Position beacon) {
		return distance(newBeacon, sensor) <= distance(beacon, sensor);
	}

	public static Set<Position> createRegion(Position sensor, Position beacon) {
		Set<Position> region = new HashSet<>();
		int distance = distance(sensor, beacon);
		for (int x = sensor.getX() - distance; x <= sensor.getX() + distance; x++) {
			for (int y = sensor.getY() - distance; y <= sensor.getY() + distance; y++) {
				Position inRegion = new Position(x, y);
				if (isIn(inRegion, sensor, beacon)) {
					region.add(inRegion);
				}
			}
		}
		return region;
	}

	public static Set<Position> createPossiblePositions(Position sensor, Position beacon, int min, int max) {
		Set<Position> possiblePositions = new HashSet<>();
		int distance = distance(sensor, beacon);
		int y = 0;
		for (int x = sensor.getX() - distance - 1; x <= sensor.getX(); x++) {
			possiblePositions.add(new Position(x, sensor.getY() + y));
			possiblePositions.add(new Position(x, sensor.getY() - y));
			y++;
		}
		y = distance;
		for (int x = sensor.getX() + 1; x <= sensor.getX() + distance + 1; x++) {
			possiblePositions.add(new Position(x, sensor.getY() + y));
			possiblePositions.add(new Position(x, sensor.getY() - y));
			y--;
		}
		return possiblePositions.stream().filter(p -> isBetween(p.getX(), min, max) && isBetween(p.getY(), min, max)).collect(Collectors.toSet());
	}

	public static boolean isBetween(int x, int min, int max) {
		return x >= min && x <= max;
	}

	public static Set<Position> createRegion(Position sensor, Position beacon, int only) {
		Set<Position> region = new HashSet<>();
		int distance = distance(sensor, beacon);
		for (int x = sensor.getX() - distance; x <= sensor.getX() + distance; x++) {
				Position inRegion = new Position(x, only);
				if (isIn(inRegion, sensor, beacon)) {
					region.add(inRegion);
				}
		}
		return region;
	}

}
