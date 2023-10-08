# maze-solver
Automated maze solver using Depth First Search with two display modes and option to select different mazes to display.


**Maze requirement**

'#' = wall

' ', '.' = path

'S' = start

'E' = end

Maze must be uniform with walls on the outside.


**How to run**

Run using the terminal by providing the display mode and maze to solve.


instructions:

java Launcher.java "display mode" "maze"
display mode can be either text or GUI
- leave to select text mode.
- replace with "GUI" to select GUI mode (in GUI mode, can select different mazes to solve without needing to recompile)
  
3 mazes to choose from
- Small.txt
- Medium.txt
- Large.txt
type the exact name of the file to select it.

example 1:
solves the Small.txt maze in text view in the terminal
- navigate to src folder
- go to terminal and type
- javac Launcher.java
- java Launcher.java Small.txt


example 2:
solves the Medium.txt maze in GUI view. 
- navigate to src folder
- go to terminal and type
- javac Launcher.java
- java Launcher.java GUI Medium.txt
    
