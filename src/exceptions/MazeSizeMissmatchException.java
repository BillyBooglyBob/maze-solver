package exceptions;

/**
 * Thrown if the maze data doesn't match the specified dimensions.
 * <p>
 * Examples:
 * Dimension at the start does not match up with the actual maze.
 * - length of the row != width of the dimension.
 * - number of rows != height of the dimension.
 */
public class MazeSizeMissmatchException extends Exception {

}
