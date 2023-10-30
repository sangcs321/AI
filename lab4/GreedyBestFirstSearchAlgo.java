package lab4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		List<Node> explored = new ArrayList<Node>();
		frontier.offer(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal))
				return current;
			else {
				explored.add(current);
			}
			List<Edge> children = current.getChildren();
			for (Edge child : children) {
				Node end = child.getEnd();
				if (!explored.contains(end) && !frontier.contains(end)) {
					end.setParent(current);
					end.setG(current.getG() + child.getWeight());
					frontier.offer(end);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		List<Node> explored = new ArrayList<Node>();
		boolean started = false;
		frontier.offer(root);

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			explored.add(current);
			if (current.getLabel().equals(goal))
				if (started)
					return current;
				else
					return null;

			for (Edge child : current.getChildren()) {
				Node end = child.getEnd();
				if (!explored.contains(end) && !frontier.contains(end)) {
					if (end.getLabel().equals(start)) {
						started = true;
						end.setParent(null);
						end.setG(0);
						frontier.clear();
						frontier.offer(end);
						break;
					}
					end.setParent(current);
					end.setG(current.getG() + child.getWeight());
					frontier.offer(end);
				}
			}

		}
		return null;
	}
	class NodeComparatorByHn implements Comparator<Node> {
		@Override
		public int compare(Node node1, Node node2) {
			return (Double.compare(node1.getH(), node2.getH()) != 0) ? Double.compare(node1.getH(), node2.getH())
					: node1.getLabel().compareTo(node2.getLabel());
		}
	}
	
}
