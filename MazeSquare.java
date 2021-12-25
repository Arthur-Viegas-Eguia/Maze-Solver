/**
* MazeSquare represents a single square within a Maze.
* @author Anna Rafferty
* @author Anya Vostinar
*/ 
public class MazeSquare {
    //Wall variables
    private boolean hasTopWall = false;
    private boolean hasRightWall = false;

    //Other variables
    private boolean visited = false;
    private boolean solutionPiece = false;
		
    //Location of this square in a larger maze.
    private int row;
    private int col;
		/**
     * Starts the class. Determines whether the maze square has top or right
     * walls and sets instance variables/
     * @param top a boolean with value set as true if the square has a top wall; false otherwise
     * @param right a boolean with value set as true if the square has a right wall; false otherwise
     * @param r an integer, representing in which row the maze the square is located.
     * @param c an integer, representing in which column of the maze square is located.
     */
    public MazeSquare(boolean top, boolean right, int r, int c) {
      hasTopWall = top;
      hasRightWall = right;
      row = r;
      col = c;
    }
		
    /**
     * Checks if the current maze square has a top wall
     * @return true if the current maze square has a top wall; false otherwise
     */
    public boolean hasTopWall() {
        return hasTopWall;
    }
		
    /**
     * Checks if the current maze square has a wall on the right
     * @return true if it has wall on the right; false otherwise
     */
    public boolean hasRightWall() {
        return hasRightWall;
    }
		
    /**
     * Returns the coordinate of the row of the labyrinth in which 
     * the current square is located
     * @return an integer, representing the row coordinate of the labyrinth where the current square is located
     */
    public int getRow() {
        return row;
    }
		
    /**
     * Returns the coordinate of the column of the labyrinth in which 
     * the current square is located
     * @return an integer, representing the column coordinate of the labyrinth where the current square is located
     */
    public int getColumn() {
        return col;
    }

    /**
     * Sets the maze square as visited
     */
    public void setVisited() {
      visited = true;
    }

    /**
     * Sets the maze square as not visited
     */
    public void setUnvisited() {
      visited = false;
    }

    /**
     * Says whether the program has already visited the maze square
     * @return true if the user has already visited the square; false otherwise
     */
    public boolean getVisited() {
      return visited;
    }

    /**
     * Says whether the square is part of the solution piece of the maze.
     * @return true if the square is part of the solution of the maze; false otherwise
     */
    public boolean getSolutionPiece() {
      return solutionPiece;
    }

    /**
     * Sets the maze square as part of the solution of the maze
     */
    public void setSolutionPiece() {
      solutionPiece = true;
    }

    /**
     * Sets the maze square as not part of the solution of the maze
     */
    public void resetSolutionPiece() {
      solutionPiece = false;
    }
    
    
} 