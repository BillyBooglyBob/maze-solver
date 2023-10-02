import control.Control;
import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import io.FileLoader;

import java.io.FileNotFoundException;

public class Launcher {
    /**
     * Accept user input to display the chosen maze in the selected mode (either GUI or text)
     *
     * @param args list of Strings containing inputs from the user
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
        // check if running by GUI or by text
        FileLoader fileLoader = new FileLoader();
        char[][] maze;
        // make default display mode Text
        String displayMode = "text";

        if (args.length == 1 && args[0].endsWith(".txt")) {
            maze = fileLoader.load("mazes/" + args[0]);
        } else if (args.length == 2 && args[0].equals("GUI") && args[1].endsWith(".txt")) {
            maze = fileLoader.load("mazes/" + args[1]);
            displayMode = args[0];
        }

//        else {
//            throw new FileNotFoundException();
//        }

        maze = fileLoader.load("C:\\Users\\willi\\OneDrive\\Desktop\\UQ Things\\Year 1\\Sem 2\\CSSE2002\\a1\\maze-solver\\maze-solver\\src\\mazes\\Large.txt");
        displayMode = "GUI";

        Control control = new Control(displayMode, maze);
        control.solveMaze();
    }
}
