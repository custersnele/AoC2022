package be.ccfun.day16;

import java.util.ArrayList;
import java.util.List;

public class Valve implements Comparable<Valve> {
	private String name;
	private int rate;
	private List<Valve> adjacent = new ArrayList<>();
	private boolean open;
	private int time;

	public List<Valve> getAdjacent() {
		return adjacent;
	}

	public String getName() {
		return name;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Valve(String name, int rate) {
		this.name = name;
		this.rate = rate;
	}

	public void addValve(Valve valve) {
		adjacent.add(valve);
	}

	public void open(int time) {
		this.time = time;
		this.open = true;
	}

	public int getPressure() {
		if (open) {
			return time * rate;
		} else {
			return 0;
		}
	}

	public boolean isOpen() {
		return open;
	}

	public Valve getNext() {
		Valve valve = adjacent.stream().filter(v -> !v.isOpen()).sorted().findFirst().orElse(null);
		return valve;
	}

	@Override
	public int compareTo(Valve o) {
		return o.rate - rate;
	}
}
