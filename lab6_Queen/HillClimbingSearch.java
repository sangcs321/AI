package lab6_Queen;

public class HillClimbingSearch {
	int stepClimbed = 0;
	int stepClimbedAfterRandomRestart = 0;
	int randomRestarts = 0;

	public Node excute(Node initialState) {
		Node current = new Node(initialState);
		Node neighbor = null;
		if (randomRestarts > 1)
			stepClimbedAfterRandomRestart = 0;

		while (true) {
			neighbor = current.getBestCandidate();
			if (neighbor.getH() >= current.getH()) return neighbor;
			else{
				current = neighbor;
				++stepClimbed;
				if (randomRestarts > 0)
					stepClimbedAfterRandomRestart++;
			}
			return current;

		}
	}
	public Node excuteWithRandomRestart(Node initialState) {
		Node state = excute(initialState);
		while (state.getH() != 0) {
			state.generateBoard();
			randomRestarts++;
			state = excute(state);
		}
		return state;
	}
	public void printStepClimbed() {
		System.out.println("stepClimbed = " + stepClimbed);
		System.out.println("stepClimbedAfterRandomRestart = " + stepClimbedAfterRandomRestart);
		System.out.println("randomRestarts = " + randomRestarts);
	}
}
