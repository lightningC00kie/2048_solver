
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
	
	public Direction bestMove() throws CloneNotSupportedException{
//		int maxMerges = 0;
//		Direction bestDirection = null;
//		for (Direction d : Direction.values()) {
//			int merges = b.countMerges(d);
//			if (maxMerges < merges) {
//				maxMerges = merges;
//				bestDirection = d;
//			}
//		}
//		
//		return bestDirection;
		return minimizeSquares();
	}
	
	// solver that just picks one direction and never goes in that direction unless necessary
	public void dumbSolver(Direction d) {
		boolean hasMoved = false;
		for (Direction dd : Direction.values()) {
			if (!dd.equals(d)) {
				if(b.moveSquares(dd)) {
					hasMoved = true;
				}
			}
		}
		if (!hasMoved && b.canMove()) {
			b.moveSquares(d);
			
		}
	}
	
	public Direction minimizeSquares() throws CloneNotSupportedException {
		int minSquares = b.squares.size();
		Direction minDir = Direction.up;
		for (Direction d : Direction.values()) {
			Board bClone = (Board) b.clone();
			bClone.moveSquares(d);
			if (bClone.squares.size() < minSquares) {
				minDir = d;
				minSquares = bClone.squares.size();
			}
		}
		System.out.println(minDir);
		return minDir;
	}
	
	
	
}
