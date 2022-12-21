package be.ccfun.day21;

import java.util.Map;

public interface MathResult {
	long getValue(Map<String, MathResult> results);
	boolean hasResult(Map<String, MathResult> results);

}
