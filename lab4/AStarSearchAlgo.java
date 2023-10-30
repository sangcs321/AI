package lab4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class AStarSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {

		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByFn());
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
				} else if (frontier.contains(end) && (end.getG() > (current.getG() + child.getWeight()))) {
					end.setParent(current);
					end.setG(current.getG() + child.getWeight());

				}
			}
		}
		return null;

	}

	@Override
	public Node execute(Node root, String start, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByFn());
		List<Node> explored = new ArrayList<Node>();
		boolean started = false;
		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node curr = frontier.poll();
			explored.add(curr);
			if (curr.getLabel().equals(goal))
				if (started)
					return curr;
				else
					return null;

			for (Edge child : curr.getChildren()) {
				Node end = child.getEnd();
				if (!explored.contains(end))
					if (!frontier.contains(end)) {
						if (end.getLabel().equals(start)) {
							started = true;
							end.setParent(null);
							end.setG(0);
							frontier.clear();
							frontier.offer(end);
							break;
						}
						end.setParent(curr);
						end.setG(curr.getG() + child.getWeight());
						frontier.offer(end);
					} else {
						double newG = curr.getG() + child.getWeight();
						if (newG < end.getG()) {
							end.setParent(curr);
							end.setG(newG);
						}
					}
			}
		}
		return null;
	}

	public boolean isAdmissibleH(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByFn());
		List<Node> explored = new ArrayList<Node>();
		boolean result = true;
		frontier.offer(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			explored.add(current);
			Node nodeRe = execute(current, goal);
			if (nodeRe != null) {
				if (current.getH() > nodeRe.getG())
					return false;

				for (Node child : current.getChildrenNodes())
					if (!explored.contains(child) && !frontier.contains(child)) {
						child.setParent(null);
						child.setG(0);
						result = result && isAdmissibleH(child, goal);
						if (!result)
							return result;
					}
			}
		}
		return true;
	}
	
	class NodeComparatorByFn implements Comparator<Node> {
		@Override
		public int compare(Node node1, Node node2) {
			return (Double.compare(node1.getG() + node1.getH(), node2.getG() + node2.getH()) != 0)
					? Double.compare(node1.getG() + node1.getH(), node2.getG() + node2.getH())
					: node1.getLabel().compareTo(node2.getLabel());
		}
	}
}
