package be.ccfun.day21;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class MathOperation implements MathResult {

	private String left;
	private String right;
	private Operator operator;
	private BigDecimal expBig;
	private double expected;

	public double getHumn2(Map<String, MathResult> mathResult) {
		if (left.equals("humn")) {
			switch (operator) {
				case PLUS:
					return expected - mathResult.get(right).getValue(mathResult);
				case MINUS:
					return expected + mathResult.get(right).getValue(mathResult);
				case TIMES:
					return expected / (double) mathResult.get(right).getValue(mathResult);
				case DIVISION:
					return expected * mathResult.get(right).getValue(mathResult);
			}
		} else if (right.equals("humn")) {
			switch (operator) {
				case PLUS:
					return expected - mathResult.get(left).getValue(mathResult);
				case MINUS:
					return -expected + mathResult.get(left).getValue(mathResult);
				case TIMES:
					return expected / (double) mathResult.get(left).getValue(mathResult);
				case DIVISION:
					return (double) mathResult.get(left).getValue(mathResult) / expected;
			}
		} else if (mathResult.get(left) instanceof MathOperation leftMathOperation && mathResult.get(right).hasResult(mathResult)) {
			switch (operator) {
				case PLUS: {
					leftMathOperation.setExpected(expected - mathResult.get(right).getValue(mathResult));
					return leftMathOperation.getHumn2(mathResult);
				}
				case MINUS: {
					leftMathOperation.setExpected(expected + mathResult.get(right).getValue(mathResult));
					return leftMathOperation.getHumn2(mathResult);
				}
				case TIMES: {
					leftMathOperation.setExpected(expected / (double) mathResult.get(right).getValue(mathResult));
					return leftMathOperation.getHumn2(mathResult);
				}
				case DIVISION: {
					leftMathOperation.setExpected(expected * (double) mathResult.get(right).getValue(mathResult));
					return leftMathOperation.getHumn2(mathResult);
				}
			}
		} else if (mathResult.get(right) instanceof MathOperation rightMathOperation && mathResult.get(left).hasResult(mathResult)) {
			switch (operator) {
				case PLUS: {
					rightMathOperation.setExpected(expected - mathResult.get(left).getValue(mathResult));
					return rightMathOperation.getHumn2(mathResult);
				}
				case MINUS: {
					rightMathOperation.setExpected(expected * -1 + mathResult.get(left).getValue(mathResult));
					return rightMathOperation.getHumn2(mathResult);
				}
				case TIMES: {
					rightMathOperation.setExpected(expected / (double) mathResult.get(left).getValue(mathResult));
					return rightMathOperation.getHumn2(mathResult);
				}
				case DIVISION: {
					rightMathOperation.setExpected((double) mathResult.get(left).getValue(mathResult) / expected);
					return rightMathOperation.getHumn2(mathResult);
				}
			}
		} else {
			System.out.println("Unexpected situation....");
			return 0;
		}
		System.out.println("Unreacheable ...");
		return 0;
	}

	public void setExpected(double expected) {
		//System.out.println("Set expected " + expected);
		this.expected = expected;
	}

	public MathOperation(String left, String right, String operator) {
		this.left = left.trim();
		this.right = right.trim();
		this.operator = Operator.bySign(operator);
	}

	public boolean hasResult(Map<String, MathResult> mathResult) {
		if (left.equals("humn") || right.equals("humn")) {
			return false;
		}
		return mathResult.get(left).hasResult(mathResult) && mathResult.get(right).hasResult(mathResult);
	}

	public BigDecimal getHumn(Map<String, MathResult> mathResult) {
		if (left.equals("humn")) {
			switch (operator) {
				case PLUS:
					return expBig.subtract(BigDecimal.valueOf(mathResult.get(right).getValue(mathResult)));
				case MINUS:
					return expBig.add(BigDecimal.valueOf(mathResult.get(right).getValue(mathResult)));
				case TIMES:
					return expBig.divide(BigDecimal.valueOf(mathResult.get(right).getValue(mathResult)), 5, RoundingMode.HALF_UP);
				case DIVISION:
					return expBig.multiply(BigDecimal.valueOf(mathResult.get(right).getValue(mathResult)));
			}
		} else if (right.equals("humn")) {
			switch (operator) {
				case PLUS:
					return expBig.subtract(BigDecimal.valueOf(mathResult.get(left).getValue(mathResult)));
				case MINUS:
					return expBig.multiply(BigDecimal.valueOf(-1)).add(BigDecimal.valueOf(mathResult.get(left).getValue(mathResult)));
				case TIMES:
					return expBig.divide(BigDecimal.valueOf(mathResult.get(left).getValue(mathResult)), 5, RoundingMode.HALF_UP);
				case DIVISION:
					return BigDecimal.valueOf(mathResult.get(left).getValue(mathResult)).divide(expBig, 5, RoundingMode.HALF_UP);
			}
		} else if (mathResult.get(left) instanceof MathOperation leftMathOperation && mathResult.get(right).hasResult(mathResult)) {
			switch (operator) {
				case PLUS: {
					leftMathOperation.setExpBig(expBig.subtract(BigDecimal.valueOf(mathResult.get(right).getValue(mathResult))));
					return leftMathOperation.getHumn(mathResult);
				}
				case MINUS: {
					leftMathOperation.setExpBig(expBig.add(BigDecimal.valueOf(mathResult.get(right).getValue(mathResult))));
					return leftMathOperation.getHumn(mathResult);
				}
				case TIMES: {
					leftMathOperation.setExpBig(expBig.divide(BigDecimal.valueOf(mathResult.get(right).getValue(mathResult)), 5, RoundingMode.HALF_UP));
					return leftMathOperation.getHumn(mathResult);
				}
				case DIVISION: {
					leftMathOperation.setExpBig(expBig.multiply(BigDecimal.valueOf(mathResult.get(right).getValue(mathResult))));
					return leftMathOperation.getHumn(mathResult);
				}
			}
		} else if (mathResult.get(right) instanceof MathOperation rightMathOperation && mathResult.get(left).hasResult(mathResult)) {
			switch (operator) {
				case PLUS: {
					rightMathOperation.setExpBig(expBig.subtract(BigDecimal.valueOf(mathResult.get(left).getValue(mathResult))));
					return rightMathOperation.getHumn(mathResult);
				}
				case MINUS: {
					rightMathOperation.setExpBig(expBig.multiply(BigDecimal.valueOf(-1)).add(BigDecimal.valueOf(mathResult.get(left).getValue(mathResult))));
					return rightMathOperation.getHumn(mathResult);
				}
				case TIMES: {
					rightMathOperation.setExpBig(expBig.divide(BigDecimal.valueOf(mathResult.get(left).getValue(mathResult)), 5, RoundingMode.HALF_UP));
					return rightMathOperation.getHumn(mathResult);
				}
				case DIVISION: {
					rightMathOperation.setExpBig(BigDecimal.valueOf(mathResult.get(left).getValue(mathResult)).divide(expBig, 5 , RoundingMode.HALF_UP));
					return rightMathOperation.getHumn(mathResult);
				}
			}
		} else {
			System.out.println("Unexpected situation....");
			return BigDecimal.ZERO;
		}
		System.out.println("Unreacheable ...");
		return BigDecimal.ZERO;
	}

	public void setExpBig(BigDecimal expBig) {
		//System.out.println("Set expected " + expBig);
		this.expBig = expBig;
	}

	public MathOperation(String operation) {
		String[] s = operation.trim().split(" ");
		left = s[0];
		right = s[2];
		operator = Operator.bySign(s[1]);
	}

	public String getLeft() {
		return left;
	}

	public String getRight() {
		return right;
	}

	public long getValue(Map<String, MathResult> mathResult) {
		if (mathResult.get(left) == null) {
			System.out.println("test");
		}
		long resultLeft = mathResult.get(left).getValue(mathResult);
		long resultRight = mathResult.get(right).getValue(mathResult);
		return switch (operator) {
			case PLUS -> resultLeft + resultRight;
			case MINUS -> resultLeft - resultRight;
			case TIMES -> resultLeft * resultRight;
			case DIVISION -> resultLeft / resultRight;
		};
	}
}
