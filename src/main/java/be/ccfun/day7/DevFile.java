package be.ccfun.day7;

public class DevFile {
	private String name;
	private long size;

	public DevFile(String name, long size) {
		this.name = name;
		this.size = size;
	}

	public DevFile(String line) {
		String[] s = line.split(" ");
		this.name = s[1];
		this.size = Long.parseLong(s[0]);
	}

	public long getSize() {
		return size;
	}

	@Override
	public String toString() {
		return size + " " + name;
	}
}
