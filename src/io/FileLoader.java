package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
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
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            // read the dimension
            // check it is the dimension (only 2 odd integers)
            int rows;
            int columns;
            line = bufferedReader.readLine();
            String[] dimensions = line.split(" ");
            rows = Integer.parseInt(dimensions[0]);
            columns = Integer.parseInt(dimensions[1]);

            if (rows % 2 == 0 && columns % 2 == 0) {
                throw new IllegalArgumentException();
            }

            while ((line = bufferedReader.readLine()) != null) {

            }


            return new char[0][];
        } catch (IOException e) {
            System.out.println("IO error occured");;
        }


        return new char[0][];
    }
}
