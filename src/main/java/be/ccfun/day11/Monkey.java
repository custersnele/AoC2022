package be.ccfun.day11;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Monkey {
	private Deque<Long> items;
	private int number;
	private Function<Long, Long> operation;
	private Test test;
	private int monkeyTrue;
	private int monkeyFalse;
	private long inspects = 0;

	public Monkey(int number) {
		this.number = number;
	}

	public void setItems(String items) {
		this.items = new LinkedList<>();
		for (String item : items.split(",")) {
			this.items.offerLast(Long.parseLong(item));
		}
	}

	public void setOperation(String operation) {
		String[] s = operation.split(" ");
		if (s[4].equals("old")) {
			this.operation = new SelfOperation(s[3]);
		} else {
			this.operation = new Operation(s[3], Integer.parseInt(s[4]));
		}
	}

	public void setTest(int value) {
		test = new Test(value);
	}

	public void setMonkeyFalse(int monkeyFalse) {
		this.monkeyFalse = monkeyFalse;
	}

	public void setMonkeyTrue(int monkeyTrue) {
		this.monkeyTrue = monkeyTrue;
	}

	public int getMonkeyByTest(long value) {
		if (this.test.test(value)) {
			return monkeyTrue;
		} else {
			return monkeyFalse;
		}
	}

	public void turn(List<Monkey> allMonkeys, long lcm) {
		System.out.println("Turn for monkey " + number);
		while (!items.isEmpty()) {
			long item = items.removeFirst();
			//System.out.println("Inspecting: " + item);
			item = operation.apply(item) % lcm;
			inspects++;
			//System.out.println("New worry level : " + item);
			if (test.test(item)) {
				System.out.println("Throw item to monkey " + monkeyTrue);
				allMonkeys.get(monkeyTrue).getItem(item);
			} else {
				System.out.println("Throw item to monkey " + monkeyFalse);
				allMonkeys.get(monkeyFalse).getItem(item);
			}
		}
	}


	public int getMod() {
		return test.getMod();
	}

	private void getItem(Long item) {
		items.offerLast(item);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Monkey ").append(number).append(": ");
		for (Long item : items) {
			builder.append(item).append(", ");
		}
		return builder.toString();
	}

	public long getInspects() {
		return inspects;
	}

	public int getNumber() {
		return number;
	}
}
