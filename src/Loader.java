import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import io.FileLoader;
import model.MazeSolver;
import view.MazeView;

import java.io.FileNotFoundException;

public class Loader {
    /**
     * Accept user input to display the chosen maze in the selected mode (either GUI or text)
     *
     * @param args
     * @throws FileNotFoundException
     * @throws MazeSizeMissmatchException
     * @throws MazeMalformedException
     */
    public static void main(String[] args) throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        // check if running by GUI or by text
        // check args.length
        FileLoader fileLoader = new FileLoader();
        char[][] maze;
        // make default display mode Text
        String displayMode = "Text";

        if (args.length == 1 && args[0].endsWith(".txt")) {
            maze = fileLoader.load("mazes/" + args[0]);
            System.out.println("Text");
        } else if (args.length == 2 && args[0].equals("GUI") && args[1].endsWith(".txt")) {
            maze = fileLoader.load("mazes/" + args[1]);
            System.out.println("GUI");
            displayMode = args[0];
        } else {
            throw new FileNotFoundException();
        }

        // find the starting point from the map
        // initialise just in case S doesn't exist
        int row = 1;
        int col = 1;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'S') {
                    row = i;
                    col = j;
                }
            }
        }

        // select the view depending on the mode
        MazeView view;
        if (displayMode.equals("GUI")) {
            view = new MazeView(displayMode, maze);
        } else {
            view = new MazeView();
        }

        // solve the maze
        MazeSolver solver = new MazeSolver(view);
        solver.solve(maze, row, col);
    }
}
