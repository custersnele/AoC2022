package be.ccfun.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.OptionalInt;

public class Day8 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day8/day8.txt"));
		int count = 0;
		int highest = 0;
		for (int row = 1; row < lines.size() - 1; row++) {
			for (int col = 1; col < lines.get(0).length() - 1; col++) {
				String upper = getUpper(lines, row, col);
				String lower = getLower(lines, row, col);
				if (isVisibleFromLeft(upper, upper.length() - 1) ||
						isVisibleFromRight(lower, 0) ||
						isVisibleFromRight(lines.get(row), col) ||
						isVisibleFromLeft(lines.get(row), col)) {
					//System.out.println(row + " " + col + " " + lines.get(row).charAt(col));
					count++;
				}
				int viewDistanceLeft = getViewDistanceLeft(lines.get(row), col);
				int viewDistanceRight = getViewDistanceRight(lines.get(row), col);
				int viewDistanceUp = getViewDistanceLeft(upper, upper.length() - 1);
				int viewDistanceLower = getViewDistanceRight(lower, 0);
				int scenicScore = viewDistanceLower * viewDistanceLeft * viewDistanceRight * viewDistanceUp;
				System.out.println(row + " " + col + " " + lines.get(row).charAt(col) + " " + scenicScore);
				if (scenicScore > highest) {
					highest = scenicScore;
				}
			}
		}
		int q = lines.size() * 4 - 4;
		System.out.println(count + q);
		System.out.println(highest);
	}

	public static boolean isVisibleFromLeft(String line, int col) {
		int max = line.substring(0, col).chars().max().getAsInt();
		return max < line.charAt(col);
	}

	public static boolean isVisibleFromRight(String line, int col) {
		int max = line.substring(col + 1).chars().max().getAsInt();
		return max < line.charAt(col);
	}

	public static String getUpper(List<String> lines, int curRow, int curCol) {
		StringBuilder s = new StringBuilder();
		for (int row = 0; row <= curRow; row++) {
			s.append(lines.get(row).charAt(curCol));
		}
		return s.toString();

	}

	public static String getLower(List<String> lines, int curRow, int curCol) {
		StringBuilder s = new StringBuilder();
		for (int row = curRow; row < lines.size(); row++) {
			s.append(lines.get(row).charAt(curCol));
		}
		return s.toString();
	}

	public static int getViewDistanceLeft(String line, int col) {
		for (int i = col - 1; i >= 0; i--) {
			if (line.charAt(i) >= line.charAt(col)) {
				return col - i;
			}
		}
		return col;
	}

	public static int getViewDistanceRight(String line, int col) {
		for (int i = col + 1; i < line.length(); i++) {
			if (line.charAt(i) >= line.charAt(col)) {
				return i - col;
			}
		}
		return line.length() - col - 1;
	}

}
