package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FileLoaderTest {

    private FileLoader fileLoader;

    @Before
    public void setUp() {
        fileLoader = new FileLoader();
    }

    @After
    public void tearDown() {
        fileLoader = null;
    }

    @Test
    public void loadTestSmallMap() throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        char[][] actualMap = fileLoader.load("maze-solver\\src\\mazes\\Small.txt");
        char[][] correctMap = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };
        assertArrayEquals(correctMap, actualMap);
    }

//    @Test
//    public void loadTestMediumMap() throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
//        char[][] actualMap = fileLoader.load("maze-solver\\src\\mazes\\Medium.txt");
//        char[][] correctMap = {
//                {'#', '#', '#', '#', '#', '#', '#'},
//                {'#', 'S', '#', ' ', ' ', ' ', '#'},
//                {'#', ' ', '#', '#', '#', ' ', '#'},
//                {'#', ' ', '#', ' ', ' ', ' ', '#'},
//                {'#', ' ', '#', ' ', '#', ' ', '#'},
//                {'#', ' ', ' ', ' ', '#', 'E', '#'},
//                {'#', '#', '#', '#', '#', '#', '#'}
//        };
//        assertArrayEquals(correctMap, actualMap);
//    }
//
//    @Test
//    public void loadTestLargeMap() throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
//        char[][] actualMap = fileLoader.load("maze-solver\\src\\mazes\\Large.txt");
//        char[][] correctMap = {
//                {'#', '#', '#', '#', '#', '#', '#'},
//                {'#', 'S', '#', ' ', ' ', ' ', '#'},
//                {'#', ' ', '#', '#', '#', ' ', '#'},
//                {'#', ' ', '#', ' ', ' ', ' ', '#'},
//                {'#', ' ', '#', ' ', '#', ' ', '#'},
//                {'#', ' ', ' ', ' ', '#', 'E', '#'},
//                {'#', '#', '#', '#', '#', '#', '#'}
//        };
//        assertArrayEquals(correctMap, actualMap);
//    }

    @Test (expected = IllegalArgumentException.class)
    public void loadTestWrongDimension()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\WrongDimension.txt");
    }

    @Test (expected = IllegalArgumentException.class)
    public void loadTestNoDimension()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\NoDimension.txt");
    }

    @Test (expected = MazeMalformedException.class)
    public void loadTestMapWrongCharacter()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\WrongCharacter.txt");
    }

    @Test (expected = MazeSizeMissmatchException.class)
    public void loadTestTooManyRows()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\TooManyRows.txt");
    }

    @Test (expected = MazeSizeMissmatchException.class)
    public void loadTestTooFewRows()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\TooFewRows.txt");
    }

    @Test (expected = MazeSizeMissmatchException.class)
    public void loadTestWrongWidth()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\WrongWidth.txt");
    }
}