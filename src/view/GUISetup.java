package view;

import javax.swing.*;
import java.awt.*;

/**
 * Set up the JFrame which contains the GUIView JPanel
 * Based on provided maze initialises the JPanel
 */
public class GUISetup {
    private GUIView mazePanel;

    /**
     * Creates the JFrame which contains the JPanel that draws the maze
     *
     * @param maze 2D array of the provided maze
     */
    public void init(char[][] maze) {
        JFrame frame = new JFrame("Maze Visualization");
        this.mazePanel = new GUIView(maze);

        frame.add(mazePanel);
        frame.setSize(400, 400); // Set the frame size accordingly
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Centre the frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;

        // Set the frame's location to the center of the screen
        frame.setLocation(x, y);
    }

    public void redraw(char[][] newMaze) {
        mazePanel.redraw(newMaze);
    }
}
