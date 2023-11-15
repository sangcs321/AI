package lab6_Queen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Node {
	public static final int N = 8;
	private Queen[] state;

	public Node() {
		// generateBoard();
		state = new Queen[N];
	}

	public Node(Queen[] state) {
		this.state = new Queen[N];
		for (int i = 0; i < state.length; i++) {
			this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
		}
	}

	public Node(Node n) {
		this.state = new Queen[N];
		for (int i = 0; i < N; i++) {
			Queen qi = n.state[i];
			this.state[i] = new Queen(qi.getRow(), qi.getColumn());
		}
	}

	public void generateBoard() {
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			state[i] = new Queen(random.nextInt(N), i);
		}
	}

	public int getH() {
		int heuristic = 0;
		// Enter your code here
		for (int i = 0; i < N ; i++) {
			for (int j = i + 1; j < N; j++) {
//				if (state[i].isConflict(state[j]))
//					return ++heuristic;
				heuristic = (state[i].isConflict(state[j])) ? ++heuristic : heuristic;
			}
		}
		return heuristic;
	}

	public List<Node> generateAllCandidates() {
		List<Node> result = new ArrayList<Node>();

		// Enter your code here
		for(int i = 0; i < N; i++) {
			Node copy = new Node(this.state);
			copy.state[i].move();
			result.add(copy);
		}
		return result;
	}

	public Node selectNextRandomCandidate() {
		// Enter your code here
		Random r = new Random();
		int i = r.nextInt(N);
		int row = r.nextInt(N);
		Node n = new Node(state);
		n.state[i].setRow(row);
		return n;
	}

	public void displayBoard() {
		int[][] board = new int[N][N];
		// set queen position on the board
		for (int i = 0; i < N; i++) {
			board[state[i].getRow()][state[i].getColumn()] = 1;
		}
		// print board
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					System.out.print("Q" + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
	}
	public Node getBestCandidate() {
		List<Node> neighbor = generateAllCandidates();
		int min = Integer.MAX_VALUE;
		Node re = null;
		for (Node node : neighbor) {
			if(node.getH()<min) {
				min = node.getH();
				re = node;
			}
		}
		
		return re;
//		List<Node> list = generateAllCandidates();
//		Node result = list.get(0);
//		int maxH = list.get(0).getH();	
//		for (int i = 1; i < N; i++) {
//			Node current = list.get(i);
//			int v = current.getH();
//			if(maxH < v) {
//				maxH = v;
//				result = current;
//			}
//		}
//		return result;
//		
	}
	public static void main(String[] args) {
		Node node = new Node();
		node.generateBoard();
		node.displayBoard();
		int h=node.getH();
		System.out.println(h);
	}
	
}
