package be.ccfun.day20;

public class Node {
	private int value;
	private int originalIdx;

	public Node(int value, int originalIdx) {
		this.value = value;
		this.originalIdx = originalIdx;
	}

	public int getValue() {
		return value;
	}

	public int getOriginalIdx() {
		return originalIdx;
	}
}
