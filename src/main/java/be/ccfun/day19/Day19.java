package be.ccfun.day19;

import be.ccfun.day18.Point3D;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day19 {
	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day19/test.txt"));
		Set<Point3D> allPoints = new HashSet<>();
		List<Blueprint> blueprints = new ArrayList<>();
		Blueprint currentBlueprint = new Blueprint();
		for (String line : lines) {
			if (line.startsWith("Blueprint")) {
				currentBlueprint = new Blueprint();
				blueprints.add(currentBlueprint);
			} else if (!line.isBlank()) {
				currentBlueprint.addRobot(new Robot(line));
			}
		}
		System.out.println(blueprints.size());
		//System.out.println(blueprints.get(0).getMaxGeodes(24));
	}

}
