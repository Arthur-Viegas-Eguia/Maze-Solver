import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

/**
* Class that models a Maze. it loads the maze from an external text file and 
* has methods to print the it on the screen and automatically solve it.
*
*/
public class Maze { 
    //Number of rows in the maze.
    private int numRows;
    
    //Number of columns in the maze.
    private int numColumns;
    
    //Grid coordinates for the starting maze square
    private int startRow;
    private int startColumn;
    
    //Grid coordinates for the final maze square
    private int finishRow;
    private int finishColumn;
    
    //A double array of the MazeSquares in the maze
    MazeSquare[][] squares;
    
    /**
     * Creates an empty maze with no squares. This is the default constructor
     * of the class.
     */
    public Maze() {
    } 
    
     /**
     * Reads the maze from an external file.
     * Constructing the class.
     * @fileName a String containing the path to a file with the labyrinth
     */
    public boolean load(String fileName) { 
      File inputFile = new File(fileName);
      Scanner scanner = null;
      try {
        scanner = new Scanner(inputFile);
      } catch (FileNotFoundException e) {
        System.err.println(e);
        System.exit(1);
      }

      numRows = Integer.parseInt(scanner.next());
      numColumns = Integer.parseInt(scanner.next());
      squares = new MazeSquare[numRows][numColumns];
      startRow = Integer.parseInt(scanner.next());
      startColumn = Integer.parseInt(scanner.next());
      finishRow = Integer.parseInt(scanner.next());
      finishColumn = Integer.parseInt(scanner.next());

      for(int i=0; i<numRows; i++){
        String linecode = scanner.next();
        for (int j=0; j<numColumns; j++) {
          String code = linecode.substring(j,j+1);
          if(code.equals("7")){
            squares[i][j] = new MazeSquare(true, true, i, j);
          } else if(code.equals("|")) {
            squares[i][j] = new MazeSquare(false, true, i, j);
          } else if(code.equals("_")) {
            squares[i][j] = new MazeSquare(true, false, i, j);
          } else if(code.equals("*")) {
            squares[i][j] = new MazeSquare(false, false, i, j);
          } else {
            return false;
          }
          
        }
      }
      

      return true;
    }  
    
    /**
     * Checks if number, which represents either a row or a column coordinate
     * of the maze, is inbounds.
     * @param number an integer value representing either the row or column coordinate of a point in the maze
     * @param lowerBound an integer representing the lower bound of the maze (either in a row or column)
     * @param upperBounnd an integer representing the upper bound of the maze (either in a row or column)
     * @return true if the point is within the boundaries or the labyrinth; otherwise return false.
     */
    private static boolean isInRange(int number, int lowerBound, int upperBound) {
        return number < upperBound && number >= lowerBound;
    }
    
    /**
     * Prints the maze on the screen, with or without its solution.
     * @solution a boolean, if true the method will print the solution of the maze; if false it will print the maze without the solution
     */
    public void print(boolean solution) {
        //We'll print off each row of squares in turn.
        for(int row = 0; row < numRows; row++) {
            
            //Print each of the lines of text in the row
            for(int charInRow = 0; charInRow < 4; charInRow++) {
                //Need to start with the initial left wall.
                if(charInRow == 0) {
                    System.out.print("+");
                } else {
                    System.out.print("|");
                }
                
                for(int col = 0; col < numColumns; col++) {
                    MazeSquare curSquare = this.getMazeSquare(row, col);
                    if(charInRow == 0) {
                        //We're in the first row of characters for this square - need to print
                        //top wall if necessary.
                        if(curSquare.hasTopWall()) {
                            System.out.print(getTopWallString());
                        } else {
                            System.out.print(getTopOpenString());
                        }
                    } else if(charInRow == 1 || charInRow == 3) {
                        //These are the interior of the square and are unaffected by
                        //the start/final state.
                        if(curSquare.hasRightWall()) {
                            System.out.print(getRightWallString());
                        } else {
                            System.out.print(getOpenWallString());
                        }
                    } else {
                        //We must be in the second row of characters.
                        //This is the row where start/finish should be displayed if relevant
                        
                        //Check if we're in the start or finish state
                        if(startRow == row && startColumn == col) {
                            System.out.print("  S  ");
                        } else if(finishRow == row && finishColumn == col) {
                            System.out.print("  F  ");
                        } else if(curSquare.getSolutionPiece()) {
                          System.out.print("  *  ");
                        } else {
                            System.out.print("     ");
                        }
                        if(curSquare.hasRightWall()) {
                            System.out.print("|");
                        } else {
                            System.out.print(" ");
                        }
                    } 
                }
                
                //Now end the line to start the next
                System.out.print("\n");
            }           
        }
        
        //Finally, we have to print off the bottom of the maze, since that's not explicitly represented
        //by the squares. Printing off the bottom separately means we can think of each row as
        //consisting of four lines of text.
        printFullHorizontalRow(numColumns);
    }
    
    /**
     * Prints the very bottom row of characters for the bottom row of maze squares (which is always walls).
     * numColumns is the number of columns of bottom wall to print.
     */
    private static void printFullHorizontalRow(int numColumns) {
        System.out.print("+");
        for(int row = 0; row < numColumns; row++) {
            //We use getTopWallString() since bottom and top walls are the same.
            System.out.print(getTopWallString());
        }
        System.out.print("\n");
    }
    
    /**
     * Returns a String representing the bottom of a horizontal wall.
     */
    private static String getTopWallString() {
        return "-----+";
    }
    
    /**
     * Returns a String representing the bottom of a square without a
     * horizontal wall.
     */
    private static String getTopOpenString() {
        return "     +";
    }
    
    /**
     * Returns a String representing a left wall (for the interior of the row).
     */
    private static String getRightWallString() {
        return "     |";
    }
    
    /**
     * Returns a String representing no left wall (for the interior of the row).
     */
    private static String getOpenWallString() {
        return "      ";
    }
    
    /**
     * gets a square of the maze, based on its row and column position.
     * @param row an integer representing the coordinate of the row of the desired square
     * @param col an integer representing the coordinate of the column of the desired square
     * @return an object of type MazeSquare, representing the square in the desired coordinates.
     */
    public MazeSquare getMazeSquare(int row, int col) {
        return squares[row][col];
    }
    /**
     * Moves to the right of the current square, if possible.
     * @param row an integer, representing the coordinates of the row of the current square of the maze
     * @param column an integer, representing the coordinates of the column of the current square of the maze
     * @return an object of type MazeSquare with the square to the right of the current one if it was possible to move; null otherwise
     */
    private MazeSquare goRight(int row, int column){
      MazeSquare currentSquare = squares[row][column];
      MazeSquare toReturn = null;
      column++;
      if(isInRange(column, 0, numColumns) && !currentSquare.hasRightWall()) {
        currentSquare = squares[row][column];
        if(!currentSquare.getVisited()){
          currentSquare.setVisited();
          currentSquare.setSolutionPiece();
          toReturn = currentSquare;
        }
      }
      return toReturn;
    }
    /**
     * Moves to the left of the current square, if possible.
     * @param row an integer, representing the coordinates of the row of the current square of the maze
     * @param column an integer, representing the coordinates of the column of the current square of the maze
     * @return an object of type MazeSquare with the square to the left of the current one if it was possible to move; null otherwise
     */
    private MazeSquare goLeft(int row, int column){
      column--;
      MazeSquare toReturn = null;
      MazeSquare currentSquare = null;
      if(isInRange(column, 0, numColumns)){
        currentSquare = squares[row][column];
        if(!currentSquare.hasRightWall() && !currentSquare.getVisited()){
          currentSquare.setVisited();
          currentSquare.setSolutionPiece();
          toReturn = currentSquare;
        }
      }
      return toReturn;
    }
    /**
     * Moves to the square on top of the current one, if possible.
     * @param row an integer, representing the coordinates of the row of the current square of the maze
     * @param column an integer, representing the coordinates of the column of the current square of the maze
     * @return an object of type MazeSquare with the square to the top of the current one if it was possible to move; null otherwise
     */
    private MazeSquare goTop(int row, int column){
      MazeSquare currentSquare = squares[row][column];
      MazeSquare toReturn = null;
      row--;
      if(isInRange(row, 0, numRows) && !currentSquare.hasTopWall()){
        currentSquare = squares[row][column];
        if(!currentSquare.getVisited()){
          currentSquare.setVisited();
          currentSquare.setSolutionPiece();
          toReturn = currentSquare;
        }
      }
      return toReturn;
    }
    /**
     * Moves down from the current square, if possible.
     * @param row an integer, representing the coordinates of the row of the current square of the maze
     * @param column an integer, representing the coordinates of the column of the current square of the maze
     * @return an object of type MazeSquare with the square to down of the current one if it was possible to move; null otherwise
     */
    private MazeSquare goBottom(int row, int column){
      row++;
      MazeSquare toReturn = null;
      MazeSquare currentSquare = null;
      if(isInRange(row, 0, numRows)){
        currentSquare = squares[row][column];
        if(!currentSquare.hasTopWall() && !currentSquare.getVisited()){
          currentSquare.setVisited();
          currentSquare.setSolutionPiece();
          toReturn = currentSquare;
        }
      }
      return toReturn;
    }
    
    /**
    * Algorythm which solves the maze automatically and returns the solution path 
    * or null if null is unsolvable.
    * @return a Stack object of type MazeSquare representing the solution of the maze; null if the maze is unsolvable
    */
    public Stack<MazeSquare> getSolution() {
      Stack<MazeSquare> solution = new LinkedStack<MazeSquare>();
      int row = startRow;
      int column = startColumn;
      MazeSquare currentSquare = squares[row][column];
      solution.push(currentSquare);
      currentSquare.setVisited();
      currentSquare.setSolutionPiece();
      while(!solution.isEmpty()){
        if(solution.peek() == squares[finishRow][finishColumn]){
          break;
        }
	      currentSquare = solution.peek();
	      row = currentSquare.getRow();
	      column = currentSquare.getColumn();
	      currentSquare = goTop(row, column);
	      if(currentSquare != null){
	        solution.push(currentSquare);
	        continue;
	      }
	      currentSquare = goBottom(row, column);
	      if(currentSquare != null){
	        solution.push(currentSquare);
	        continue;
	      }
	      currentSquare = goLeft(row, column);
				if(currentSquare != null){
					solution.push(currentSquare);
					continue;
			  }
        currentSquare = goRight(row, column);
        if(currentSquare != null){
	        solution.push(currentSquare);
	        continue;
	      }
        currentSquare = solution.peek();
        currentSquare.resetSolutionPiece();
        solution.pop();
      }
      return solution;
    }
    private void removeSolution(Stack<MazeSquare> stack){
      MazeSquare currentItem = null;
      while(!stack.isEmpty()){
        currentItem = stack.pop();
        currentItem.resetSolutionPiece();
      }
    }
 
    /**
     * Loads a maze from an external file provided by the user. Provides users
     * with the option to print a maze without the solution. Solves the maze.
     * Prints a message if the maze is unsolvable.
     * @param args args[0] is the path to an external file containing the maze.
     */ 
    public static void main(String[] args) {
      Maze myMaze = null;
      boolean breakLoop = true;
      int userValue;
      Scanner userInput;
      Stack<MazeSquare> solution;
      if(args.length == 1) {
        myMaze = new Maze();
        myMaze.load(args[0]);
      } else {
        System.out.println("Please provide a path to a file which contains a maze.");
        System.exit(1);
      }
      userInput = new Scanner(System.in);
      while(breakLoop){
        System.out.println("Do you want the program to print the maze without its solution? If so type 1, otherwise type 2");
        userValue = userInput.nextInt();
        switch(userValue){
          case 1:
          myMaze.print(false);
          breakLoop = false;
          break;
          case 2:
          breakLoop = false;
          break;
          default:
          System.out.println("Please make sure to type either 1 or 2");
          break;
        }
      }
      solution = myMaze.getSolution();
      if(!solution.isEmpty()){
        breakLoop = true;
        while(breakLoop){
          System.out.println("Do you want the program to print the maze without solution, with its solution or not print at all? type 1 to print maze with solution, type 2 to print maze without solution, type 3 to exit the program");
          userValue = userInput.nextInt();
          switch(userValue){
            case 1:
            myMaze.print(true);
            breakLoop = false;
            break;
            case 2:
            myMaze.removeSolution(solution);
            myMaze.print(false);
            breakLoop = false;
            break;
            case 3:
            breakLoop = false;
            break;
            default:
            System.out.println("Please make sure to type either 1, 2 or 3");
            break;
          }
        }
      } else {
        System.out.println("The maze you have provided has no solution, please try uploading another maze");
      }
      userInput.close();
    } 
}
