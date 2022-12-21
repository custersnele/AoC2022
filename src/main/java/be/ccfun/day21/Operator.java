package be.ccfun.day21;

public enum Operator {
	PLUS,
	MINUS,
	TIMES,
	DIVISION;

	public Operator getReverse() {
		switch (this) {
			case PLUS: return MINUS;
			case MINUS: return PLUS;
			case TIMES: return DIVISION;
			case DIVISION: return TIMES;
		}
		throw new IllegalArgumentException();
	}

	public static Operator bySign(String sign) {
		switch (sign.trim()) {
			case "+":
				return PLUS;
			case "-":
				return MINUS;
			case "*":
				return TIMES;
			case "/":
				return DIVISION;
		}
		throw new IllegalArgumentException("Unknown operator " + sign);
	}

}
