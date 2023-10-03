package view;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel that draws the provided maze in the GUI.
 * <p>
 * Can redraw with updated map.
 * </p>
 */
public class GUIPanel extends JPanel {
    /** maze to be drawn in the GUI. */
    private char[][] maze;

    /**
     * Initialises the maze and the panel with the dimension of the screen.
     *
     * @param maze 2D array of the provided maze.
     */
    public GUIPanel(char[][] maze) {
        this.maze = maze;

        // Get the screen size
        // set the size of the both the panel and frame at one place
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    /**
     * Overrides the default paintComponent to draw the maze.
     *
     * @param g the <code>Graphics</code> object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        int mazeWidth = maze[0].length;
        int mazeHeight = maze.length;

        // size of each cell in pixels
        // alter the size based on the dimension of the maze
        int cellSizeX = screenWidth / mazeWidth;
        int cellSizeY = screenHeight / mazeHeight;

        // calculate offset for the maze to centre it in the middle
        int offsetX = (screenWidth - cellSizeX * mazeWidth) / 2;
        int offsetY = (screenHeight - cellSizeY * mazeHeight) / 2;
        g.translate(offsetX, offsetY);

        // go through the maze and draw each block with the designated colour
        for (int row = 0; row < mazeHeight; row++) {
            for (int col = 0; col < mazeWidth; col++) {
                char cell = maze[row][col];
                int x = col * cellSizeX;
                int y = row * cellSizeY;

                // Map characters to the correct coloured block
                switch (cell) {
                    case '#' -> {
                        g.setColor(Color.BLACK);
                        g.fillRect(x, y, cellSizeX, cellSizeY);
                    }
                    case 'S' -> {
                        g.setColor(Color.GREEN);
                        g.fillRect(x, y, cellSizeX, cellSizeY);
                    }
                    case 'E' -> {
                        g.setColor(Color.RED);
                        g.fillRect(x, y, cellSizeX, cellSizeY);
                    }
                    case ' ', '.' -> {
                        g.setColor(Color.WHITE);
                        g.fillRect(x, y, cellSizeX, cellSizeY);
                    }
                    case 'T' -> {
                        g.setColor(Color.ORANGE);
                        g.fillRect(x, y, cellSizeX, cellSizeY);
                    }
                    case 'P' -> {
                        g.setColor(Color.red);
                        g.fillRect(x, y, cellSizeX, cellSizeY);
                    }
                }
            }
        }
    }

    /**
     * Redraws the maze based on the updated version
     *
     * @param newMaze 2D array of the new maze
     */
    public void redraw(char[][] newMaze) {
        this.maze = newMaze;
        repaint();
    }
}
