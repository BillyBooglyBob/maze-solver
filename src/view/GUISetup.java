package view;

import javax.swing.*;
import java.awt.*;

/**
 * Set up the JFrame which contains the GUIView JPanel based on provided maze initialises the JPanel.
 */
public class GUISetup {
    /** Stores the panel to ensure it can be redrawn. */
    private GUIView mazePanel;

    /**
     * Creates the JFrame which contains the JPanel that draws the maze.
     *
     * @param maze 2D maze to be displayed.
     */
    public void init(char[][] maze) {
        // Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        // initialise the panel to the size of the screen
        this.mazePanel = new GUIView(maze);
        mazePanel.setPreferredSize(new Dimension(screenWidth, screenHeight));

        // initialise the frame
        JFrame frame = new JFrame("Maze Solver");
        frame.add(mazePanel);
        frame.setSize(screenWidth, screenHeight);
        frame.setLocationRelativeTo(null); // Set the frame to the centre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
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
