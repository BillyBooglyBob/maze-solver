package control;

import model.MazeSolver;
import view.MazeView;

import java.util.ArrayList;
import java.util.List;

public class Control {
    private MazeSolver solver;
    private String displayMode;
    private char[][] maze;
    private List<Integer> startingCoordinate = new ArrayList<>();

    public Control(String displayMode, char[][] maze) {
        this.displayMode = displayMode;
        this.maze = maze;

        MazeView view;
        if (displayMode.equals("GUI")) {
            view = new MazeView(displayMode, maze);
        } else {
            view = new MazeView();
        }

        MazeSolver solver = new MazeSolver(view);
    }

    public void solveMaze() {
        int startRow = startingCoordinate.get(0);
        int startCol = startingCoordinate.get(1);
        solver.solve(maze, startRow, startCol);
    }

    /**
     * Used to find the starting coordinate from the maze
     *
     * <p>
     *     If starting coordinate not found, use random
     * </p>
     *
     * @param maze
     */
    public void findStartingCooridnate(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'S') {
                    startingCoordinate.set(0, i);
                    startingCoordinate.set(1, j);
                }
            }
        }
    }
}
