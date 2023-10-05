package view;

import model.MazeSolver;
import view.GUIView.GUIView;
import view.TextView.TextView;

/**
 * Class for displaying the maze in the terminal or in the GUI.
 * <p>
 * Two constructors made, one for the default text display and the other for the GUI display.
 * </p>
 */
public class MazeView {

    /**
     * Constructor that lets the user decide between text or GUI display of the maze.
     * <p>
     * Depending on the mode, creates instance of that view and adds it to the Model's observer list for updating.
     * </p>
     *
     * @param mazeSolver used to add instance of the view chosen to its observers' list, so it can update it when
     *                   solving the maze.
     * @param displayMode mode that specifies whether the maze will be displayed using text or GUI.
     * @param maze 2D array of the maze to display.
     */
    public MazeView(MazeSolver mazeSolver, String displayMode, char[][] maze) {
        if (displayMode.equals("GUI")) {
            GUIView GUIView = new GUIView(maze);
            mazeSolver.addObserver(GUIView);
        } else {
            view.TextView.TextView textView = new TextView();
            mazeSolver.addObserver(textView);
        }
    }
}
