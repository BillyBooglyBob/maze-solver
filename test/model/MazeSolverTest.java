package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.MazeView;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MazeSolverTest {
    private MazeSolver solver;

    // used for testing System.out.print() outputs
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUp() {
        MazeView view = new MazeView();
        solver = new MazeSolver(view);

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    @After
    public void tearDown() {
        solver = null;

        System.setOut(originalOut);
        System.setErr(originalErr);
    }

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