package be.ccfun.day13;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class XPacket implements XPacketItem {
	private XPacket prev;
	private List<XPacketItem> items = new LinkedList<>();

	public void add(int number) {
		items.add(new XNumberItem(number));
	}

	public void addXPacket(XPacket xPacket) {
		items.add(xPacket);
	}

	public void setPrev(XPacket prev) {
		this.prev = prev;
	}

	public XPacket getPrev() {
		return prev;
	}

	public static XPacket parseXPacket(String message) {
		message = message.replace("[", "[,").replace("]", ",]").replace(",,",",");
		String[] split = message.split(",");
		XPacket result = new XPacket();
		XPacket current = result;
		for (int i = 1; i < split.length; i++) {
			if (split[i].equals("[")) {
				XPacket x = new XPacket();
				current.addXPacket(x);
				x.setPrev(current);
				current = x;
			} else if (split[i].equals("]")) {
				current = current.getPrev();
			} else {
				current.add(Integer.parseInt(split[i]));
			}
		}
		return result;
	}

	@Override
	public int compareTo(XPacketItem o) {
		if (o instanceof XNumberItem) {
			XPacket packet = new XPacket();
			packet.add(((XNumberItem) o).getNumber());
			return this.compareTo(packet);
		} else {
			XPacket other = (XPacket) o;
			for (int i = 0; i < Math.min(items.size(), other.items.size()); i++) {
				if (items.get(i).compareTo(other.items.get(i)) < 0) {
					return -1;
				} else if (items.get(i).compareTo(other.items.get(i)) > 0) {
					return 1;
				}
			}
			return Integer.compare(items.size(), other.items.size());
		}
	}
}
