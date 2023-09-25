package model;

public class MazeSolver {
    public static boolean solveNextStep(char[][] maze, Coordinate coordinate) {
        int row = coordinate.getRow();
        int column = coordinate.getCol();
        // check if target node is reached
        if (maze[row][column] == 'E') {
            maze[row][column] = 'T';

            return true;
        }

        // when current position not visited, mark it as visited
        // T signifies traversed path
        if (maze[row][column] == ' ' || maze[row][column] == '.') {
            maze[row][column] = 'T';
        }


        // Define possible moves: left, right, up, down
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        // Explore all possible neighbor nodes
        for (int i = 0; i < 4; i++) {
            int newRow = row + dy[i];
            int newColumn = column + dx[i];

            // Check if the new position is within bounds and not a wall ('#') or visited ('T')
            if (newRow >= 0 && newRow < maze.length &&
                    newColumn >= 0 && newColumn < maze[0].length &&
                    (maze[newRow][newColumn] == ' ' || maze[newRow][newColumn] == 'E')) {
                coordinate.setRow(newRow);
                coordinate.setCol(newColumn);
                return true;
            }
        }

        return false;
    }
}
