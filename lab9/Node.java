package lab9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
	private List<Integer> data = new ArrayList<Integer>();
	private int value;
	private Node bestNextMove;
	public void add(Integer val) {
		this.data.add(val);
	}

	public void addAll(List<Integer> data) {
		this.data.addAll(data);
	}

	// Get children of the current nodes
	public List<Node> getSuccessors() {
		// Enter your code here

		List<Node> re = new ArrayList<>();

		for (int i = 0; i < data.size(); i++) {
			int current = data.get(i);
			for (int j = 1; j < current / 2; j++) {
				if (j != current / 2) {
					Node n = new Node();
					n.add(i);
					n.add(current - j);
					Collections.sort(n.data);
					re.add(n);
				}
				
			}
		}
		
		return re;
	}

	// Check whether a node is terminal or not
	public boolean isTerminal() {
		// Enter your code here
		Collections.sort(data);
		if (data.get(0) <= 2)
			return true;
		return false;
	}

	public int getValue() {
		return isTerminal() ? (data.size() % 2 == 0) ? (value = 0) : (value = 1) : value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getBestNextMove() {
		return bestNextMove;
	}

	public void setBestNextMove(Node bestNextMove) {
		this.bestNextMove = bestNextMove;
	}
	public static final Comparator<Integer> DESCOMPARATOR = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	};

	@Override
	public String toString() {
		Collections.sort(this.data, DESCOMPARATOR);
		return this.data.toString();
	}

}
