package view;

/**
 * Set up the JFrame which contains the GUIView JPanel based on provided maze initialises the JPanel.
 */
public class GUISetup {
    /** Stores the panel to ensure it can be redrawn. */
    private GUIPanel mazePanel;

    /**
     * Creates the JFrame which contains the JPanel that draws the maze.
     *
     * @param maze 2D maze to be displayed.
     */
    public void init(char[][] maze) {
        mazePanel = new GUIPanel(maze);
        new GUIFrame(mazePanel);
    }

    /**
     * Redraws the maze based on the new updated maze.
     *
     * @param newMaze new 2D maze to display.
     */
    public void redraw(char[][] newMaze) {
        mazePanel.redraw(newMaze);
    }
}
