package be.ccfun.day15;

import javafx.geometry.Pos;

import java.util.HashSet;
import java.util.Set;

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
