package be.ccfun.day4;

public class Section {
	private int start;
	private int stop;

	public Section(int start, int stop) {
		this.start = start;
		this.stop = stop;
	}

	public Section(String startstop) {
		String[] split = startstop.split("-");
		start = Integer.parseInt(split[0]);
		stop = Integer.parseInt(split[1]);
	}

	public boolean includes(Section other) {
		return other.start >= start && other.stop <= stop;
	}

	public boolean overlaps(Section other) {
		return Math.max(start, other.start) <= Math.min(stop, other.stop);
	}
}
