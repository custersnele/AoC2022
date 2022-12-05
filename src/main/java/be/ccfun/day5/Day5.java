package be.ccfun.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day5 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day5/day5.txt"));
		int containers = 9;
		List<ContainerStack> ship = new ArrayList<>();
		for (int i = 0; i < containers; i++) {
			ship.add(new ContainerStack());
		}
		boolean unpacking = false;
		for (String line : lines) {
			if (line.isBlank()) {
				unpacking = true;
			} else if (line.contains("[")) {
				int i = line.indexOf("[");
				while (i != -1) {
					int idx = i / 4;
					ship.get(idx).add(line.substring(i+1, i +2));
					i = line.indexOf("[", i + 1);
				}
			} else if (unpacking) {
				int idxNumber = 5;
				int idxFrom = line.indexOf("from ");
				int idxTo = line.indexOf("to ");
				int number = Integer.parseInt(line.substring(idxNumber, idxFrom - 1));
				int from = Integer.parseInt(line.substring(idxFrom + 5, idxTo - 1));
				int to = Integer.parseInt(line.substring(idxTo + 3));
				System.out.println(number + " " + from + " " + to);
		//		for (int i = 0; i < number; i++) {
//					String s = ship.get(from - 1).get();
//					ship.get(to - 1).push(s);
					List<String> s = ship.get(from - 1).get(number);
					ship.get(to - 1).push(s);
		//		}
//				for (ContainerStack c : ship) {
//					System.out.println(c);
//				}
			}
		}
		for (ContainerStack c : ship) {
			System.out.print(c.get());
		}

	}
}
