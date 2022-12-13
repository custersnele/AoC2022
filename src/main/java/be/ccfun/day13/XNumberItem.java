package be.ccfun.day13;

public class XNumberItem implements XPacketItem {
	private int number;


	public XNumberItem(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public int compareTo(XPacketItem o) {
		if (o instanceof XNumberItem) {
			XNumberItem otherNumber = (XNumberItem) o;
			return Integer.compare(number, otherNumber.number);
		} else {
			XPacket packet = new XPacket();
			packet.add(number);
			return packet.compareTo(o);
		}
	}
}
