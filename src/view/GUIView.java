package view;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel that draws the provided maze in the GUI
 *
 * <p>
 *     Can redraw with updated map
 * </p>
 */
public class GUIView extends JPanel {
    private char[][] maze;

    /**
     * Initialises the maze
     *
     * @param maze 2D array of the provided maze
     */
    public GUIView(char[][] maze) {
        this.maze = maze;
    }

    /**
     * Overrides the default paintComponent to draw the maze.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // size of each cell in pixels
        // alter the size based on the dimension of the maze
        int cellSizeX = 700 / maze[0].length - 1;
        int cellSizeY = 700 / maze.length - 1;
        g.translate(35, 20);

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                char cell = maze[row][col];
                int x = col * cellSizeX;
                int y = row * cellSizeY;

                // Map characters to colors or images
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
                    // Add more cases for other characters if needed
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
