package lab2_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo{

	@Override
	public Node execute(Node root, String goal) {
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(root);
		List<Node> explored = new ArrayList<Node>();
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			if (current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);

			List<Node> children = current.getChildrenNodes();
			for(int i=children.size()-1; i >=0; i--) {
				if(!frontier.contains(children.get(i))&& !explored.contains(children.get(i))) {
					explored.add(children.get(i));
					children.get(i).setParent(current);
					
				}
			}
		}
		return null;
	}
	

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
