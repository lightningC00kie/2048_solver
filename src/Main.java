
//import java.util.Random;

import processing.core.*;

public class Main extends PApplet{
	
	Board board = new Board();
	public static void main(String[] args) {
//		Direction[] dirs = {Direction.up, Direction.down, Direction.right, Direction.left};
//		Random rnd = new Random();
//		
//		Player player = new Player(board);
//		int index;
//		Direction d;
//		for (int i = 0; i < 10; i++) {
//			index = rnd.nextInt(4);
//			d = dirs[index];
//			player.makeMove(d);
//		}

		PApplet.main("Main");
	}

	public void settings() {
        size(505,505);
	}

    public void setup() {
        Integer[][] colors = new Integer[11][3];
        colors[0] = new Integer[] {238, 228, 218};
        colors[1] = new Integer[] {237, 224, 200};
        colors[2] = new Integer[] {242, 177, 121};
        colors[3] = new Integer[] {245, 149, 99};
        colors[4] = new Integer[] {246, 124, 96};
        colors[5] = new Integer[] {246, 94, 59};
        colors[6] = new Integer[] {237, 207, 115};
        colors[7] = new Integer[] {237, 204, 98};
        colors[8] = new Integer[] {237, 200, 80};
        colors[9] = new Integer[] {237, 197, 63};
        colors[10] = new Integer[] {237, 194, 45};
        for (int i = 0; i < 11; i++) {
        	Square.setColor((int)Math.pow(2, i+1), colors[i]);
        }        
    }

    public void draw() {
        background(165, 144, 129);
        drawGrid();

        for (Square s : board.squares) {
            drawSquare(s);
        }

    }
    
    public void drawGrid() {
    	noStroke();
    	fill(193, 175, 158);
    	float startY = 5;
    	float startX = 5;
    	float width = 120;
    	float height = 120;
    	float padding = 5;
    	for (int i = 0; i < 4; i++) {
    		for (int j = 0; j < 4; j++) {
    			rect(startX, startY, width, height);
    			startX += width + padding;
    		}
			startY += (height + padding);
			startX = padding;
    	}
    }

    public void drawSquare(Square s) {
    	float[] coords = s.getCoords();
    	Integer[] color = s.getColor();
    	fill(color[0], color[1], color[2]);
    	rect(coords[1], coords[0], s.width, s.height);
    	int[] textColor = s.getTextColor();
    	fill(textColor[0], textColor[1], textColor[2]);
    	if (s.val == 2 || s.val == 4 || s.val == 8) {
    		textSize(70);
    		text(String.valueOf(s.val), (float)(coords[1] + 40), (float)(coords[0] + 80));
    	}
    	else {
    		textSize(60);
    		text(String.valueOf(s.val), (float)(coords[1] + 25), (float)(coords[0] + 80));
    	}
    	
    }
    
    public void keyPressed() {
	    if (keyCode == UP || key == 'w') {
    	  System.out.println("up");
	      board.moveSquares(Direction.up);
	    } 
	    if (keyCode == DOWN || key == 's') {
  	      board.moveSquares(Direction.down);
  	    } 
	    if (keyCode == LEFT || key == 'a') {
	      board.moveSquares(Direction.left);
	    } 
	    if (keyCode == RIGHT || key == 'd') {
	      board.moveSquares(Direction.right);
	    }
	    
	    if (board.gameOver()) {
	    	exit();
	    }
    }
}
