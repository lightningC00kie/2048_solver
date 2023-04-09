
public class Player {
	Board b;
	
	public Player (Board b) {
		this.b = b;
	}
	
	public void makeMove(Direction d) {
		b.moveSquares(d);
		b.addSquare();
		b.writeBoard();
	}
	
}
