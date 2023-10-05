package view.GUIView;

import control.Control;
import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import io.FileLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Create the JFrame which contains the JPanel that draws the maze and menu to select mazes to solve.
 */
public class GUIFrame extends JFrame implements ActionListener {
    /** Required to be an instance variable to allow access by the action listener. */
    private final JMenuItem selectMaze;

    /**
     * Initialises the JFrame with the JPanel that draws the maze.
     *
     * @param mazePanel JPanel which actually draws the provided maze.
     */
    public GUIFrame(GUIPanel mazePanel) {
        // initialise the frame
        this.setTitle("Maze Solver");
        this.add(mazePanel);
        this.setLocationRelativeTo(null); // Set the frame to the centre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the GUI when close button is clicked
        this.setVisible(true);
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // maximise the screen by default

        // create menu
        JMenuBar menuBar = new JMenuBar();
        JMenu mazeMenu = new JMenu("Mazes");

        selectMaze = new JMenuItem("Select Maze");
        selectMaze.addActionListener(this);

        mazeMenu.add(selectMaze);
        menuBar.add(mazeMenu);
        this.setJMenuBar(menuBar);
    }

    /**
     * Select a file from the mazes directory and attempt to solve it.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectMaze) {
            JFileChooser fileChooser = new JFileChooser();

            // set default directory opened
            fileChooser.setCurrentDirectory(new File("./mazes"));

            // select file to open
            int response = fileChooser.showOpenDialog(null);

            // if a file is opened attempt to solve it
            if (response == JFileChooser.APPROVE_OPTION) {
                // close the current maze solver before re-opening a new one with the selected maze
                this.dispose();

                // start solving the maze in a background thread
                // ensure the GUI is responsive while the maze is being solved by separating the latter from the
                // EDT to prevent GUI freezing
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        Control control = getControl(fileChooser);
                        control.solveMaze();
                        return null;
                    }
                };

                worker.execute();
            }
        }
    }

    /**
     * Solves the chosen file.
     *
     * @param fileChooser contains the file chosen by the user.
     * @return Control class initialised with the maze chosen and selected to display in GUI.
     */
    private static Control getControl(JFileChooser fileChooser) {
        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

        // convert file path to the String format for use by the FileLoader
        String mazeFile = file.toString();

        // set default display mode to be GUI
        String displayMode = "GUI";

        // load the chosen maze into 2D array
        FileLoader fileLoader = new FileLoader();
        char[][] maze;
        try {
            maze = fileLoader.load(mazeFile);
        } catch (MazeMalformedException | MazeSizeMissmatchException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return new Control(displayMode, maze);
    }
}
