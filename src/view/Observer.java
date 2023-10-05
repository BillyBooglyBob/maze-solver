package view;

/**
 * Interface used to define different kinds of views that redraws the given maze in their chosen method
 * (e.g. text or GUI).
 */
public interface Observer {
    /**
     * Redraws the provided maze on the screen.
     *
     * @param maze 2D array containing the maze to redraw.
     */
    void redraw(char[][] maze);
}
