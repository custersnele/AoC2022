package be.ccfun.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day1 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day1/day1.txt"));
		long max = 0;
		long current = 0;
		List<Long> cals = new ArrayList<>();
		for (String line : lines) {
			if (line.isBlank()) {
				if (current > max) {
					max = current;
				}
				cals.add(current);
				current = 0;
			} else {
				current += Long.parseLong(line);
			}
		}
		if (current > max) {
			max = current;
		}
		cals.add(current);
		long sum = cals.stream().sorted(Comparator.reverseOrder()).mapToLong(x -> x).limit(3).sum();
		System.out.println(max);
		System.out.println(sum);
	}

}
