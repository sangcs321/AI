package lab2_3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearchAlgo implements ISearchAlgo{

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(root);
		List<Node> explored = new ArrayList<Node>();
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);
			List<Node> children = current.getChildrenNodes();
			for(Node child: children) {
				if(!frontier.contains(child)&& !explored.contains(child)) {
					frontier.add(child);
					child.setParent(current);
				}
			}
		}
		return null;} //null if the goal is not found
		
	

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		Node startNode = findNode(root, start);
		if(startNode!=null) {
			return execute(startNode, goal);
		}
		return null;// null if not found
	}
	private Node findNode(Node root, String label) {
		if(root.getLabel().equals(label)) {
			return root;
		}
		for(Node child:root.getChildrenNodes()) {
			Node result = findNode(child,label);
			if(result!=null) {
				return result;
			}
		}
		return null;
	}

}
