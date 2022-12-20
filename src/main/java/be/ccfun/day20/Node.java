package be.ccfun.day20;

public class Node {
	private long decryptionKey = 811589153L;
	private int value;
	private int originalIdx;

	public Node(int value, int originalIdx) {
		this.value = value;
		this.originalIdx = originalIdx;
	}

	public void setOriginalIdx(int originalIdx) {
		this.originalIdx = originalIdx;
	}

	public long getValue() {
		return value * decryptionKey;
	}

	public long getSteps(int size) {
		return Math.floorMod(getValue(), size - 1);
	}

	public int getOriginalIdx() {
		return originalIdx;
	}
}
