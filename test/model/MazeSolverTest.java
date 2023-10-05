package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.MazeView;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Tests the MazeSolver with various mazes and modes
 * <p>
 * Did not use the Fileloader.load() method for the tests to make sure each unit testing is independent.
 * </p>
 */
public class MazeSolverTest {
    /** Variable shared by all the tests to avoid repetition. */
    private MazeSolver solver;

    /** used for testing System.out.print() outputs */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    /**
     * Create the MazeSolver instance with the default MazeView (text display) to avoid repetitive setup in the tests.
     */
    @Before
    public void setUp() {
        MazeView view = new MazeView();
        solver = new MazeSolver(view);

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Clean up after each test to make sure every test starts at the same consistent states.
     */
    @After
    public void tearDown() {
        solver = null;

        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * Solves the Small.txt maze which is solvable.
     */
    @Test
    public void solveMazeSmall() {
        char[][] maze = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };

        boolean solutionFound = solver.solveMaze(maze, 1, 1);
        char[][] solvedMaze = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'P', '#', 'T', 'T', 'T', '#'},
                {'#', 'P', '#', '#', '#', 'T', '#'},
                {'#', 'P', '#', 'P', 'P', 'P', '#'},
                {'#', 'P', '#', 'P', '#', 'P', '#'},
                {'#', 'P', 'P', 'P', '#', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };
        assertArrayEquals(maze, solvedMaze);
        assertTrue(solutionFound);
    }

    /**
     * Solves a maze which is not solvable.
     */
    @Test
    public void solveMazeNoSolution() {
        char[][] maze = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', '#', '#'},
                {'#', ' ', ' ', ' ', '#', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };

        boolean solutionFound = solver.solveMaze(maze, 1, 1);
        char[][] solvedMaze = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'T', '#', 'T', 'T', 'T', '#'},
                {'#', 'T', '#', '#', '#', 'T', '#'},
                {'#', 'T', '#', 'T', 'T', 'T', '#'},
                {'#', 'T', '#', 'T', '#', '#', '#'},
                {'#', 'T', 'T', 'T', '#', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };
        assertArrayEquals(maze, solvedMaze);
        assertFalse(solutionFound);
    }

    /**
     * Check the success message is printed for the maze which is solvable.
     */
    @Test
    public void showMazeSolutionSmallGUI() {
        char[][] maze = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };
        MazeView GUIView = new MazeView("GUI", maze);
        solver = new MazeSolver(GUIView);
        solver.showMazeSolution(maze, 1, 1);
        assertEquals("Path found", outContent.toString());
    }

    /**
     * Check the failure message is printed for the maze which is not solvable.
     */
    @Test
    public void showMazeSolutionNoSolutionGUI() {
        char[][] maze = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', '#', '#'},
                {'#', ' ', ' ', ' ', '#', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };
        MazeView GUIView = new MazeView("GUI", maze);
        solver = new MazeSolver(GUIView);
        solver.showMazeSolution(maze, 1, 1);
        assertEquals("No path", outContent.toString());
    }
}