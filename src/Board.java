import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Board {
	private int boardSize = 4;
	public List<Square> squares = new ArrayList<Square>();
	public Square[][] grid = new Square[boardSize][boardSize];
	
	public Board() {
		this.addSquare();
	}
	
	public boolean gameOver() {
		if (squares.size() == 16) {
			return true;
		}
		return false;
	}
	
	public void addSquare() {
		squares.add(generateSquare());
		updateGrid();
	}
	
	public void moveSquares (Direction d) {
		if (d == Direction.up) {
			System.out.println("moving up");
			
			for (int i = 0; i < boardSize; i++) {
				for (int j = 0; j < boardSize; j++) {
					if (grid[i][j] != null) {
						Square s  = grid[i][j];
						while(withinBounds(s, d) && s.up == null) {
							// get the neighbors before moving to guarantee that the square can move
							setNeighbors(s);
							s.move(d);
						}
					}
				}
				// update the grid after moving each square so that next square can move
				updateGrid();
			}
		}
		
		if (d == Direction.right) {
			System.out.println("moving right");
			
			for (int i = 0; i < boardSize; i++) {
				for (int j = boardSize - 1; j >= 0; j--) {
					if (grid[i][j] != null) {
						Square s  = grid[i][j];
						while(withinBounds(s, d) && s.right == null) {
							setNeighbors(s);
							s.move(d);
						}
					}
				}
				updateGrid();
			}
		}
		
		if (d == Direction.left) {
			System.out.println("moving left");
			
			for (int i = 0; i < boardSize; i++) {
				for (int j = 0; j < boardSize; j++) {
					if (grid[i][j] != null) {
						Square s  = grid[i][j];
						while(withinBounds(s, d) && s.left == null) {
							setNeighbors(s);
							s.move(d);
						}
					}
				}
				updateGrid();
			}
		}
		
		if (d == Direction.down) {
			System.out.println("moving down");
			
			for (int i = boardSize - 1; i >= 0; i--) {
				for (int j = 0; j < boardSize; j++) {
					if (grid[i][j] != null) {
						Square s  = grid[i][j];
						while(withinBounds(s, d) && s.down == null) {
							setNeighbors(s);
							s.move(d);
						}
					}
				}
				updateGrid();
			}
		}
	}
	
	private void setNeighbors(Square s) {
		for(Square q : this.squares) {
			if (isAdjacent(q, s)) {
				if(q.pos[0] == s.pos[0] + 1) {
					s.right = q;
				}
				else if (q.pos[0] == s.pos[0] - 1) {
					s.left = q;
				}
				else if (q.pos[1] == s.pos[1] + 1) {
					s.down = q;
				}
				else {
					s.up = q;
				}				
			}
		}
	}
	
	private boolean isAdjacent(Square s1, Square s2) {
		if ((s1.pos[0] == s2.pos[0] + 1 || s1.pos[0] == s2.pos[0] - 1) && s1.pos[1] == s2.pos[1]) {
			return true;
		}
		else if ((s1.pos[1] == s2.pos[1] + 1 || s1.pos[1] == s2.pos[1] - 1) && s1.pos[0] == s2.pos[0]) {
			return true;
		}
		return false;
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
	
	private void updateGrid() {
		clearGrid();
		for (Square s : this.squares) {
			this.grid[s.pos[0]][s.pos[1]] = s;
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
}
