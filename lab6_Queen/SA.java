package lab6_Queen;

public class SA {
	public Node excute(Node initialState, double temperature, double coolingRate) {
		double T = temperature;
		Node current = initialState;
		Node next;
		double deltaE;
		while (current.getH() != 0) {
			next = current.selectNextRandomCandidate();
			deltaE = next.getH() - current.getH();
			if (deltaE < 0)
				current = next;
			else if (Math.exp(deltaE / T) > Math.random())
				current = next;
			T *= coolingRate;
		}
		return current;
	}
}
