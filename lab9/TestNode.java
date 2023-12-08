package lab9;

import java.util.Arrays;
import java.util.List;

public class TestNode {
	public static void main(String[] args) {
		Node node = new Node();
		Integer[] data = { 7 };
		node.addAll(Arrays.asList(data));

		MinimaxAlgo algo = new MinimaxAlgo();
		System.out.println("Numbers of tokens = 7");
		System.out.print("Value = ");
		algo.execute(node);
		System.out.println("Best next move for MIN: " + node.getBestNextMove());
		System.out.println("——————————————————————————————————");
	}
}