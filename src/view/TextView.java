package view;

public class TextView {
    public static void redraw(char[][] newMaze) {
        String end = "\u001B[41m   ";      // red
        String start = "\u001B[42m   ";    // green
        String paths = "\u001B[47m   ";    // grey
        String walls = "\u001B[100m   ";   // dark grey
        String traversed = "\u001B[42m   ";   // green
        String solution = "\u001B[41m   ";    // red
        String resetColor = "\u001B[0m";       // Reset color to default


        for (char[] i : newMaze) {
            for (char j : i) {
                switch (j) {
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
}
