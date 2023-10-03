package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;

import java.io.*;

/**
 * Class for loading the map of a selected maze file into a 2D array.
 */
public class FileLoader implements FileInterface {

    /**
     * Reads a file containing the map for a maze and outputs it as a 2D array.
     *
     * @param fileName The path to the maze file to be loaded.
     * @return 2D array of the maze.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     *                                For example, the first row is not the dimension.
     *                                Or the row doesn't start and end with a wall.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     *                                    For example, the width of the row exceeds the provided dimension.
     * @throws IllegalArgumentException For other validation errors not covered by MazeMalformedException
     *                                  and MazeSizeMissmatchException.
     * @throws FileNotFoundException If the maze file is not found.
     * @require fileName != null &&
     *          file exists &&
     *          dimension provided are both odd &&
     *          dimension of the actual maze matches the dimension provided at the start of the file &&
     *          content of the maze are all valid characters part of the list ('#', ' ', '.', 'S', 'E') &&
     *          border of the maze are all walls &&
     *          there must be one start point and one endpoint &&
     *          only one start point and end point can exist.
     * @ensure a valid maze is returned as a 2D array.
     */
    @Override
    public char[][] load(String fileName) throws MazeMalformedException, MazeSizeMissmatchException,
            IllegalArgumentException, FileNotFoundException {
        // check if the file exists
        FileLoader.fileExist(fileName);

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String row;

            // read the first row which should contain the dimensions of the maze
            int rows;
            int columns;
            // used to keep track of how many rows are in the actual file
            int rowCounter = 0;

            // read the first row which should contain the dimensions of the maze
            String[] dimensions = readMazeDimensions(bufferedReader);

            rows = Integer.parseInt(dimensions[0]);
            columns = Integer.parseInt(dimensions[1]);

            // verify the dimension is valid (both are odd)
            validDimension(rows, columns);

            // initialise 2D array for the maze based on the given dimensions
            char[][] maze;
            maze = new char[rows][columns];

            // subtract by one to ensure rows and rowsCounter both start at 0
            rows--;

            // read the maze
            while ((row = bufferedReader.readLine()) != null) {
                int rowWidth = row.length();
                // check width of the maze matches the dimension provided
                FileLoader.checkLineLength(rowWidth, columns);

                // ensure the content of the maze are all valid
                checkRowBorders(row, rowWidth, rows, rowCounter);

                // check all the characters of the row are valid
                checkRowContent(row, rowWidth);

                // check if the maze has more rows than the specified dimension
                checkRowCounterExceedsRows(rows, rowCounter);

                // add row to the array
                maze[rowCounter] = row.toCharArray();
                rowCounter++;
            }

            // check if the maze has fewer rows than the specified dimension
            checkRowCounterLessThanRows(rows, rowCounter);

            // check if the maze has a start and end point, and ensure there is only one of each

            checkStartEndPoints(maze);
            
            return maze;
        } catch (IOException e) {
            System.out.println("IO error occurred.");
        }

        // return empty maze if file is empty
        return new char[0][];
    }

    /**
     * Check if the file exists.
     *
     * @param fileName file name entered by the user.
     * @throws FileNotFoundException If the maze file is not found.
     */
    private static void fileExist(String fileName) throws FileNotFoundException{
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found.");
        }
    }

    /**
     * Reads the dimensions of a maze from the first row of a BufferedReader.
     *
     * @param bufferedReader The BufferedReader to read the dimensions from.
     * @return An array containing the maze dimensions [rows, columns].
     * @throws IOException If an IO error occurs while reading the dimensions.
     */
    private String[] readMazeDimensions(BufferedReader bufferedReader) throws IOException {
        String row = bufferedReader.readLine();
        return row.split(" ");
    }

    /**
     * Check the dimensions provided are both valid.
     *
     * @param rows Width of the maze given at the start of the maze.
     * @param columns Height of the maze given at the start of the maze.
     * @throws IllegalArgumentException For other validation errors not covered by MazeMalformedException
     *                                  and MazeSizeMissmatchException.
     * @require rows >= 3 && columns >= 3 &&
     *          rows != null && columns != null &&
     *          both rows and columns are odd.
     * @ensure The maze has an external wall and internal paths.
     */
    private void validDimension(int rows, int columns) throws IllegalArgumentException {
        if (rows % 2 == 0 && columns % 2 == 0
                || rows < 3 || columns < 3) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks if the length of the row matches the given dimension.
     *
     * @param rowWidth The width of the row.
     * @param columns The expected number of columns.
     * @throws MazeSizeMissmatchException If the length of the row does not match the expected number of columns.
     */
    private static void checkLineLength(int rowWidth, int columns) throws MazeSizeMissmatchException {
        if (rowWidth != columns) {
            throw new MazeSizeMissmatchException();
        }
    }

    /**
     * Checks if the given row's borders are all walls ('#').
     *
     * @param row The row to be checked.
     * @param rowWidth Width of the row.
     * @param rows Width of the maze given at the start of the maze.
     * @param rowCounter Counter counting how many rows there are in the maze.
     * @throws MazeMalformedException If the row's borders are not valid.
     */
    private void checkRowBorders(String row, int rowWidth, int rows, int rowCounter) throws MazeMalformedException {
        char firstChar = row.charAt(0);
        char lastChar = row.charAt(rowWidth - 1);

        if (rowCounter == 0 || rowCounter == rows) {
            for (int i = 0; i < rowWidth; i++) {
                char a = row.charAt(i);
                if (a != '#') {
                    throw new MazeMalformedException();
                }
            }
        } else {
            if (firstChar != '#' || lastChar != '#') {
                throw new MazeMalformedException();
            }
        }
    }

    /**
     * Checks if the given row's content are all valid characters.
     * <p>
     * Characters are all part of the list ('#', ' ', '.', 'S', 'E').
     * </p>
     *
     * @param row The row to be checked.
     * @throws MazeMalformedException If the row's content is not valid.
     */
    private void checkRowContent(String row, int rowWidth) throws MazeMalformedException {
         for (int i = 0; i < rowWidth; i++) {
            char a = row.charAt(i);
            if (a != '#' && a != ' ' && a != '.' && a != 'S' && a != 'E') {
                throw new MazeMalformedException();
            }
        }
    }

    /**
     * Checks if the rowCounter exceeds the specified number of rows.
     *
     * @param rows Width of the maze given at the start of the maze.
     * @param rowCounter The current row counter.
     * @throws MazeSizeMissmatchException If the rowCounter exceeds the specified number of rows.
     */
    private void checkRowCounterExceedsRows(int rows, int rowCounter) throws MazeSizeMissmatchException {
        if (rowCounter > rows) {
            throw new MazeSizeMissmatchException();
        }
    }

    /**
     * Checks if the rowCounter is less than the specified number of rows.
     *
     * @param rows Width of the maze given at the start of the maze.
     * @param rowCounter The current row counter.
     * @throws MazeSizeMissmatchException If the rowCounter is less than the specified number of rows.
     */
    private void checkRowCounterLessThanRows(int rows, int rowCounter) throws MazeSizeMissmatchException {
        if (rowCounter < rows) {
            throw new MazeSizeMissmatchException();
        }
    }

    /**
     * Check if there are correct number of start and end points in the maze.
     *
     * @param maze 2D array of the maze to be checked.
     * @throws MazeMalformedException when there isn't only one start point and end point.
     */
    private void checkStartEndPoints(char[][] maze) throws MazeMalformedException {
        int startPointCounter = 0;
        int endPointCounter = 0;
        
        for (char[] row : maze) {
            for (char cell : row) {
                if (cell == 'S') {
                    startPointCounter++;
                }
                if (cell == 'E') {
                    endPointCounter++;
                }
            }
        }
        
        if (startPointCounter != 1 || endPointCounter != 1) {
            throw new MazeMalformedException();
        }
    }
}
