package game_2048;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.Random;

public class Board implements Cloneable {
	private int boardSize = 4;
	public List<Square> squares = new ArrayList<Square>();
	public Square[][] grid = new Square[boardSize][boardSize];
	
	public Board() {
		this.addSquare();
	}
	
	// the game is over if there are 16 squares on the board
	// i.e. there is no more room for new squares
	// and there is no move that you can make
	public boolean gameOver() {
		if (squares.size() == 16 && !canMove()) {
			return true;
		}
		return false;
	}
	
	// generate a square and then add it to the squares of the board
	// then update the grid to make sure that the neighbors of each square
	// are updated
	public void addSquare() {
		squares.add(generateSquare());
		updateGrid();
	}
	
	
	// main function to move all squares on the board
	// the function checks for each direction because
	// the order of moving the squares is important 
	// and is dependent on directions. It moves each
	// square individually by updating the coordinates
	// and checks if any squares are neighbors with the same
	// value which would mean that they can merge. If they
	// can be merged, the squares merge in the direction of 
	// movement.
	public boolean moveSquares (Direction d) {
		boolean hasMoved = false;
		if (d == Direction.up) {
			
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
		return hasMoved;
	}
	
	private void resetNeighbors(Square s) {
		s.up = null;
		s.down = null;
		s.left = null;
		s.right = null;
	}
	
	// reset the neighbor values for the square
	// by checking surrounding squares
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
	
	// generate a new square
	// the square with number 2 is generated 80% of the time
	// the square with number 4 is generated 20% of the time
	private Square generateSquare() {
		Random rand = new Random();
		double val = rand.nextDouble();
		int[] pos = getEmptySquare();
		if (val <= 0.10) {
			return new Square(4, pos);
		}
		return new Square(2, pos);
	}
	
	// return a random empty square from the board
	// returns a position on the board
	// called when the player moves and a new square needs
	// to be generated in an new random position
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
	
	// update the grid array based on the list of squares
	// called when a move is made to remove old squares and
	// add the new ones
	public void updateGrid() {
		clearGrid();
		
		for (Square s : this.squares) {
			this.grid[s.pos[0]][s.pos[1]] = s;
		}
		
		for (Square s : this.squares) {
			setNeighbors(s);
		}
	}
	
	// remove all squares from the grid array
	private void clearGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				this.grid[i][j] = null;
			}
		}
	}
	
	// check if a square falls within the borders of the board
	// after movement in a specific direction.
	// if it is it returns true otherwise it returns false
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
	
	// debugging helper to write the board on the terminal
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
	
	
	// takes two squares and forms one square
	// from the values of the 2 squares merged to each other
	// Then adds this square to the grid and squares list
	// and removes the old squares
	private void mergeSquares(Square s1, Square s2) {
		if (s1.val != s2.val) {
			return;
		}
		
		this.squares.add(new Square(s1.val + s2.val, s1.pos));
		this.squares.remove(s1);
		this.squares.remove(s2);
		updateGrid();
	}
	
	// returns true if the board can move in any direction
	// a move is any movement of squares or merging of any squares
	public boolean canMove() {
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
	
	//return a clone of the board. Used for solver.
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	// counts the number of merges in a position on the board
	// used for the solver
	public int countMerges(Direction d) {
		int merges = 0;
		if (d == Direction.right) {
			for (int i = 0; i < boardSize; i++) {
				for (int j = boardSize - 1; j >= 0; j--) {
					if (grid[i][j] != null) {
						Square s  = grid[i][j];
						if (s.right != null && s.val == s.right.val){
							merges++;
						}
					}
				}
			}
		}
		return merges;
	}
	
	
}
