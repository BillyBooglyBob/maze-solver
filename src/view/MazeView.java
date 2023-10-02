package view;

/**
 * Class for displaying the maze in the terminal or in the GUI.
 */
public class MazeView {
    /*
    use constructor to decide which mode (terminal/GUI to implement)
    if it says GUI make it GUI, else make it terminal
     */
    private final String displayMode;
    private GUISetup GUIDisplay;

    /**
     * Initialise the default display mode to text.
     */
    public MazeView() {
        this.displayMode = "text";
    }

    /**
     * Constructor that lets the user decide between text or GUI display of the maze.
     * <p>
     * Default display is text.
     * </p>
     *
     * @param displayMode mode that specifies whether the maze will be displayed using text or GUI.
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
