package be.ccfun.day12;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ShortestPath extends Thread {

	private List<Position> positions;
	private Position start;
	private int shortestPath = Integer.MAX_VALUE;

	public ShortestPath(List<Position> positions, Position start) {
		this.positions = positions;
		this.start = start;
	}

	@Override
	public void run() {
		// Create a queue for BFS
		LinkedList<Position> queue = new LinkedList<>();
		// Mark the current node as visited and enqueue it
		start.setVisited(true);
		queue.add(start);
		Position curr = null;
		boolean finished = false;
		while (queue.size() != 0 && !finished) {
			// Dequeue a vertex from queue and print it
			curr = queue.poll();
			//System.out.println(curr);

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			Iterator<Position> i = curr.getAdjacent(positions).listIterator();
			while (i.hasNext() && !finished) {
				Position p = i.next();
				if (p.isDestination()) {
					finished = true;
					shortestPath = curr.getDistance() + 1;
				} else if (!p.isVisited()) {
					p.setVisited(true);
					p.setDistance(curr.getDistance() + 1);
					queue.add(p);
				}
			}
		}
	}

	public int getShortestPath() {
		return shortestPath;
	}
}
