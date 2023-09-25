import model.Coordinate;
import model.MazeSolver;
import view.MazeView;

import java.util.ArrayList;
import java.util.List;

public class Loader {
    public static void main(String[] args) {
        char[][] maze = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };

        List<Integer> path = new ArrayList<>();

        Coordinate start = new Coordinate(1, 1);
        MazeSolver.solveNextStep(maze, start);
        MazeView view = new MazeView();
        view.redraw(maze);

        while(MazeSolver.solveNextStep(maze, start)) {
            view.redraw(maze);
        }
        view.redraw(maze);





    }
}
