package be.ccfun.day11;

import java.util.function.Predicate;

public class Test implements Predicate<Long> {

	private int mod;

	public Test(int mod) {
		this.mod = mod;
	}

	public int getMod() {
		return mod;
	}

	@Override
	public boolean test(Long value) {
		return value % mod == 0;
	}
}
