package game_2048;

import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;

public class Square extends PApplet {
	// the value that the square holds
	int val;
	int[] pos = new int[2];
	// x and y coordinates of the square in terms of pixels
	int x = (int)this.getCoords()[1];
	int y = (int)this.getCoords()[0];
	private static Map<Integer, Integer[]> colors = new HashMap<Integer, Integer[]>();
	// width and height of each square in terms of pixels
	public float width = 120;
	public float height = 120;
	// neighbors of the square
	Square left;
	Square right;
	Square up;
	Square down;
	
	// a square has a value (the number in the square) and a position
	public Square(int val, int[] pos) {
		this.val = val; this.pos = pos;
	}
	
	
	// move the square in 1 of 4 directions
	public void move(Direction d) {
		if (d == Direction.up) {
			this.pos[0] -= 1;
		}
		else if (d == Direction.down) {
			this.pos[0] += 1;
		}
		else if (d == Direction.left) {
			this.pos[1] -= 1;
		}
		else if (d == Direction.right) {
			this.pos[1] += 1;
		}
	}
	
	// get the board coordinates of the square
	// in terms of pixels (translates from array indices to pixels)
	public float[] getCoords() {
		return new float[] {(this.pos[0] * 120) + ((this.pos[0] + 1) * 5), (this.pos[1] * 120) + ((this.pos[1] + 1) * 5)};
	}
	
	
	// get the color of the square
	public Integer[] getColor() {
		return colors.get(this.val);
	}
	
	
	//set the color of the text on the square
	public static void setColor(Integer val, Integer[] color) {
		colors.put(val, color);
	}
	
	// get the color of the text on the square
	public int[] getTextColor() {
		if (this.val == 2 || this.val == 4) {
			return new int[] {119, 110, 101};
		}
		else {
			return new int[] {249, 246, 242};
		}
	}
	
	// get an array of neighbors
	public Square[] getNeighbors() {
		return new Square[] {this.up, this.down, this.left, this.right};
	}
	
	
	// slide the squares on the board to a specific direction
	// for animation
	void slide() {
		if (this.x < this.getCoords()[0]) {
			this.x += 2;
		}
		else if (this.x > this.getCoords()[0]) {
			this.x -= 2;
		}
		else if (this.y < this.getCoords()[1]) {
			this.y += 2;
		}
		else if (this.y > this.getCoords()[1]) {
			this.y -= 2;
		}
	}
	
	// helper function that writes the neighbors of a square to help debug
	public void writeNeighbors() {
		System.out.println("UP: " + this.up);
		System.out.println("DOWN: " + this.down);
		System.out.println("LEFT: " + this.left);
		System.out.println("RIGHT: " + this.right);
		
	}
	
	// representation function
	public String toString() {
		return "" + val;
	}
	
}
