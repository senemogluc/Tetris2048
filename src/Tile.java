import java.awt.Color; // the color type used in StdDraw
import java.awt.Font; // the font type used in StdDraw
import java.util.Random;

// A class used for modeling numbered tiles as in 2048
public class Tile {
   // Data fields: instance variables
   // --------------------------------------------------------------------------   
   // Each element corresponds to a number.
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

   public int number; // the number on the tile
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
   // The constructor that creates a tile for merging tiles. It creates a tile starting from 2 to 2048 with corresponding color values.
   public Tile(int value){
      int i = value*2;
      if(i==2){
         this.number = 2;
         this.backgroundColor = colors[0];
         boxColor = new Color(0, 100, 200);
      }
      else if (i == 4){
         this.number = 4;
         this.backgroundColor = colors[1];
         boxColor = new Color(0, 100, 200);
      }
      else if(i == 8){
         this.number = 8;
         this.backgroundColor = colors[2];
         boxColor = new Color(0, 100, 200);
      }
      else if(i == 16){
         this.number = 16;
         this.backgroundColor = colors[3];
         boxColor = new Color(0, 100, 200);
      }
      else if(i == 32){
         this.number = 32;
         this.backgroundColor = colors[4];
         boxColor = new Color(0, 100, 200);
      }
      else if(i == 64){
         this.number = 64;
         this.backgroundColor = colors[5];
         boxColor = new Color(0, 100, 200);
      }
      else if(i == 128){
         this.number = 128;
         this.backgroundColor = colors[6];
         boxColor = new Color(0, 100, 200);
      }
      else if(i == 256){
         this.number = 256;
         this.backgroundColor = colors[7];
         boxColor = new Color(0, 100, 200);
      }
      else if(i == 512){
         this.number = 512;
         this.backgroundColor = colors[8];
         boxColor = new Color(0, 100, 200);
      }
      else if(i == 1024){
         this.number = 1024;
         this.backgroundColor = colors[9];
         boxColor = new Color(0, 100, 200);
      }
      else if(i == 2048){
         this.number = 2048;
         this.backgroundColor = colors[10];
         boxColor = new Color(0, 100, 200);
      }
   }
   // The constructor that creates a tile for tetrominos. It only creates number 2 and number 4.
   public Tile() {
      Random rand = new Random();
      int i = rand.nextInt(2);
      // Number 2
      if(i == 0){
         this.number = 2;
         this.backgroundColor = colors[i];
         boxColor = new Color(0, 100, 200);
      }
      // Number 4
      else if (i == 1){
      this.number = 4;
      this.backgroundColor = colors[i];
      boxColor = new Color(0, 100, 200);
      }
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