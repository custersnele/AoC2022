package be.ccfun.day13;

public class PacketCompare {

	public static boolean isRightOrder(String left, String right) {
		XPacket leftP = XPacket.parseXPacket(left);
		XPacket rightP = XPacket.parseXPacket(right);
		return leftP.compareTo(rightP) <= 0;
	}

}
