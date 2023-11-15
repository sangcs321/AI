package lab6_Queen;

import java.util.List;

public class testQueen {
	public static void main(String[] args) {
//		Node node = new Node();
//		node.generateBoard();
//		HillClimbingSearch hc = new HillClimbingSearch();
//		
//		node.displayBoard();
//		System.out.println("H ban dau: "+node.getH());
//		
//		System.out.println("-----------------------------");
//		
////		node.getBestCandidate().displayBoard();
//		
////		System.out.println(node.getBestCandidate().getH());
////		hc.excute(node).displayBoard();
//	
//		hc.excuteWithRandomRestart(node).displayBoard();
//		System.out.println("H: "+hc.excuteWithRandomRestart(node).getH());
//	
//		
//		hc.printStepClimbed();
		//test SA
		Node initialState = new Node();
		initialState.generateBoard();
		initialState.displayBoard();
		System.out.println("H = " + initialState.getH());
		SA sa = new SA();
		System.out.println("————————————————————");
		Node re = sa.excute(initialState, 1000, 0.995);
		System.out.println("H = " + re.getH());
		re.displayBoard();
	}
}
