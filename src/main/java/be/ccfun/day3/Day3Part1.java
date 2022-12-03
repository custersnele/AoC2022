package be.ccfun.day3;

import be.ccfun.day2.RPC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day3Part1 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day3/day3.txt"));
		long sum = 0;
		for (String line : lines) {
			int length = line.length();
			String s1 = line.substring(0, length / 2);
			String s2 = line.substring(length / 2);
			System.out.println(s1 + " " + s2);
			System.out.println(s1.length() + " " + s2.length());
			String common = common(s1, s2);
			System.out.println(common);
			int score = score(common.charAt(0));
			System.out.println(score);
			sum += score;

		}

		System.out.println(sum);

	}


	public static String common(String s1, String s2) {
		for (int i = 0; i < s1.length(); i++) {
			if (s2.contains(s1.substring(i, i+1))) {
				return s1.substring(i, i+1);
			}
		}
		return null;
	}

	public static int score(Character c) {
		if (Character.isUpperCase(c)) {
			return (int) c - (int) 'A' + 27;
		} else {
			return (int) c - (int) 'a' + 1;
		}
	}


}
