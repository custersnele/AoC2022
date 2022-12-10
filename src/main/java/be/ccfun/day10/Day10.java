package be.ccfun.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day10 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day10/day10.txt"));
		long clock = 0;
		int next = 40; // for part 1 put 20 here
		long v = 1;
		long sum = 0;
		int position = 0;
		StringBuilder crt = new StringBuilder();
		for (String line : lines) {
			if (line.startsWith("noop")) {
				clock += 1;
				crt.append(getCharacter(position, v));
				position++;
				if (clock == next) {
					long sum1 = clock * v;
					sum += sum1;
					System.out.println(clock + " " + v  + " " + sum1);
					next += 40;
					//position = 1;
					crt.append("\n");
				}
			} else {
				String[] s = line.split(" ");
				clock += 1;
				crt.append(getCharacter(position, v));
				position++;
				if (clock == next) {
					long sum1 = clock * v;
					sum += sum1;
					System.out.println(clock + " " + v  + " " + sum1);
					next += 40;
					//position = 1;
					crt.append("\n");
				}
				clock += 1;
				crt.append(getCharacter(position, v));
				position++;
				if (clock == next) {
					long sum1 = clock * v;
					sum += sum1;
					System.out.println(clock + " " + v  + " " + sum1);
					next += 40;
					//position = 1;
					crt.append("\n");
				}
				v += Integer.parseInt(s[1]);
			}
		}
		System.out.println(sum);
		System.out.println(crt.toString());
	}

	private static String getCharacter(int position, long v) {
		if (position % 40 >= v - 1 && position % 40 <= v+ 1) {
			return "#";
		} else {
			return ".";
		}
	}


}
