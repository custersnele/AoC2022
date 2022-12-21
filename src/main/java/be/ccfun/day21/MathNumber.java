package be.ccfun.day21;

import java.util.Map;

public class MathNumber implements MathResult {
	private long value;

	public MathNumber(long value) {
		this.value = value;
	}

	public long getValue(Map<String, MathResult> results) {
		return value;
	}

	@Override
	public boolean hasResult(Map<String, MathResult> results) {
		return true;
	}
}
