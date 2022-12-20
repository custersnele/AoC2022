package be.ccfun.day20;

import be.ccfun.day18.Point3D;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day20 {

	public static void main(String[] args) throws IOException {
		//CircularList circularList = new CircularList("1, 2, -3, 3, -2, 0, 4");
		CircularList circularList = new CircularList();
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day19/day20.txt"));
		for (String line : lines) {
			circularList.add(Integer.parseInt(line));
		}
		for (int i = 0; i < circularList.getSize(); i++) {
			circularList.move(i);
			System.out.println(circularList);
		}
		int idx = circularList.getIdxOfNumber(0);
		int v1 = circularList.getIndex(idx + 1000);
		System.out.println(v1);
		int v2 = circularList.getIndex(idx + 2000);
		System.out.println(v2);
		int v3 = circularList.getIndex(idx + 3000);
		System.out.println(v3);
		System.out.println(v1 + v2 + v3);
	}

}
