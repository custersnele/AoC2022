package be.ccfun.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 {

	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day13/day13.txt"));
		List<XPacket> allPackets = new ArrayList<>();
		int idx = 0;
		int sum = 0;
		for (int i = 0; i < lines.size(); i+= 3) {
			idx++;
			if (PacketCompare.isRightOrder(lines.get(i), lines.get(i+1))) {
				sum += idx;
			}
			allPackets.add(XPacket.parseXPacket(lines.get(i)));
			allPackets.add(XPacket.parseXPacket(lines.get(i+1)));
		}
		System.out.println(sum);
		XPacket firstPacket = XPacket.parseXPacket("[[2]]");
		allPackets.add(firstPacket);
		XPacket secondPacket = XPacket.parseXPacket("[[6]]");
		allPackets.add(secondPacket);
		List<XPacket> sorted = allPackets.stream().sorted().toList();
		int idx1 = sorted.indexOf(firstPacket) + 1;
		int idx2 = sorted.indexOf(secondPacket) + 1;
		System.out.println(idx1);
		System.out.println(idx2);
		System.out.println(idx1 * idx2);


	}
}
