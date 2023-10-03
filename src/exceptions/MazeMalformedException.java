package exceptions;

/**
 * Thrown if the maze data doesn't match the given format.
 * <p>
 * Examples:
 * The first line is not the dimensions of the maze.
 * First and last row of the maze are not all walls ('#').
 * Start and end of every single row are not walls ('#').
 * Content of the maze are not part of the list ('#', ' ', '.', 'S', 'E').
 * There is not only one start and end point.
 */
public class MazeMalformedException extends Exception {
}
