import control.Control;
import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import io.FileLoader;

import java.io.FileNotFoundException;

public class Launcher {
    /**
     * Accept user input to display the chosen maze in the selected mode (either GUI or text).
     *
     * @param args list of Strings containing inputs from the user in the form of command line arguments.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     *                                For example, the first line is not the dimension.
     *                                Or the line doesn't start and end with a wall.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     *                                    For example, the width of the line exceeds the dimension.
     * @throws IllegalArgumentException For other validation errors.
     * @throws FileNotFoundException If the maze file is not found.
     */
    public static void main(String[] args)
            throws FileNotFoundException, MazeSizeMissmatchException, IllegalArgumentException, MazeMalformedException {
        // determine the display mode and maze to solve based on user input
        FileLoader fileLoader = new FileLoader();
        char[][] maze;
        // make default display mode Text
        String displayMode = "text";

        // determine the display mode based on the arguments passed in the command line
        if (args.length == 1 && args[0].endsWith(".txt")) {
            maze = fileLoader.load("mazes/" + args[0]);
        } else if (args.length == 2 && args[0].equals("GUI") && args[1].endsWith(".txt")) {
            maze = fileLoader.load("mazes/" + args[1]);
            displayMode = args[0];
        } else {
            throw new FileNotFoundException();
        }

        Control control = new Control(displayMode, maze);
        control.solveMaze();
    }
}
