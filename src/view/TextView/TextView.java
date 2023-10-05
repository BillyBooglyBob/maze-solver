package view.TextView;

import view.Observer;

/**
 * Class dedicated to displaying the given maze using text with colours in the terminal.
 */
public class TextView implements Observer {
    /**
     * Redraws the maze using text with ANSI colours.
     *
     * @param maze new 2D maze to display in the terminal using text.
     */
    @Override
    public void redraw(char[][] maze) {
        // change the size based on maze dimension
        int blockSize = TextView.blockSize(maze);
        String block = TextView.createBlock(blockSize);

        // colours used for the blocks
        String red = "\u001B[41m";
        String green = "\u001B[42m";
        String grey = "\u001B[47m";
        String darkGrey = "\u001B[100m";

        // create blocks for each part of the maze
        String end = red + block;           // red
        String start = green + block;       // green
        String paths = grey + block;        // grey
        String walls = darkGrey + block;    // dark grey
        String traversed = green + block;   // green
        String solution = red + block;      // red

        String resetColor = "\u001B[0m";       // Reset color to default

        // go through the maze
        for (char[] row : maze) {
            for (char cell : row) {
                switch (cell) {
                    case 'S' -> System.out.print(start);
                    case 'E' -> System.out.print(end);
                    case ' ', '.' -> System.out.print(paths);
                    case '#' -> System.out.print(walls);
                    case 'T' -> System.out.print(traversed);
                    case 'P' -> System.out.print(solution);
                }
            }
            // used to reset the text colour to prevent colouring the entire line
            System.out.print(resetColor);
            System.out.println();
        }
        // to separate each maze by one line for clarity
        System.out.println();
    }

    /**
     * Get the size of the block to display depending on the size of the maze.
     *
     * @param maze 2D array of the current maze.
     * @return suitable size for a block of the maze that fits within the terminal.
     */
    private static int blockSize(char[][] maze) {
        int mazeWidth = maze[0].length;
        // 140 is the typical size of the terminal
        if (mazeWidth > (140 / 3)) {
            return 140 / mazeWidth;
        }
        // 3 is a suitable block size that ensures each cell of the maze is square
        return 3;
    }

    /**
     * Creates the block itself based on the calculated blockSize.
     *
     * @param blockSize size of the block (how many characters it will have).
     * @return String containing same number of spaces as the blockSize.
     * @require blockSize != null && blockSize >= 0
     * @ensure each cell of the maze displayed with accurate sizes.
     */
    private static String createBlock(int blockSize) {
        StringBuilder block = new StringBuilder();
        for (int i = 0; i < blockSize; i++) {
            block.append(" ");
        }
        return block.toString();
    }
}
