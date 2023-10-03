package model;

import view.MazeView;

/**
 * Model class which solves the given maze from a given starting position.
 */
public class MazeSolver {

    /** Used to update the view with the newest maze after each step of solving. */
    private final MazeView view;

    /**
     * Initialises the MazeSolver with the provided view.
     *
     * @param view used to update the maze.
     */
    public MazeSolver(MazeView view) {
        this.view = view;
    }

    /** Implements the depth first search algorithm to solve the provided maze from its starting position.
     * <p>
     * - Returning true and outline the valid path if solvable.
     * - Returning false vice versa.
     * The maze is solved incrementally with each step shown.
     * </p>
     *
     * @param maze 2D maze to be solved.
     * @param row starting position's row.
     * @param col starting position's column.
     * @return boolean value depending on if the maze is solved.
     * @require valid starting point provided.
     * @ensure the maze may be partially solved, and the view will be updated with each step.
     */
    public boolean solveMaze(char[][] maze, int row, int col) {
        // check if the end is reached ('E' is the end)
        if (maze[row][col] == 'E') {
            // redraws the maze with the path found highlighted
            this.view.redraw(maze);
            return true;
        }

        // when the current position is not visited mark it as visited ('T' is the traversed path)
        if (maze[row][col] == ' ' || maze[row][col] == '.' || maze[row][col] == 'S') {
            maze[row][col] = 'T';
            // redraw the updated maze
            this.view.redraw(maze);

            // sleep to give adequate time to view the changing maze
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // visit all the neighbour cells in the four directions (NSEW) recursively
            int dx = -1;
            int dy = 0;
            if (solveMaze(maze, row + dx, col + dy)) {
                maze[row][col] = 'P';
                return true;
            }

            dx = 1;
            dy = 0;
            if (solveMaze(maze, row + dx, col + dy)) {
                maze[row][col] = 'P';
                return true;
            }

            dx = 0;
            dy = -1;
            if (solveMaze(maze, row + dx, col + dy)) {
                maze[row][col] = 'P';
                return true;
            }

            dx = 0;
            dy = 1;
            if (solveMaze(maze, row + dx, col + dy)) {
                maze[row][col] = 'P';
                return true;
            }
        }
        return false;
    }

    /**
     * Depending on the outcome of the maze-solving attempt, inform the user of the outcome.
     * <p>
     * If solvable, displays the correct path and success message.
     * If not solvable, outputs fail message.
     * </p>
     *
     * @param maze 2D maze to solve.
     * @param row starting position's row.
     * @param col starting position's column.
     */
    public void showMazeSolution(char[][] maze, int row, int col) {
        if (!this.solveMaze(maze, row, col)) {
            System.out.println("No path");
        } else {
            // uses print instead of println as it is the end of the output and to enable testing of output
            System.out.println("Path found");
        }
    }
}