# Maze-Solver
Homework about stacks developed for Data Structures.
## Overview:
The program models an algorithm to find the solution to mazes, and prints it on the screen.
With this program you can:
- Upload your own mazes for the program via external files
- Print the maze on the screen (without the solution)
- Automatically find the solution to the maze
- Print the solution of the maze on the screen
- Having the program inform you that there is no possible solution, if this is the case
## Usage
To use the program, you have to compile and run the code after downloading. To do this, simply
type in the terminal:
```
javac *.java
java Maze value
```
Where value represents the path of the external file which contains the maze. The file containing
the maze should follow the following format:
```
<number of rows> <number of columns>
<Number of the start row(in a grid that starts from 0)> <Number of the start column(in a grid
that starts from 0)>
<Number of the finish row(in a grid that starts from 0)> <Number of the finish column(in a grid
that starts from 0)>
<One row>
<described>
<per>
<line>
```
The symbols to describe the rows are:
- 7 if there is a top and a right wall
- | (pipe) if there is a right wall but no top wall
- _ if there is a top wall but not a right wall
- \* The square has neither top nor right wall



Use as many symbols in a single row as the number squares you have per row.
All Operations of the program depend on a maze being loaded. So it is of paramount importance
that the file provided has the format specified and the path to it is correct.
