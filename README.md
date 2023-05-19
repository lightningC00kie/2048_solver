# 2048 User Documentation

## Introduction

The 2048 game is a single-player puzzle game where the goal is to combine tiles with the same number to reach the tile with the value of 2048. The game is played on a 4x4 grid, and the player can move the tiles in four directions: up, down, left, and right.

## Getting Started

To run the game, follow these steps:

1. Make sure you have Java Development Kit (JDK) installed on your machine.
2. Download the source code files for the 2048 game.
3. Open a terminal or command prompt and navigate to the directory where the source code files are located.
4. Compile the code by running the following command:

   ```
   javac Main.java
   ```

5. Run the game by executing the following command:

   ```
   java Main
   ```

6. The game window will open, and you can start playing.

## Gameplay

The game is played using the arrow keys or the WASD keys. Each key represents a move direction:

- Up: Move tiles upwards.
- Down: Move tiles downwards.
- Left: Move tiles to the left.
- Right: Move tiles to the right.

The objective of the game is to reach the 2048 tile by combining tiles with the same number. When two tiles with the same number collide during a move, they merge into a new tile with twice the value.

The game ends when there are no more valid moves available or when the 2048 tile is reached.

## Code Structure

The game consists of the following classes:

1. **Main.java**: The main class that initializes the game and handles the user interface.
2. **Board.java**: Represents the game board, including the grid and the logic for moving and merging tiles.
3. **Square.java**: Represents an individual tile on the game board.

## Customization

You can customize the appearance and behavior of the game by modifying the code. Here are some possible modifications you can make:

- Adjust the colors of the tiles by changing the RGB values in the `Main.java` file.
- Change the size of the game window by modifying the `size()` function in the `Main.java` file.
- Modify the probabilities of generating different tile values in the `generateSquare()` function in the `Board.java` file.

## Extending the Game

If you want to extend the game and add new features, you can modify the existing code or add new classes. Some possible extensions could include:

- Adding a scoring system to keep track of the player's score.
- Implementing an AI player to automatically play the game.
- Adding animations or effects to enhance the user interface.
- Implementing different game modes or levels of difficulty.

## Conclusion

Congratulations! You now have a basic understanding of the 2048 game and how to run and customize it. Have fun playing the game and exploring different possibilities for extensions and improvements!

# 2048 Developer Documentation

### Class Descriptions
1. `Board`:
   - Represents the game board/grid.
   - Maintains the current state of the board and the positions/values of squares.
   - Provides methods to manipulate the board, such as adding a new square, moving squares, and checking game over conditions.

2. `Square`:
   - Represents an individual square on the game board.
   - Stores the value of the square and its position on the board.
   - Provides methods to update the square value and position.

3. `Main`:
   - Contains the main entry point of the 2048 game.
   - Initializes the game board and starts the game loop.

4. Enum class `Direction`:
   - Represents the possible directions for moving the squares on the board.
   - Contains constants for the four directions: UP, DOWN, LEFT, and RIGHT.

### Top-Level Entities and Interactions
The interactions between the top-level entities in the 2048 game are as follows:

1. `Main` interacts with `Board`:
   - `Main` creates an instance of `Board` to represent the game board.
   - `Main` starts the game loop and handles user input.
   - `Main` calls methods on `Board` to manipulate the board state (e.g., moving squares).
   - `Main` retrieves the current board state from `Board` to display it to the player.

2. `Board` interacts with `Square`:
   - `Board` contains a grid of `Square` objects to represent the game board.
   - `Board` updates the state of squares on the board by calling methods on `Square` (e.g., updating square values, swapping squares).

These classes work together to create and manage the 2048 game logic.