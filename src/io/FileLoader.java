package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;

import java.io.*;

/**
 * Class for loading the map of a maze file into a 2D array
 */
public class FileLoader implements FileInterface {

    /**
     * Reads a file containing the map for a maze and outputs it into a 2D array.
     *
     * @param filename The path to the maze file to be loaded.
     * @return 2D array of the maze
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     *                                For example, the first line is not the dimension.
     *                                Or the line doesn't start and end with a wall.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     *                                    For example, the width of the line exceeds the dimension.
     * @throws IllegalArgumentException For other validation errors.
     * @throws FileNotFoundException If the maze file is not found.
     */
    @Override
    public char[][] load(String filename) throws MazeMalformedException, MazeSizeMissmatchException,
            IllegalArgumentException, FileNotFoundException {

        /*
        read the first line to get the dimensions & initialised rows + columns

        read all the lines 1 by one
        convert each line into a charArray[]
        convert each corresponding row into charArray[]


         */

        // check if file exists
        File file = new File(filename);
        if (!file.exists()) {
            // The file does not exist, so throw FileNotFoundException
            throw new FileNotFoundException("File not found.");
        }

        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            // read the first line which should contain the dimensions
            int rows;
            int columns;
            // used to keep track of the first and last line of the map
            int rowCounter = 0;

            line = bufferedReader.readLine();
            String[] dimensions = line.split(" ");

            // minus 1 as counting starts from 0
            rows = Integer.parseInt(dimensions[0]);
            columns = Integer.parseInt(dimensions[1]);

            // verify the dimension is valid (both are odd)
            if (rows % 2 == 0 && columns % 2 == 0
                || rows < 0 || columns < 0) {
                throw new IllegalArgumentException();
            }

            char[][] map;
            map = new char[rows][columns];

            // subtract value to ensure rows and rowsCounter both start at 0
            rows--;

            // read the map
            while ((line = bufferedReader.readLine()) != null) {
                int lengthOfLine = line.length();
                // check width of map
                if (lengthOfLine != columns) {
                    throw new MazeSizeMissmatchException();
                }

                // check border of the map
                // first and last line must all be walls
                // start and end of any other lines must both be walls
                // rows - 1 as rowCounter starts at 0 and rows at 1
                if (rowCounter == 0 || rowCounter == rows) {
                    for (int i = 0; i < lengthOfLine; i++) {
                        char a = line.charAt(i);
                        if (a != '#') {
                            throw new MazeMalformedException();
                        }
                    }
                } else {
                    char firstChar = line.charAt(0);
                    char lastChar = line.charAt(lengthOfLine - 1);
                    if (firstChar != '#' || lastChar != '#') {
                        throw new MazeMalformedException();
                    }
                    else {
                        for (int i = 0; i < lengthOfLine; i++) {
                            char a = line.charAt(i);
                            if (a != '#' && a != ' ' && a != '.' && a != 'S' && a != 'E') {
                                throw new MazeMalformedException();
                            }
                        }
                    }
                }

                // check rows of the map matches with the rows of the dimension
                // accounts for when there are fewer rows or more rows
                if (rowCounter > rows) {
                    throw new MazeSizeMissmatchException();
                }

                // check each character is correct


                // add line to the array
                map[rowCounter] = line.toCharArray();

                rowCounter++;
            }

            // map has fewer rows than the specified dimension
            if (rowCounter < rows) {
                throw new MazeSizeMissmatchException();
            }


            return map;
        } catch (IOException e) {
            System.out.println("IO error occurred.");
        }


        return new char[0][];
    }

    public static void main(String[] args) throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        FileLoader a = new FileLoader();
        char[][] b = a.load("maze-solver\\test\\mazes\\TooFewRows.txt");
        for (char[] chars : b) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println(); // Move to the next line after each row
        }
    }
}
