import model.MazeSolver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.MazeView;

import static org.junit.Assert.*;

public class LoaderTest {


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void mainNoPath() {
        char[][] maze = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', '#', '#'},
                {'#', ' ', ' ', ' ', '#', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };

        int row = 1;
        int col = 1;

        MazeView view = new MazeView("GUI", maze);
        MazeSolver solver = new MazeSolver(view);
    }


}