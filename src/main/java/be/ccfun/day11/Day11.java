package be.ccfun.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day11/day11.txt"));
		List<Monkey> monkeys = new ArrayList<>();
		Monkey currentMonkey = null;
		int monkeyIdx = 0;
		int i = 0;
		while (i < lines.size()) {
			String line = lines.get(i++);
			if (line.startsWith("Monkey")) {
				currentMonkey = new Monkey(monkeyIdx++);
				monkeys.add(currentMonkey);
			} else if (line.contains("Starting items")) {
				int idx = line.indexOf(":");
				currentMonkey.setItems(line.substring(idx + 2).replace(" ", ""));
			} else if (line.contains("Operation")) {
				int idx = line.indexOf(":");
				currentMonkey.setOperation(line.substring(idx + 2));
			} else if (line.contains("Test")) {
				int idx = line.indexOf("by");
				currentMonkey.setTest(Integer.parseInt(line.substring(idx + 3)));
			} else if (line.contains("true")) {
				int idx = line.indexOf("monkey");
				currentMonkey.setMonkeyTrue(Integer.parseInt(line.substring(idx + 7)));
			} else if (line.contains("false")) {
				int idx = line.indexOf("monkey");
				currentMonkey.setMonkeyFalse(Integer.parseInt(line.substring(idx + 7)));
			}
		}
		System.out.println(monkeys.size());
		long lcm = lcm(monkeys.stream().map(Monkey::getMod).collect(Collectors.toList()));
		System.out.println(lcm);

		for (int j = 0; j < 10000; j++) {
			System.out.println("Round " + j);
			for (Monkey monkey : monkeys) {
				monkey.turn(monkeys, lcm);
			}
			for (Monkey monkey : monkeys) {
				System.out.println(monkey.getNumber() + " " + monkey.getInspects());
			}
		}
		List<Long> values = monkeys.stream().map(m -> m.getInspects()).sorted(Comparator.reverseOrder()).limit(2).toList();
		System.out.println(values.get(0) * values.get(1));

	}


	private static long lcm(long a, long b) {
		return a * (b / gcd(a, b));
	}

	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		} else {
			return (gcd(b, a % b));
		}
	}

	private static long lcm(List<Integer> input) {
		long result = input.get(0);
		for (int i = 1; i < input.size(); i++) result = lcm(result, input.get(i));
		return result;
	}

}
