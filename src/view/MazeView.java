package view;

/**
 * Class for displaying the maze in the terminal or in the GUI
 */
public class MazeView {
    /*
    use constructor to decide which mode (terminal/GUI to implement)
    if it says GUI make it GUI, else make it terminal
     */
    private final String displayMode;

    public MazeView() {
        this.displayMode = "text";
    }

    /**
     * Constructor that lets the user decide between text or GUI display of the maze.
     *
     * <p>
     *     Default display is text.
     * </p>
     *
     * @param displayMode mode that selects the way the maze will be display
     *                    either in the terminal or in a GUI
     */
    public MazeView(String displayMode) {
        if (displayMode.equals("GUI")) {
            this.displayMode = displayMode;
        } else {
            this.displayMode = "text";
        }
    }

    /**
     * Redraws the maze based on the updated version in the selected mode.
     * @param maze 2D array of the current maze
     */
    public void redraw(char[][] maze) {
        if (displayMode.equals("text")) {
            TextView.redraw(maze);
        } else {
            GUISetup GUIDisplay = new GUISetup();
            GUIDisplay.init(maze);

        }
    }

    public static void main(String[] args) {
        MazeView view = new MazeView("GUI");
        char[][] maze = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', '#', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };
        view.redraw(maze);

        char[][] newMaze = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '#', ' ', ' ', ' ', '#'},
                {'#', 'S', '#', '#', '#', ' ', '#'},
                {'#', 'S', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', 'E', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };
        view.redraw(newMaze);
    }
}
