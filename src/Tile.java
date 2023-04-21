import java.awt.Color; // the color type used in StdDraw
import java.awt.Font; // the font type used in StdDraw
import java.util.Arrays;
import java.util.Random;

// A class used for modeling numbered tiles as in 2048
public class Tile {
   // Data fields: instance variables
   // --------------------------------------------------------------------------
   private final static Integer[] numbers = {2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};
   
   private final static Color[] colors = {
      new Color(255, 229, 204),   // 2
      new Color(255, 204, 153),   //4
      new Color(255, 178, 102),   //8
      new Color(255, 153, 51),    //16
      new Color(236, 92, 92),     //32
      new Color(238, 50, 50),     //64
      new Color(226, 226, 49),    //128
      new Color(191, 191, 20),    //256
      new Color(156, 156, 30),    //512
      new Color(124, 124, 43),    //1024
      new Color(144, 144, 66)};   //2048

   private int number; // the number on the tile
   private Color backgroundColor; // background (tile) color
   private Color foregroundColor = Color.BLACK; // foreground (number) color
   private Color boxColor; // box (boundary) color
   private Point bottomLeftCell = new Point();
   // Data fields: class variables
   // --------------------------------------------------------------------------
   // the value of the boundary thickness (for the boundaries around the tiles)
   private static double boundaryThickness = 0.004;
   // the font used for displaying the tile number
   private static Font font = new Font("Arial", Font.PLAIN, 14);

   // Methods
   // --------------------------------------------------------------------------
   // the default constructor that creates a tile
   public Tile() {
      Random rand = new Random();
      int i = rand.nextInt(2);
      // Number 2
      if(i == 0){
         number = 2;
         this.backgroundColor = colors[i];
         boxColor = new Color(0, 100, 200);
      }
      // Number 4
      else if (i == 1){
      number = 4;
      this.backgroundColor = colors[i];
      boxColor = new Color(0, 100, 200);
      }
      // Number 8
      else if (i == 2){
         number = 8;
         // set the colors of the tile
         this.backgroundColor = colors[i];
         boxColor = new Color(0, 100, 200);
      }
      // Number 16
      else if (i == 3){
         number = 16;
         // set the colors of the tile
         this.backgroundColor = colors[i];
         boxColor = new Color(0, 100, 200);
      }
      // Number 32
      else if (i == 4){
      number = 32;
      this.backgroundColor = colors[i];
      boxColor = new Color(0, 100, 200);
      }
      // Number 64
      else if (i == 5){
         number = 64;
         // set the colors of the tile
         this.backgroundColor = colors[i];
         boxColor = new Color(0, 100, 200);
      }
      // Number 128
      else if (i == 6){
         number = 128;
         // set the colors of the tile
         this.backgroundColor = colors[i];
         boxColor = new Color(0, 100, 200);
      }
      // Number 256
      else if (i == 7){
         number = 256;
         // set the colors of the tile
         this.backgroundColor = colors[i];
         boxColor = new Color(0, 100, 200);
      }
      // Number 512
      else if (i == 8){
         number = 512;
         // set the colors of the tile
         this.backgroundColor = colors[i];
         boxColor = new Color(0, 100, 200);
      }
      // Number 1024
      else if (i == 9){
         number = 1024;
         // set the colors of the tile
         this.backgroundColor = colors[i];
         boxColor = new Color(0, 100, 200);
      }
      else{
         number = 2048;
      // set the colors of the tile
      this.backgroundColor = colors[i];
      boxColor = new Color(0, 100, 200);
      }
   }
   public Integer getNumber(){
      return this.number;
   }
   public void resetNumber(){
      this.number = 0;
   }
   public void incNumber(int value){
      this.number =+ value;

      if(this.number > 2048)
         this.number = 2048;

      int i = Arrays.asList(numbers).indexOf(this.number); 
      this.backgroundColor = colors[i];  
   }

    
   @Override
   public String toString() {
       return Integer.toString(number);
   }
   // a method for drawing the tile
   public void draw(Point position, double... sideLength) {
      // the default value for the side length (sLength) is 1
      double sLength;
      if (sideLength.length == 0) // sideLength is a variable-length parameter
         sLength = 1;
      else
         sLength = sideLength[0];
      // draw the tile as a filled square
      StdDraw.setPenColor(backgroundColor);
      StdDraw.filledSquare(position.getX(), position.getY(), sLength / 2);
      // draw the bounding box around the tile as a square
      StdDraw.setPenColor(boxColor);
      StdDraw.setPenRadius(boundaryThickness);
      StdDraw.square(position.getX(), position.getY(), sLength / 2);
      StdDraw.setPenRadius(); // reset the pen radius to its default value
      // draw the number on the tile
      StdDraw.setPenColor(foregroundColor);
      StdDraw.setFont(font);
      StdDraw.text(position.getX(), position.getY(), "" + number);
   }
   
   public Point getPosition(){
      Point position = new Point();

      position.setX(bottomLeftCell.getX());
      position.setY(bottomLeftCell.getY());
      return position;
   }
}