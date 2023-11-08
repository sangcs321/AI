package lab5_8;

import java.net.SecureCacheResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.text.AttributeSet.FontAttribute;

public class GreedyBestFirstSearch implements IPuzzleAlgo {

	@Override
	public Node execute(Puzzle model) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByH);
		List<Node> explored = new ArrayList<Node>();

		Node start = new Node(model.getInitialState());
		start.setH(model.computeH2(start));
		frontier.add(start);

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			explored.add(current);
			if (current.getH() == 0)
				return current;
			else {
				for (Node child : model.getSuccessors(current)) {
					if (!frontier.contains(child) && !explored.contains(child)) {
						frontier.add(child);
					} else if (frontier.contains(child) && !explored.contains(child)) {
						for (Node node : frontier) {
							if (current.equals(node) && current.getH() < node.getH()) {
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
