import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;

public class Square extends PApplet {
	int val;
	int[] pos = new int[2];
	int x = (int)this.getCoords()[1];
	int y = (int)this.getCoords()[0];
	private static Map<Integer, Integer[]> colors = new HashMap<Integer, Integer[]>();
	public float width = 120;
	public float height = 120;
	Square left;
	Square right;
	Square up;
	Square down;
	
	public Square(int val, int[] pos) {
		this.val = val; this.pos = pos;
	}
	
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
	
	public float[] getCoords() {
		return new float[] {(this.pos[0] * 120) + ((this.pos[0] + 1) * 5), (this.pos[1] * 120) + ((this.pos[1] + 1) * 5)};
	}
	
	public Integer[] getColor() {
		return colors.get(this.val);
	}
	
	public static void setColor(Integer val, Integer[] color) {
		colors.put(val, color);
	}
	
	public int[] getTextColor() {
		if (this.val == 2 || this.val == 4) {
			return new int[] {119, 110, 101};
		}
		else {
			return new int[] {249, 246, 242};
		}
	}
	
	public Square[] getNeighbors() {
		return new Square[] {this.up, this.down, this.left, this.right};
	}
	
//	void display() {
//    	Integer[] color = this.getColor();
//    	fill(color[0], color[1], color[2]);
//    	rect(x, y, this.width, this.height);
//    	int[] textColor = this.getTextColor();
//    	fill(textColor[0], textColor[1], textColor[2]);
//    	textSize(80);
//    	text(String.valueOf(this.val), (float)(x + 40), (float)(y + 80));
//    	this.slide();
//	}
	
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
	
	public void writeNeighbors() {
		System.out.println("UP: " + this.up);
		System.out.println("DOWN: " + this.down);
		System.out.println("LEFT: " + this.left);
		System.out.println("RIGHT: " + this.right);
		
	}
	
	public String toString() {
		return "" + val;
	}
	
}
