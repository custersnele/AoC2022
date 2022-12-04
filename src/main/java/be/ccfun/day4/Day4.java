package be.ccfun.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day4 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day4/day4.txt"));
		int includes = 0;
		int overlaps = 0;
		for (String line : lines) {
			String[] split = line.split(",");
			Section section1 = new Section(split[0]);
			Section section2 = new Section(split[1]);
			if (section1.includes(section2) || section2.includes(section1)) {
				includes += 1;
			}
			if (section1.overlaps(section2)) {
				overlaps += 1;
			}
		}
		System.out.println(includes);
		System.out.println(overlaps);


	}
}
