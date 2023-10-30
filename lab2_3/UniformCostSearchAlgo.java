package lab2_3;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class UniformCostSearchAlgo implements ISearchAlgo{

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier= new PriorityQueue<Node>(new NodeComparator());
		List<Node> explored = new LinkedList<>();
		frontier.offer(root);
		while(!frontier.isEmpty()) {
			Node current=frontier.poll();
			if(current.getLabel().equals(goal)) {
				return current;
			}
			List<Edge> children = current.getChildren();
			for (Edge child : children) {
				Node n = child.getEnd();
				if(!explored.contains(n)&&!frontier.contains(n)) {
					frontier.add(n);
					n.setParent(current);
					n.setPathCost(current.getPathCost()+child.getWeight());
				}else {
					double newPathCost = current.getPathCost()+child.getWeight();
					if(newPathCost<n.getPathCost()&&frontier.contains(n)) {
						n.setParent(current);
						n.setPathCost(newPathCost);
					}
				}
				
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		return null;
	}

}
