package control;

import model.MazeSolver;
import view.MazeView;

/**
 * Control class that handles the input of display mode and maze into Model and View classes,
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

        // MazeView set as local variable as it is only used once here
        MazeView view;
        if (displayMode.equals("GUI")) {
            view = new MazeView(displayMode, maze);
        } else {
            view = new MazeView();
        }

       this.solver = new MazeSolver(view);
    }

    /**
     * Solves the maze from its starting coordinate.
     */
    public void solveMaze() {
        int[] startingCoordinate = findStartingCoordinate(maze);
        int startRow = startingCoordinate[0];
        int startCol = startingCoordinate[1];
        solver.showMazeSolution(maze, startRow, startCol);
    }

    /**
     * Used to find the starting coordinate of the selected maze.
     *
     * @param maze 2D maze to solve.
     * @return starting coordinate in the form [row, column].
     * @require only one start point ('S') exists.
     * @ensure the start points coordinate is returned.
     */
    public static int[] findStartingCoordinate(char[][] maze) {
        int[] startingCoordinate = {0, 0};
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'S') {
                    startingCoordinate[0] = i;
                    startingCoordinate[1] = j;
                }
            }
        }
        return startingCoordinate;
    }
}
