package be.ccfun.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day16 {

	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day16/test.txt"));
		List<Valve> allValves = new ArrayList<>();
		for (String line : lines) {
			//Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
			String[] parts = line.split(";");
			String[] v = parts[0].replace("Valve ","").replace(" has flow rate=", ",").split(",");
			String name = v[0];
			Valve result = getValve(allValves, name);
			result.setRate(Integer.parseInt(v[1]));
			String[] adjacent = parts[1].replace(" tunnels lead to valves ","").replace(" tunnel leads to valve ","").split(",");
			for (String adj : adjacent) {
				Valve valve = getValve(allValves, adj.trim());
				result.addValve(valve);
			}
		}
		Valve current = getValve(allValves, "AA");
		for (int i = 0; i < 30; i+=2) {
			current = current.getNext();
			System.out.println("Move to: " + current.getName());
			current.open(i + 1);
		}
		int sum = allValves.stream().mapToInt(v -> v.getPressure()).sum();
		System.out.println(sum);
	}

	private static Valve getValve(List<Valve> allValves, String name) {
		Valve result = allValves.stream().filter(valve -> valve.getName().equals(name)).findAny().orElse(null);
		if (result == null) {
			result = new Valve(name, 0);
			allValves.add(result);
		}
		return result;
	}

}
