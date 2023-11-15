package lab6_Queen;

public class Queen {
	private int row;
	private int column;

	public Queen(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public void move() {
		// Enter your code here
		if(row == Node.N -1) row = 0;
		row +=1;
		
	}

	// check whether this Queen can attack the given Queen (q)
	public boolean isConflict(Queen q) {
		// Enter your code here
		if (this.row == q.row || this.column == q.column || Math.abs(this.column - q.column) == Math.abs(this.row - q.row))return true;
			
		return false;
	}

	public int getRow() {
		return row;
		
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
	public static void main(String[] args) {
		Queen q1 = new Queen(0, 0);
		Queen q2 = new Queen(1, 1);
		System.out.println(q1.isConflict(q2));
		
	}
}
