package view.GUIView;

import view.Observer;

/**
 * Sets up the GUIPanel (JPanel) which draws the maze and pass it to GUIFrame (JFrame).
 * <p>
 * Can directly interact with GUIPanel and redraw it.
 * </p>
 */
public class GUIView implements Observer {
    /** Stores the panel to ensure it can be redrawn. */
    private final GUIPanel mazePanel;

    /**
     * Creates the JFrame which contains the JPanel that draws the maze.
     *
     * @param maze 2D maze to be displayed.
     */
    public GUIView(char[][] maze) {
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
