package control;

import model.MazeSolver;
import view.MazeView;

/**
 * Control class that handles the input of display mode and maze into the Model and View classes,
 * allowing for maze solving and solution display.
 */
public class Control {
    /** needed to run the solve maze method. */
    private final MazeSolver solver;
    /** stored to be passed as input to the solve maze method and find starting coordinate method. */
    private final char[][] maze;

    /**
     * Initialise the Control class with the user inputted display mode and selected maze.
     *
     * @param displayMode mode to display the maze in (either text or GUI).
     * @param maze 2D maze to solve.
     */
    public Control(String displayMode, char[][] maze) {
        this.maze = maze;
        this.solver = new MazeSolver();
        new MazeView(solver, displayMode, maze);
    }

    /**
     * Solves the maze and print out the outcome depending on whether it can be solved or not.
     */
    public void solveMaze() {
        boolean mazeSolvability = solver.showMazeSolution(maze);
        if (!mazeSolvability) {
            System.out.print("No path\n");
        } else {
            System.out.print("Path found\n");
        }
    }
}
