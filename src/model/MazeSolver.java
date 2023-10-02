package model;

import view.MazeView;

/**
 * Model class which solves the given maze from a given starting position.
 */
public class MazeSolver {

    private final MazeView view;
    // contains the actual path that solves the maze

    public MazeSolver(MazeView view) {
        this.view = view;
    }

    /** Implements the depth first algorithm to solve the given maze from the given starting position.
     * <p>
     * - Returning true and outline the valid path if solvable.
     * - Returning false vice versa.
     * </p>
     *
     * @param maze 2D maze to be solved.
     * @return boolean value depending on if the maze is solved.
     */
    public boolean solveMaze(char[][] maze, int row, int col) {
        // check if target node is reached
        if (maze[row][col] == 'E') {
            return true;
        }

        // when current position not visited, mark it as visited
        // T signifies traversed path
        if (maze[row][col] == ' ' || maze[row][col] == '.' || maze[row][col] == 'S') {
            maze[row][col] = 'T';
            // redraw the updated maze
            this.view.redraw(maze);

            // sleep to give adequate time to view the changing maze
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // visit all the neighbour nodes recursively
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
     * Attempt to solve the maze, displays the correct path if solvable and success message if solvable.
     * <p>
     * If not solvable, outputs fail message.
     * </p>
     *
     * @param maze 2D maze to solve.
     * @param row starting position's row.
     * @param col starting position's column.
     */
    public void showMazeSolution(char[][] maze, int row, int col) {
        if (!this.solveMaze(maze, row, col)) {
            System.out.print("No path");
        } else {
            // redraw the maze with the path found
            this.view.redraw(maze);

            // uses print instead of println as it is the end of the output and to enable testing of output
            System.out.print("Path found");
        }
    }
}