package model;

import view.MazeView;

import java.util.ArrayList;
import java.util.List;

public class MazeSolver {

    private final MazeView view;
    // contains the actual path that solves the maze
    private List<Integer> path = new ArrayList<>();
    public MazeSolver(MazeView view) {
        this.view = view;
    }
    /**
     *
     * @param maze
     * @return
     */
    public boolean solveNextStep(char[][] maze, int row, int col, List<Integer> path) {
        // check if target node is reached
        if (maze[row][col] == 'E') {
            this.addPath(path, row, col);
            return true;
        }

        // when current position not visited, mark it as visited
        // T signifies traversed path
        if (maze[row][col] == ' ' || maze[row][col] == '.' || maze[row][col] == 'S') {
            maze[row][col] = 'T';
            this.view.redraw(maze);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // visit all the neighbour nodes recursively
            int dx = -1;
            int dy = 0;
            if (solveNextStep(maze, row + dx, col + dy, path)) {
                maze[row][col] = 'T';
                this.addPath(path, row, col);
                return true;
            }

            dx = 1;
            dy = 0;
            if (solveNextStep(maze, row + dx, col + dy, path)) {
                maze[row][col] = 'T';
                this.addPath(path, row, col);
                return true;
            }

            dx = 0;
            dy = -1;
            if (solveNextStep(maze, row + dx, col + dy, path)) {
                maze[row][col] = 'T';
                this.addPath(path, row, col);
                return true;
            }

            dx = 0;
            dy = 1;
            if (solveNextStep(maze, row + dx, col + dy, path)) {
                maze[row][col] = 'T';
                this.addPath(path, row, col);
                return true;
            }
        }

        return false;
    }

    /**
     * Adds current coordinate to the correct path
     * @param path
     * @param row
     * @param col
     */
    public void addPath(List<Integer> path, int row, int col) {
        path.add(row);
        path.add(col);
    }

    /**
     * Solves the maze and displays the correct path
     * @param maze
     * @param row
     * @param col
     */
    public void solve(char[][] maze, int row, int col) {
        if (!this.solveNextStep(maze, row, col, path)) {
            System.out.println("No path");
        } else {
            // displays the correct path if one exists
            for (int i = 0; i < path.size(); i+=2) {
                int x = path.get(i);
                int y = path.get(i + 1);
                maze[x][y] = 'P';
                }
            }
            this.view.redraw(maze);
            System.out.println("Path found");
        }
    }
