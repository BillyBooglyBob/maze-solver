package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import static org.junit.Assert.assertArrayEquals;

/**
 * Tests the FileLoader with various mazes.
 */
public class FileLoaderTest {
    /** Variable shared by all the tests to avoid repetition. */
    private FileLoader fileLoader;

    /**
     * Create the fileLoader instance to avoid repetitive setup in the tests.
     */
    @Before
    public void setUp() {
        fileLoader = new FileLoader();
    }

    /**
     * Clean up after each test to make sure every test starts at the same consistent states.
     */
    @After
    public void tearDown() {
        fileLoader = null;
    }

    /**
     * Test if Small.txt is loaded correctly into a 2D array.
     *
     * @throws FileNotFoundException If the maze file is not found.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     */
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

    /**
     * Test if Medium.txt is loaded correctly into a 2D array.
     *
     * @throws FileNotFoundException If the maze file is not found.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     */
    @Test
    public void loadTestMediumMap() throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        char[][] actualMap = fileLoader.load("maze-solver\\src\\mazes\\Medium.txt");
        char[][] correctMap = {
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', '#', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', '#', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };
        assertArrayEquals(correctMap, actualMap);
    }

    /**
     * Test if Small.txt with path in the form of '.' instead of ' ' is loaded correctly into a 2D array.
     *
     * @throws FileNotFoundException If the maze file is not found.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     */
    @Test
    public void loadTestSmallWithDotPath() throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        char[][] actualMap = fileLoader.load("maze-solver\\test\\mazes\\SmallWithDotPath.txt");
        char[][] correctMap = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '#', ' ', ' ', ' ', '#'},
                {'#', '.', '#', '#', '#', ' ', '#'},
                {'#', '.', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };
        assertArrayEquals(correctMap, actualMap);
    }

    /**
     * Test if a maze with even dimension throws IllegalArgumentException.
     *
     * @throws FileNotFoundException If the maze file is not found.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     */
    @Test (expected = IllegalArgumentException.class)
    public void loadTestWrongDimension()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\WrongDimension.txt");
    }

    /**
     * Test if a maze with no dimension throws IllegalArgumentException.
     *
     * @throws FileNotFoundException If the maze file is not found.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     */
    @Test (expected = IllegalArgumentException.class)
    public void loadTestNoDimension()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\NoDimension.txt");
    }

    /**
     * Test if a maze containing characters not part of the list ('#', ' ', '.', 'S', 'E') throws
     * MazeMalformedException.
     *
     * @throws FileNotFoundException If the maze file is not found.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     */
    @Test (expected = IllegalArgumentException.class)
    public void loadTestMapWrongCharacter()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\WrongCharacter.txt");
    }

    /**
     * Test if attempting to load a maze that does not exist throws FileNotFoundException.
     *
     * @throws FileNotFoundException If the maze file is not found.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     */
    @Test (expected = FileNotFoundException.class)
    public void loadTestNonExistentFile()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\NonExistentFile.txt");
    }

    /**
     * Test if a maze with no starting point throws MazeMalformedException.
     *
     * @throws FileNotFoundException If the maze file is not found.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     */
    @Test (expected = MazeMalformedException.class)
    public void loadTestMapNoStartPoint()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\NoStartPoint.txt");
    }

    /**
     * Test if a maze with no end point throws MazeMalformedException.
     *
     * @throws FileNotFoundException If the maze file is not found.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     */
    @Test (expected = MazeMalformedException.class)
    public void loadTestMapNoEndPoint()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\NoEndPoint.txt");
    }

    /**
     * Test if a maze containing too many end points throws MazeMalformedException.
     *
     * @throws FileNotFoundException If the maze file is not found.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     */
    @Test (expected = MazeMalformedException.class)
    public void loadTestMapTooManyEndPoints()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\TooManyEndPoints.txt");
    }

    /**
     * Test if a maze containing more rows than the specified dimension throws MazeSizeMissmatchException.
     *
     * @throws FileNotFoundException If the maze file is not found.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     */
    @Test (expected = MazeSizeMissmatchException.class)
    public void loadTestTooManyRows()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\TooManyRows.txt");
    }

    /**
     * Test if a maze containing fewer rows than the specified dimension throws MazeSizeMissmatchException.
     *
     * @throws FileNotFoundException If the maze file is not found.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     */
    @Test (expected = MazeSizeMissmatchException.class)
    public void loadTestTooFewRows()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\TooFewRows.txt");
    }

    /**
     * Test if a maze containing rows with greater width than the specified dimension throws MazeSizeMissmatchException.
     *
     * @throws FileNotFoundException If the maze file is not found.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     */
    @Test (expected = MazeSizeMissmatchException.class)
    public void loadTestTooLongWidth()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\TooLongWidth.txt");
    }

    /**
     * Test if a maze containing rows with shorter width than the specified dimension throws MazeSizeMissmatchException.
     *
     * @throws FileNotFoundException If the maze file is not found.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match the provided size.
     * @throws MazeMalformedException If the maze data is not correctly formatted.
     */
    @Test (expected = MazeSizeMissmatchException.class)
    public void loadTestTooLessWidth()
            throws FileNotFoundException, MazeSizeMissmatchException, MazeMalformedException {
        fileLoader.load("maze-solver\\test\\mazes\\TooLessWidth.txt");
    }

}