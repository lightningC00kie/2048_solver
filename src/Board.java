import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.Random;

public class Board {
	private int boardSize = 4;
	public List<Square> squares = new ArrayList<Square>();
	public Square[][] grid = new Square[boardSize][boardSize];
//	private Direction prevMove;
	public Board() {
		this.addSquare();
	}
	
	public boolean gameOver() {
		if (squares.size() == 16 && !canMerge()) {
			return true;
		}
		return false;
	}
	
	public void addSquare() {
		squares.add(generateSquare());
		updateGrid();
		writeBoard();
	}
	
	public void moveSquares (Direction d) {
		boolean hasMoved = false;
		if (d == Direction.up) {
			System.out.println("moving up");
			
			for (int i = 0; i < boardSize; i++) {
				for (int j = 0; j < boardSize; j++) {
					if (grid[i][j] != null) {
						Square s  = grid[i][j];
						while(withinBounds(s, d)) {
							// get the neighbors before moving to guarantee that the square can move
							if (s.up == null) {
								s.move(d);
								setNeighbors(s);
								updateGrid();	
							}
							else if (s.val == s.up.val){
								mergeSquares(s.up, s);
								break;
							}
							else {
								break;
							}
							hasMoved = true;
						}
					}
				}
				// update the grid after moving each square so that next square can move
			}
		}
		
		if (d == Direction.right) {
			System.out.println("moving right");
			
			for (int i = 0; i < boardSize; i++) {
				for (int j = boardSize - 1; j >= 0; j--) {
					if (grid[i][j] != null) {
						Square s  = grid[i][j];
						while(withinBounds(s, d)) {
							if (s.right == null) {
								s.move(d);
								setNeighbors(s);
								updateGrid();	
							}
							else if (s.val == s.right.val){
								mergeSquares(s.right, s);
								break;
							}
							else {
								break;
							}
							hasMoved = true;
						}
					}
				}
			}
		}
		
		if (d == Direction.left) {
			System.out.println("moving left");
			
			for (int i = 0; i < boardSize; i++) {
				for (int j = 0; j < boardSize; j++) {
					if (grid[i][j] != null) {
						Square s  = grid[i][j];
						while(withinBounds(s, d)) {
							if (s.left == null) {
								s.move(d);
								setNeighbors(s);
								updateGrid();	
							}
							else if (s.val == s.left.val){
								mergeSquares(s.left, s);
								break;
							}
							else {
								break;
							}
							hasMoved = true;
						}
					}
				}
			}
		}
		
		if (d == Direction.down) {
			System.out.println("moving down");
			
			for (int i = boardSize - 1; i >= 0; i--) {
				for (int j = 0; j < boardSize; j++) {
					if (grid[i][j] != null) {
						Square s  = grid[i][j];
						while(withinBounds(s, d)) {
							if (s.down == null) {
								s.move(d);
								setNeighbors(s);
								updateGrid();	
							}
							else if (s.val == s.down.val){
								mergeSquares(s.down, s);
								break;
							}
							else {
								break;
							}
							hasMoved = true;
						}
					}
				}
			}
		}
		
		if (hasMoved && !gameOver() && squares.size() < 16) {
			addSquare();
		}
		updateGrid();
	}
	
	private void resetNeighbors(Square s) {
		s.up = null;
		s.down = null;
		s.left = null;
		s.right = null;
	}
	
	private void setNeighbors(Square s) {
		resetNeighbors(s);
		int i = s.pos[0];
		int j = s.pos[1];
		if (withinBounds(s, Direction.down)) {
			s.down = grid[i + 1][j];
		}
		if (withinBounds(s, Direction.right)) {
			s.right = grid[i][j + 1];
		}
		if (withinBounds(s, Direction.left)) {
			s.left = grid[i][j - 1];
		}
		if (withinBounds(s, Direction.up)) {
			s.up = grid[i - 1][j];			
		}
		
	}
	
	private Square generateSquare() {
		Random rand = new Random();
		int val = rand.nextInt(2);
		int[] pos = getEmptySquare();
		if (val == 0) {
			return new Square(2, pos);
		}
		return new Square(4, pos);
	}
	
	private int[] getEmptySquare() {
		List<Integer[]> emptySquares = new ArrayList<Integer[]>();
		Random rand = new Random();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == null) {
					emptySquares.add(new Integer[] {i, j});
				}
			}
		}
	
		Integer[] square = emptySquares.get(rand.nextInt(emptySquares.size()));
		return new int[] {square[0], square[1]};
	}
	
	public void updateGrid() {
		clearGrid();
		
		for (Square s : this.squares) {
			this.grid[s.pos[0]][s.pos[1]] = s;
		}
		
		for (Square s : this.squares) {
			setNeighbors(s);
		}
	}
	
	private void clearGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				this.grid[i][j] = null;
			}
		}
	}
	
	public boolean withinBounds(Square s, Direction d) {
		if (d == Direction.up && s.pos[0] - 1 < 0) {
			return false;
		}
		else if (d == Direction.down && s.pos[0] + 1 > boardSize - 1) {
			return false;
		}
		else if (d == Direction.left && s.pos[1] - 1 < 0) {
			return false;
		}
		else if (d == Direction.right && s.pos[1] + 1 > boardSize - 1) {
			return false;
		}
		return true;
	}
	
	public void writeBoard() {
		for (Square[] row : grid) {
			for (Square s : row) {
				if (s == null) {
					System.out.print("-");
				}
				else {
					System.out.print(s);	
				}
			}
			System.out.println();
		}
	}
	
	private void mergeSquares(Square s1, Square s2) {
		if (s1.val != s2.val) {
			return;
		}
		
		this.squares.add(new Square(s1.val + s2.val, s1.pos));
		this.squares.remove(s1);
		this.squares.remove(s2);
		updateGrid();
	}
	
	
	private boolean canMerge() {
		Square[] neighbors;
		for (Square s : this.squares) {
			neighbors = s.getNeighbors();
			for (Square neighbor : neighbors) {
				if (neighbor != null && s.val == neighbor.val) {
					return true;
				}
			}
		}
		return false;
	}
	
}
