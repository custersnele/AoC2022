package be.ccfun.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day3Part2 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day3/day3.txt"));
		long sum = 0;
		for (int i = 0; i < lines.size(); i += 3) {
			String elf1 = lines.get(i);
			String elf2 = lines.get(i + 1);
			String elf3 = lines.get(i + 2);
			String common = common(elf1, elf2);
			System.out.print(common + " ");
			common = common(common, elf3);
			System.out.println(common);
			int score = score(common.charAt(0));
			System.out.println(score);
			sum += score;
		}
		System.out.println(sum);

	}


	public static String common(String s1, String s2) {
		StringBuilder common = new StringBuilder();
		for (int i = 0; i < s1.length(); i++) {
			if (s2.contains(s1.substring(i, i+1))) {
				common.append(s1.charAt(i));
			}
		}
		return common.toString();
	}

	public static int score(Character c) {
		if (Character.isUpperCase(c)) {
			return (int) c - (int) 'A' + 27;
		} else {
			return (int) c - (int) 'a' + 1;
		}
	}


}
