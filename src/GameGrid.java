import java.awt.Color; // the color type used in StdDraw
import java.awt.Font;

// A class used for modelling the game grid
public class GameGrid {
   // data fields
   private int gridHeight, gridWidth; // the size of the game grid
   private Tile[][] tileMatrix; // to store the tiles locked on the game grid
   // the tetromino that is currently being moved on the game grid
   private Tetromino currentTetromino = null;
   // the gameOver flag shows whether the game is over or not
   private boolean gameOver = false;
   private Color emptyCellColor; // the color used for the empty grid cells
   private Color lineColor; // the color used for the grid lines
   private Color boundaryColor; // the color used for the grid boundaries
   private double lineThickness; // the thickness used for the grid lines
   private double boxThickness; // the thickness used for the grid boundaries
   public static int score=0;
   Color textColor = new Color(31, 160, 239);
   Color buttonColor = new Color(25, 255, 228);

   // A constructor for creating the game grid based on the given parameters
   public GameGrid(int gridH, int gridW) {
      // set the size of the game grid as the given values for the parameters
      gridHeight = gridH;
      gridWidth = gridW;
      // create the tile matrix to store the tiles locked on the game grid
      tileMatrix = new Tile[gridHeight][gridWidth];
      // set the color used for the empty grid cells
      emptyCellColor = new Color(42, 69, 99);
      // set the colors used for the grid lines and the grid boundaries
      lineColor = new Color(0, 100, 200);
      boundaryColor = new Color(0, 100, 200);
      // set the thickness values used for the grid lines and the grid boundaries
      lineThickness = 0.002;
      boxThickness = 10 * lineThickness;
   }

   // A setter method for the currentTetromino data field
   public void setCurrentTetromino(Tetromino currentTetromino) {
      this.currentTetromino = currentTetromino;
   }
   // A method used for displaying the game grid
   public void display() {
      // clear the background to emptyCellColor
      StdDraw.clear(emptyCellColor);
      // draw the game grid
      drawGrid();
      // draw the current/active tetromino if it is not null (the case when the
      // game grid is updated)
      if (currentTetromino != null)
         currentTetromino.draw();
      // draw a box around the game grid
      drawBoundaries();
      // show the resulting drawing with a pause duration = 50 ms
      StdDraw.show();
      StdDraw.pause(50);
   }

   // A method for drawing the cells and the lines of the game grid
   public void drawGrid() {
      // for each cell of the game grid
      for (int row = 0; row < gridHeight; row++)
         for (int col = 0; col < gridWidth; col++)
            // draw the tile if the grid cell is occupied by a tile
            if (tileMatrix[row][col] != null)
               tileMatrix[row][col].draw(new Point(col, row));
      // draw the inner lines of the grid
      StdDraw.setPenColor(lineColor);
      StdDraw.setPenRadius(lineThickness);
      // x and y ranges for the game grid
      double startX = -0.5, endX = gridWidth - 0.5;
      double startY = -0.5, endY = gridHeight - 0.5;
      for (double x = startX + 1; x < endX; x++) // vertical inner lines
         StdDraw.line(x, startY, x, endY);
      for (double y = startY + 1; y < endY; y++) // horizontal inner lines
         StdDraw.line(startX, y, endX, y);
      StdDraw.setPenRadius(); // reset the pen radius to its default value
      //show the score  
      Font font = new Font("Arial", Font.PLAIN, 25);
      StdDraw.setFont(font);
      StdDraw.setPenColor(textColor);   
      StdDraw.text(11.6, 17, "Your Score");
      StdDraw.text(11.6, 16, getScore());
   }

   // A method for drawing the boundaries around the game grid
   public void drawBoundaries() {
      // draw a bounding box around the game grid as a rectangle
      StdDraw.setPenColor(boundaryColor); // using boundaryColor
      // set the pen radius as boxThickness (half of this thickness is visible
      // for the bounding box as its lines lie on the boundaries of the canvas)
      StdDraw.setPenRadius(boxThickness);
      // the center point coordinates for the game grid
      double centerX = (gridWidth+0.1) / 2 - 0.5, centerY = gridHeight / 2 - 0.5;
      StdDraw.rectangle(centerX, centerY, (gridWidth+0.1) / 2, gridHeight / 2);
      StdDraw.setPenRadius(); // reset the pen radius to its default value
   }

   // A method for checking whether the grid cell with given row and column
   // indexes is occupied by a tile or empty
   public boolean isOccupied(int row, int col) {
      // considering newly entered tetrominoes to the game grid that may have
      // tiles out of the game grid (above the topmost grid row)
      if (!isInside(row, col))
         return false;
      // the cell is occupied by a tile if it is not null
      return tileMatrix[row][col] != null;
   }

   // A method for checking whether the cell with given row and column indexes
   // is inside the game grid or not
   public boolean isInside(int row, int col) {
      if (row < 0 || row >= gridHeight)
         return false;
      if (col < 0 || col >= gridWidth)
         return false;
      return true;
   }

   // A method for checking the all columns to find a merging tiles. Basicly if selected tile is same as it's below tile,
   // marge them together and create a new doubled tile.
   public void doMerge(Tile[][] tileMatrix){ 
      int nRows = tileMatrix.length-1, nCols = tileMatrix[0].length; // nRows = 19, nCols = 12 
      for(int col = 0; col < nCols; col++){ // col max = 11
         for(int row = 0; row < nRows; row++){ // row max == 18
            if(col == 0 && (tileMatrix[row][col] != null && tileMatrix[row+1][col] != null)){
               if(tileMatrix[row][col].number == tileMatrix[row+1][col].number){
                  tileMatrix[row+1][col] = null;
                  // New tile
                  tileMatrix[row][col] = new Tile(tileMatrix[row][col].number);
                  col=0;
               }
            }
            else if(tileMatrix[row][col] != null && tileMatrix[row+1][col] != null){
               if(tileMatrix[row][col].number == tileMatrix[row+1][col].number){
                  tileMatrix[row+1][col] = null;
                  // New tile
                  tileMatrix[row][col] = new Tile(tileMatrix[row][col].number);
                  col--;
               }
            }
         }
      }
   }

   // A method for checking the all the colums to find a gap between tiles. If gap is founded, the algorithm moves the upper tile to the bottom position.
   public void isGapped(Tile[][] tileMatrix){ 
      int nRows = tileMatrix.length, nCols = tileMatrix[0].length; // nRows = 19, nCols = 12 
         for(int col = 0; col < nCols; col++){ // col max = 11
            for(int row = 0; row < nRows-1; row++){ // row max = 17
               if(col == 0 && (tileMatrix[row+1][col]!= null && tileMatrix[row][col] == null)){
                  tileMatrix[row][col] = tileMatrix[row+1][col];
                     tileMatrix[row+1][col] = null;
                     col=0;
               }
               else if(tileMatrix[row+1][col]!= null && tileMatrix[row][col] == null){
                     tileMatrix[row][col] = tileMatrix[row+1][col];
                     tileMatrix[row+1][col] = null;
                     col--;
               }
            }
         }
   }

   // A method for checking the last row of the game grid, if all the columns of the last row is not empty, 
   // deletes the last row and moves the upper rows one position down. When the algorithm deletes the last row it also adds the deleted tiles number to the score.
   public void deleteRows(Tile[][] tileMatrix){
      int nCols = tileMatrix[0].length; // nRows = 19, nCols = 12
      if(tileMatrix[0][0] != null && tileMatrix[0][1] != null && tileMatrix[0][2] != null && tileMatrix[0][3] != null && 
      tileMatrix[0][4] != null && tileMatrix[0][5] != null && tileMatrix[0][6] != null && tileMatrix[0][7] != null && 
      tileMatrix[0][8] != null){
         for(int col = 0; col < nCols; col++){ // col start 0, max = 11
               score += tileMatrix[0][col].number;
               tileMatrix[0][col]=null;
               isGapped(tileMatrix);     
         }
      } 
   }

   // A method for getting the score.
   public String getScore(){
      return Integer.toString(score);
   }

   // A method that locks the tiles of the landed tetromino on the game grid while.
   // checking if the game is over due to having tiles above the topmost grid row.
   // The method returns true when the game is over and false otherwise.
   public boolean updateGrid(Tile[][] tilesToLock, Point blcPosition) {
      // necessary for the display method to stop displaying the tetromino
      currentTetromino = null;
      // lock the tiles of the current tetromino (tilesToLock) on the game grid
      int nRows = tilesToLock.length, nCols = tilesToLock[0].length;
      
      for (int col = 0; col < nCols; col++) {
         for (int row = 0; row < nRows; row++) {
            // place each tile onto the game grid
            if (tilesToLock[row][col] != null) {
               // compute the position of the tile on the game grid
               Point pos = new Point();
               pos.setX(blcPosition.getX() + col);
               pos.setY(blcPosition.getY() + (nRows - 1) - row);
               if (isInside(pos.getY(), pos.getX())){
                  tileMatrix[pos.getY()][pos.getX()] = tilesToLock[row][col];    
               }
               // the game is over if any placed tile is above the game grid
               else
                  gameOver = true;
            }
         }
      } 
      doMerge(tileMatrix);
      isGapped(tileMatrix);
      deleteRows(tileMatrix);
      return gameOver;
   }
}