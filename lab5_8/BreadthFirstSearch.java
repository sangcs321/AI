package lab5_8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch implements IPuzzleAlgo {

	@Override
	public Node execute(Puzzle model) {
		Queue<Node> frontier = new LinkedList<Node>();

		List<Node> explored = new ArrayList<Node>();

		frontier.add(model.getInitialState());
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			explored.add(current);
			if (current.equals(model.getGoalState()))
				return current;

			List<Node> children = model.getSuccessors(current);
			for (Node child : children) {
				if (!frontier.contains(child) && !explored.contains(child))
					frontier.add(child);
			}
		}
		return null;
	}

}
