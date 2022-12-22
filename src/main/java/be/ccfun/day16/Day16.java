package be.ccfun.day16;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day16 {
	// https://www.happycoders.eu/algorithms/floyd-warshall-algorithm-java/#Floyd-Warshall_Algorithm_in_Java
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
		MutableValueGraph<String, Integer> graph = ValueGraphBuilder.directed().build();
		for (Valve valve : allValves) {
			for (Valve v2 : valve.getAdjacent()) {
				//if (v2.getRate() != 0) {
					graph.putEdgeValue(valve.getName(), v2.getName(), 2);
				//}
			}
		}
		FloydWarshall.findShortestPaths(graph, true);
//		FloydWarshallOld floydWarshall = new FloydWarshallOld(allValves);
//		floydWarshall.print();
//		System.out.println();
//		for (int i = 0; i < 2; i++) {
//			floydWarshall.execute2();
//			floydWarshall.print();
//			System.out.println();
//		}

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
