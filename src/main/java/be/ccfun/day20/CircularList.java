package be.ccfun.day20;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CircularList {
	private List<Node> elements = new ArrayList<>();
	private int size = 0;

	public CircularList(String values){
		String[] split = values.split(",");
		for (String s : split) {
			add(Integer.parseInt(s.trim()));
		}
	}

	public int getSize() {
		return size;
	}

	public void add(int number) {
		elements.add(new Node(number, size++));
	}

	public int translateIdx(int idx) {
		for (int i = 0; i <elements.size(); i++) {
			if (elements.get(i).getOriginalIdx() == idx) {
				return i;
			}
		}
		return Integer.MAX_VALUE;
	}

	public void move(int idx) {
		if (idx == 4) {
			System.out.println("in debug");
		}
		idx = translateIdx(idx);
		Node toRemove = elements.remove(idx);
		int newIdx = Math.floorMod(idx + toRemove.getValue(), size - 1);
		if (newIdx == 0) {
			newIdx = size - 1;
		}
		elements.add(newIdx, toRemove);
	}

	CircularList() {

	}

	public int getIndex(int idx) {
		idx = Math.floorMod(idx, size);
		return elements.get(idx).getValue();
	}

	public int getIdxOfNumber(int number) {
		for (int i = 0; i <elements.size(); i++) {
			if (elements.get(i).getValue() == number) {
				return i;
			}
		}
		return Integer.MAX_VALUE;
	}

	@Override
	public String toString() {
		return elements.stream().map(n -> String.valueOf(n.getValue())).collect(Collectors.joining( "," ));
	}
}
