package lab5_8;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStar implements IPuzzleAlgo{

	@Override
	public Node execute(Puzzle model) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByF);
		List<Node> explored = new ArrayList<Node>();
		Node start = new Node(model.getInitialState());
		start.setH(model.computeH1(start));
		System.out.println(start.getG());
		frontier.add(start);
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			explored.add(current);
			if(current.getH()==0) return current;
			else {
				for(Node child: model.getSuccessors(current)) {
					if(!frontier.contains(child)&& !explored.contains(child)) {
//						child.setG(current.getG()+1);
						frontier.add(child);
					}else if(frontier.contains(child) && !explored.contains(child)) {
						for(Node node:frontier) {
							if(current.equals(node) && current.getF() < node.getF()) {
								frontier.remove(node);
								frontier.add(child);
								break;
							}
						}
					}
				}
			}
		}
		return null;
	}

}
