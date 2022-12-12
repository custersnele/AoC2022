package be.ccfun.day11;

import java.util.function.Function;

public class SelfOperation implements Function<Long, Long> {
	private String operator;

	public SelfOperation(String operator) {
		this.operator = operator;
	}

	@Override
	public Long apply(Long value) {
		switch (operator) {
			case "+": return value + value;
			case "*": return value * value;
			default: return -1L;
		}
	}
}
