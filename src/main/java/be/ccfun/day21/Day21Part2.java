package be.ccfun.day21;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day21Part2 {


	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day21/day21.txt"));
		Map<String, MathResult> mathResultMap = new HashMap<>();
		String rootLeft = "";
		String rootRight = "";
		for (String line : lines) {
			String[] split = line.split(":");
			if (split[0].equals("root")) {
				rootLeft = new MathOperation(split[1]).getLeft();
				rootRight = new MathOperation(split[1]).getRight();
			} else if (!split[0].equals("humn")){
				try {
					int value = Integer.parseInt(split[1].trim());
					mathResultMap.put(split[0], new MathNumber(value));
				} catch (NumberFormatException e) {
					mathResultMap.put(split[0], new MathOperation(split[1].trim()));
				}
			}
		}
		boolean rootLeftHasValue = false;
		long rootLeftValue = 0;
		boolean rootRightHasValue = false;
		long rootRightValue = 0;
		try {
			rootLeftValue = mathResultMap.get(rootLeft).getValue(mathResultMap);
			rootLeftHasValue = true;
		} catch (NullPointerException e) {
		}
		try {
			rootRightValue = mathResultMap.get(rootRight).getValue(mathResultMap);
			rootRightHasValue = true;
		} catch (NullPointerException e) {
		}
		if (rootLeftHasValue && mathResultMap.get(rootRight) instanceof MathOperation rootRightMathOperation) {
//			rootRightMathOperation.setExpected(rootLeftValue);
//			double humn = rootRightMathOperation.getHumn(mathResultMap);
//			System.out.println(humn);
//			NumberFormat formatter = new DecimalFormat("#0.00");
//			System.out.println(formatter.format(humn));
		}
		if (rootRightHasValue && mathResultMap.get(rootLeft) instanceof MathOperation rootLeftMathOperation) {
			rootLeftMathOperation.setExpBig(BigDecimal.valueOf(rootRightValue));
			rootLeftMathOperation.setExpected(rootRightValue);
			System.out.println("Excepted: " + rootRightValue);
			BigDecimal humn = rootLeftMathOperation.getHumn(mathResultMap);
			System.out.println(humn);
			double humn2 = rootLeftMathOperation.getHumn2(mathResultMap);
			System.out.println(humn2);
			NumberFormat formatter = new DecimalFormat("#0.00");
			System.out.println(formatter.format(humn));
			mathResultMap.put("humn", new MathNumber(3916936880448L));
			System.out.println(rootLeftMathOperation.getValue(mathResultMap));

		}
	}
}
