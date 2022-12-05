package be.ccfun.day5;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ContainerStack {
	private Deque<String> stack = new LinkedList<>();


	public void add(String element) {
		stack.offerFirst(element);
	}

	public void push(String element) {
		stack.offerLast(element);
	}

	public String get() {
		return stack.pollLast();
	}

	public void push(List<String> elements) {
		for (String elt: elements) {
			push(elt);
		}
	}

	public List<String> get(int number) {
		Deque<String> packs = new LinkedList<>();
		for (int i = 0; i < number; i++) {
			packs.offerFirst(stack.pollLast());
		}
		return packs.stream().toList();
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (String value : stack) {
			s.append(value).append(" ");
		}
		return s.toString();
	}
}
