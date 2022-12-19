package be.ccfun.day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day18 {

	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day18/day18.txt"));
		Set<Point3D> allPoints = new HashSet<>();
		for (String line : lines) {
			allPoints.add(new Point3D(line));
		}
		Set<Point3D> toAdd = new HashSet<>();
		int count = 0;
		for (Point3D aPoint : allPoints) {
			int exposed = aPoint.getExposed(allPoints);
			count += exposed;
		}
		System.out.println(count);

		for (Point3D aPoint : allPoints) {
			for (Point3D candidate : aPoint.getCandidates()) {
				if (!toAdd.contains(candidate) && candidate.getExposed(allPoints) == 0) {
					toAdd.add(candidate);
					System.out.println(candidate);
				}
				if (!toAdd.contains(candidate) && candidate.isSurrounded(allPoints)) {
					toAdd.add(candidate);
					System.out.println(candidate);
				}
			}
		}
		allPoints.addAll(toAdd);
		count = 0;
		for (Point3D aPoint : allPoints) {
			int exposed = aPoint.getExposed(allPoints);
			count += exposed;
		}
		System.out.println(count);
	}
}
