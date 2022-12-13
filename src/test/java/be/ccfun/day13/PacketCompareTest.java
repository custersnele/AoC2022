package be.ccfun.day13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PacketCompareTest {

	@Test
	public void test1() {
		boolean result = PacketCompare.isRightOrder("[1,1,3,1,1]","[1,1,5,1,1]");
		Assertions.assertTrue(result);
	}

	@Test
	public void test2() {
		boolean result = PacketCompare.isRightOrder("[[1],[2,3,4]]", "[[1],4]");
		Assertions.assertTrue(result);
	}

	@Test
	public void test3() {
		boolean result = PacketCompare.isRightOrder("[9]", "[[8,7,6]]");
		Assertions.assertFalse(result);
	}

	@Test
	public void test4() {
		boolean result = PacketCompare.isRightOrder("[[4,4],4,4]","[[4,4],4,4,4]");
		Assertions.assertTrue(result);
	}

	@Test
	public void test5() {
		boolean result = PacketCompare.isRightOrder("[1,[2,[3,[4,[5,6,7]]]],8,9]", "[1,[2,[3,[4,[5,6,0]]]],8,9]");
		Assertions.assertFalse(result);
	}

	@Test
	public void test6() {
		boolean result = PacketCompare.isRightOrder("[]","[3]");
		Assertions.assertTrue(result);
	}



}
