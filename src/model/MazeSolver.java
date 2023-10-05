package model;

import view.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class which solves the given maze from its starting position.
 */
public class MazeSolver {
    /** List of views to update. */
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Adds instance of the view that displays the maze in the user chosen mode (text or GUI) to the observers list
     * used by the MazeSolver to update the display as it solves the maze.
     *
     * @param observer instance of the view that displays the maze in the user chosen mode (text or GUI).
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Notifies the view instance on the list to redraw the updated maze.
     *
     * @param maze new updated maze to redraw on the screen.
     */
    private void notifyObservers(char[][] maze) {
        for (Observer view : observers) {
            view.redraw(maze);
        }
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
            return true;
        }

        // when the current position is not visited mark it as visited ('T' is the traversed path)
        if (maze[row][col] == ' ' || maze[row][col] == '.' || maze[row][col] == 'S') {
            maze[row][col] = 'T';
            // redraw the updated maze
            notifyObservers(maze);

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
     * Used to find the starting coordinate of the selected maze.
     *
     * @param maze 2D maze to solve.
     * @return starting coordinate in the form [row, column].
     * @require only one starting point ('S') exists.
     * @ensure the starting point's coordinate is returned.
     */
    public static int[] findStartingCoordinate(char[][] maze) {
        int[] startingCoordinate = {0, 0};
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                if (maze[row][col] == 'S') {
                    startingCoordinate[0] = row;
                    startingCoordinate[1] = col;
                }
            }
        }
        return startingCoordinate;
    }

    /**
     * Runs the maze solving process from its starting coordinate and returns the outcome using boolean value.
     * <p>
     * First obtains the maze's starting coordinate then
     * If solvable, return true.
     * Else, return false.
     * </p>
     *
     * @param maze 2D maze to solve.
     * @return whether the maze the solved or not.
     */
    public boolean showMazeSolution(char[][] maze) {
        // Starting position's coordinate
        int[] startingCoordinate = MazeSolver.findStartingCoordinate(maze);
        int startRow = startingCoordinate[0];
        int startCol = startingCoordinate[1];

        boolean result = this.solveMaze(maze, startRow, startCol);
        // needed to redraw the maze with the solution path
        notifyObservers(maze);
        return result;
    }

}