package be.ccfun.day11;

import java.math.BigInteger;
import java.util.function.Function;

public class Operation implements Function<Long, Long> {
	private String operator;
	private int operand;

	public Operation(String operator, int operand) {
		this.operator = operator;
		this.operand = operand;
	}

	public String getOperator() {
		return operator;
	}

	@Override
	public Long apply(Long value) {
		switch (operator) {
			case "+": return value + operand;
			case "*": return value * operand;
			default: return -1L;
		}
	}
}
