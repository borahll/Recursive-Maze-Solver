package labs.lab5;
//********************************************************************
//  Maze.java       Author: Lewis/Loftus
//
//  Represents a maze of characters. The goal is to get from the
//  top left corner to the bottom right, following a path of 1s.
//********************************************************************

public class Maze
{
   private final int TRIED = 2;
   private final int TRIED_PORTAL = 3;
   private final int PATH = 7;

   private int[][] grid = { {1, 4, 0, 4, 1, 1},
                            {0, 0, 0, 0, 0, 5},
                            {0, 0, 0, 1, 0, 0},
                            {0, 0, 0, 1, 1, 1},
                            {0, 0, 0, 0, 0, 0},
                            {5, 6, 0, 0, 6, 1}
                           };

   //-----------------------------------------------------------------
   //  Attempts to recursively traverse the maze. Inserts special
   //  characters indicating locations that have been tried and that
   //  eventually become part of the solution.
   //-----------------------------------------------------------------
   public boolean traverse (int row, int column)
   {
      boolean done = false;
      /**
       * the numbers reserved for portals are 4 5 6 
       */
      
      if (valid (row, column))
      {

         if(grid[row][column] == 4){
            grid[row][column] = TRIED_PORTAL;

            for(int i = 0; i < grid.length; i++){
               for(int j = 0; j < grid[0].length; j++){
                  if(grid[i][j] == 4){
                     row = i;
                     column = j;
                     grid[i][j] = TRIED_PORTAL;
                  }
               }
            }
         }
         else if(grid[row][column] == 5){
            grid[row][column] = TRIED_PORTAL;

            for(int i = 0; i < grid.length; i++){
               for(int j = 0; j < grid[0].length; j++){
                  if(grid[i][j] == 5){
                     row = i;
                     column = j;
                     grid[i][j] = TRIED_PORTAL;
                  }
               }
            }
         }
         else if(grid[row][column] == 6){
            grid[row][column] = TRIED_PORTAL;

            for(int i = 0; i < grid.length; i++){
               for(int j = 0; j < grid[0].length; j++){
                  if(grid[i][j] == 6){
                     row = i;
                     column = j;
                     grid[i][j] = TRIED_PORTAL;
                  }
               }
            }
         }
         else{
            grid[row][column] = TRIED;// this cell has been tried
         }  

         if (row == grid.length-1 && column == grid[0].length-1)
            done = true;  // the maze is solved
         else
         {
            done = traverse (row+1, column);     // down
            if (!done)
               done = traverse (row, column+1);  // right
            if (!done)
               done = traverse (row-1, column);  // up
            if (!done)
               done = traverse (row, column-1);  // left
         }

         if (done)  // this location is part of the final path
            grid[row][column] = PATH;
      }
      
      return done;
   }
   
   //-----------------------------------------------------------------
   //  Determines if a specific location is valid.
   //-----------------------------------------------------------------
   private boolean valid (int row, int column)
   {
      boolean result = false;
 
      // check if cell is in the bounds of the matrix
      if (row >= 0 && row < grid.length &&
          column >= 0 && column < grid[row].length)

         //  check if cell is not blocked and not previously tried
         if (grid[row][column] == 1 || grid[row][column] == 4|| grid[row][column] == 5 || grid[row][column] == 6)
            result = true;

      return result;
   }

   //-----------------------------------------------------------------
   //  Returns the maze as a string.
   //-----------------------------------------------------------------
   public String toString ()
   {
      String result = "\n";

      for (int row=0; row < grid.length; row++)
      {
         for (int column=0; column < grid[row].length; column++)
            result += grid[row][column] + "";
         result += "\n";
      }

      return result;
   }
}
