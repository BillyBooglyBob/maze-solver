package control;

import model.MazeSolver;
import view.MazeView;

/**
 * Control class that inputs display mode and maze into Model and View classes to solve and display.
 */
public class Control {
    private final MazeSolver solver;
    private final char[][] maze;

    /**
     * Initialise the Control class with the user inputted display mode and selected maze.
     * @param displayMode mode to display the maze in (either text or GUI).
     * @param maze 2D maze to solve.
     */
    public Control(String displayMode, char[][] maze) {
        this.maze = maze;

        MazeView view;
        if (displayMode.equals("GUI")) {
            view = new MazeView(displayMode, maze);
        } else {
            view = new MazeView();
        }

       this.solver = new MazeSolver(view);
    }

    /**
     * Runs the solve maze method of the Model class.
     */
    public void solveMaze() {
        int[] startingCoordinate = findStartingCoordinate(maze);
        int startRow = startingCoordinate[0];
        int startCol = startingCoordinate[1];
        solver.showMazeSolution(maze, startRow, startCol);
    }

    /**
     * Used to find the starting coordinate from the maze.
     * <p>
     * If starting coordinate not found, use random.
     * </p>
     *
     * @param maze 2D maze to solve.
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
