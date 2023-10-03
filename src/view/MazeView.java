package view;

/**
 * Class for displaying the maze in the terminal or in the GUI.
 * <p>
 * Two constructors made, one for the default text display and the other for the GUI display.
 * </p>
 */
public class MazeView {

    /** Mode chosen to display the maze (either text or GUI). */
    private final String displayMode;

    /** Used to display the maze in the GUI */
    private GUISetup GUIDisplay;

    /**
     * Default constructor which makes the default display mode text.
     */
    public MazeView() {
        this.displayMode = "text";
    }

    /**
     * Constructor that lets the user decide between text or GUI display of the maze.
     *
     * @param displayMode mode that specifies whether the maze will be displayed using text or GUI.
     * @param maze 2D array of the maze to display.
     */
    public MazeView(String displayMode, char[][] maze) {
        if (displayMode.equals("GUI")) {
            this.displayMode = displayMode;
            GUIDisplay = new GUISetup();
            GUIDisplay.init(maze);
        } else {
            this.displayMode = "text";
        }
    }

    /**
     * Redraws the maze based on the updated version in the selected mode.
     *
     * @param maze 2D array of the current maze.
     */
    public void redraw(char[][] maze) {
        if (displayMode.equals("text")) {
            TextView.redraw(maze);
        } else {
            GUIDisplay.redraw(maze);
        }
    }
}
