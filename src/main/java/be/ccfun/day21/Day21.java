package be.ccfun.day21;

import be.ccfun.day20.CircularList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day21 {


	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day21/test.txt"));
		Map<String, MathResult> mathResultMap = new HashMap<>();
		for (String line : lines) {
			String[] split = line.split(":");
			try {
				int value = Integer.parseInt(split[1].trim());
				mathResultMap.put(split[0], new MathNumber(value));
			} catch (NumberFormatException e) {
				mathResultMap.put(split[0], new MathOperation(split[1].trim()));
			}
		}
		System.out.println(mathResultMap.get("root").getValue(mathResultMap));
	}
}
