import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import io.FileLoader;
import model.MazeSolver;
import view.MazeView;

import java.io.FileNotFoundException;

public class Loader {
    public static void main(String[] args) throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        FileLoader fileLoader = new FileLoader();

        // check if running by GUI or by text
        // check args.length
        // if length == 1 && args[0] ends in 'txt' run text mode with the maze
        // if length == 2 && args[0] == "GUI" && args[1] ends in 'txt' run GUI mode with maze

        // run by terminal
        char[][] maze = fileLoader.load("mazes/" + args[0]);

        // run by intellij
//        char[][] maze = fileLoader.load("maze-solver\\src\\Small.txt");


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

        MazeView view = new MazeView();
        MazeSolver solver = new MazeSolver(view);
        solver.solve(maze, row, col);

    }
}
