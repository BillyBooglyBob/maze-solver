package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;

import java.io.*;

/**
 * Class for loading the map of a selected maze file into a 2D array.
 */
public class FileLoader implements FileInterface {

    /**
     * Reads a file containing the map for a maze and outputs it into a 2D array.
     *
     * @param fileName The path to the maze file to be loaded.
     * @return 2D array of the maze.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     *                                For example, the first row is not the dimension.
     *                                Or the row doesn't start and end with a wall.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     *                                    For example, the width of the row exceeds the dimension.
     * @throws IllegalArgumentException For other validation errors not covered by MazeMalformedException
     *                                  and MazeSizeMissmatchException.
     * @throws FileNotFoundException If the maze file is not found.
     * @require
     * @ensure
     */
    @Override
    public char[][] load(String fileName) throws MazeMalformedException, MazeSizeMissmatchException,
            IllegalArgumentException, FileNotFoundException {
        // check if the file exists first
        FileLoader.fileExist(fileName);

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String row;

            // read the first row which should contain the dimensions of the maze
            int rows;
            int columns;
            // used to keep track of the first and last row of the map
            int rowCounter = 0;

            row = bufferedReader.readLine();
            String[] dimensions = row.split(" ");
            rows = Integer.parseInt(dimensions[0]);
            columns = Integer.parseInt(dimensions[1]);

            // verify the dimension is valid (both are odd)
            validDimension(rows, columns);

            // initialise 2D array for the map based on the given dimensions
            char[][] map;
            map = new char[rows][columns];

            // subtract by one to ensure rows and rowsCounter both start at 0
            rows--;

            // read the map
            while ((row = bufferedReader.readLine()) != null) {
                int rowWidth = row.length();
                // check width of the maze matches the dimension provided
                FileLoader.checkLineLength(rowWidth, columns);

                // ensure the content of the maze are all valid.
                checkRowBorders(row, rowWidth, rows, rowCounter);

                // check if the map has more rows than the specified dimension
                checkRowCounterExceedsRows(rows, rowCounter);

                // add row to the array
                map[rowCounter] = row.toCharArray();
                rowCounter++;
            }

            // check if the map has fewer rows than the specified dimension
            checkRowCounterLessThanRows(rows, rowCounter);

            return map;
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
     * Check the dimensions provided are both valid.
     *
     * @param rows Width of the maze given at the start of the map.
     * @param columns Height of the maze given at the start of the map.
     * @throws IllegalArgumentException For other validation errors not covered by MazeMalformedException
     *                                  and MazeSizeMissmatchException.
     * @require rows >= 3 && columns >= 3 && rows != null && columns != null &&
     *          rows % 2 == 1 && columns % 2 == 1
     * @ensure The maze has an external wall and internal paths.
     */
    private void validDimension(int rows, int columns) throws IllegalArgumentException {
        if (rows % 2 == 0 && columns % 2 == 0
                || rows < 3 || columns < 3) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks if the length of the row matches the specified number of columns.
     *
     * @param rowWidth The width of the row.
     * @param columns The expected number of columns.
     * @throws MazeSizeMissmatchException If the length of the row does not match the expected number of columns.
     * @require rowWidth >= 0 && columns >= 3 && rowWidth != null && columns != null
     * @ensure rowWidth == columns
     */
    private static void checkLineLength(int rowWidth, int columns) throws MazeSizeMissmatchException {
        if (rowWidth != columns) {
            throw new MazeSizeMissmatchException();
        }
    }

    /**
     * Checks if the given row's borders meet the maze requirements.
     *
     * @param row The row to be checked.
     * @param rowWidth Width of the row.
     * @param rows Width of the maze given at the start of the map.
     * @param rowCounter Counter counting how many rows there are in the maze.
     * @throws MazeMalformedException If the row's borders are not valid.
     * @require rows >= 3 && rows != null
     * @ensure If the row is invalid, a MazeMalformedException is thrown.
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
            } else {
                checkRowContent(row);
            }
        }
    }

    /**
     * Checks if the given row's content is valid within the maze.
     *
     * @param row The row to be checked.
     * @throws MazeMalformedException If the row's content is not valid.
     * @require The row is not null.
     * @ensure If the row's content is invalid, a MazeMalformedException is thrown.
     */
    private void checkRowContent(String row) throws MazeMalformedException {
        int rowWidth = row.length();

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
     * @param rows Width of the maze given at the start of the map.
     * @param rowCounter The current row counter.
     * @throws MazeSizeMissmatchException If the rowCounter exceeds the specified number of rows.
     * @require The rowCounter is not null.
     * @ensure If the rowCounter exceeds the specified number of rows, a MazeSizeMissmatchException is thrown.
     */
    private void checkRowCounterExceedsRows(int rows, int rowCounter) throws MazeSizeMissmatchException {
        if (rowCounter > rows) {
            throw new MazeSizeMissmatchException();
        }
    }

    /**
     * Checks if the rowCounter is less than the specified number of rows.
     *
     * @param rows Width of the maze given at the start of the map.
     * @param rowCounter The current row counter.
     * @throws MazeSizeMissmatchException If the rowCounter is less than the specified number of rows.
     * @require The rowCounter is not null.
     * @ensure If the rowCounter is less than the specified number of rows, a MazeSizeMissmatchException is thrown.
     */
    private void checkRowCounterLessThanRows(int rows, int rowCounter) throws MazeSizeMissmatchException {
        if (rowCounter < rows) {
            throw new MazeSizeMissmatchException();
        }
    }
}
