package lab5_8;

import java.util.Arrays;
import java.util.List;

public class TestNode {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();
		p.readInput("src/lab5_8/PuzzleMap.txt", "src/lab5_8/PuzzleGoalState.txt");

	//	Node initialState = p.getInitialState();
	// tmp = new Node(initialState);
	//	System.out.println(initialState.equals(tmp));
//		Node goalState = p.getGoalState();
//		System.out.println(p.getInitialState());
	//	System.out.println("H: "+initialState.getH());
//		System.out.println(Arrays.toString(initialState.getWhiteTilePosition()));
//		System.out.println(p.getGoalState());
	//	Node re = p.moveWhiteTile(initialState, 'r');
//
//		System.out.println(re); 
//		System.out.println(re.getH());
//		System.out.println(initialState);
		
//		System.out.println(Arrays.toString(re.getWhiteTilePosition()));
//		System.out.println(p.computeH(init, goal));

		// System.out.println(p.getInitialState());
		// System.out.println(p.getGoalState());
		//
		// List<Node> children = p.getSuccessors(initialState);
		// System.out.println("Size: "+children.size());
		// for (Node child : children) {
		// System.out.println(child.getH()+" "+p.computeH(child) );
		// }
//		DepthFirstSearch de= new DepthFirstSearch(); //cái này hơi lâu, lâu ác luon á chớ
//		Node re = de.execute(p);
//		System.out.println(re);
//		BreadthFirstSearch be= new BreadthFirstSearch();
//		Node re = be.execute(p);
//		System.out.println(re);
//		GreedyBestFirstSearch gr = new GreedyBestFirstSearch(); 
//		Node result = gr.execute(p);
//		System.out.println(result);
		AStar astar = new AStar(); 
		Node result = astar.execute(p);
		System.out.println(result);
	}
}
