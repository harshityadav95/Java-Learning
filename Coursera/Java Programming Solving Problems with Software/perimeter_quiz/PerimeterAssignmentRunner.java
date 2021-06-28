import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count =0;
        for (Point currPt: s.getPoints())
        {

            count= count +1; 

            //System.out.println("The number of points in this shape is+" + count);

        }
       // Put code here
        return count;
    }

    public double getAverageLength(Shape s) {
        double length = getPerimeter(s);
        double numsides = (double) getNumPoints(s);
        double avgLength = length/numsides;
        return avgLength;
      
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double LargestSide = 0.0;
        double prevDist = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) 
        {
            double currDist = prevPt.distance(currPt);

            if (currDist > prevDist){

                LargestSide = currDist;

            }

            //LargestSide = currDist;

        }

        

        return LargestSide;
    }

      public double getLargestX(Shape s) 
      {	    	      
          Point prevPt = s.getLastPoint();    	      
          double largestX = 0.0;    	    	      
          for (Point currPt : s.getPoints()) 
          {    		       
              double prevX = prevPt.getX();    	       
              if (prevX > currPt.getX())
              {    			         
                  largestX = prevX;    		        
              }    
              else 
              {
                   largestX = currPt.getX();              
                } 
                prevPt = currPt;
        }
          return largestX;              
      }

    public double getLargestPerimeterMultipleFiles()
    {
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
     Shape shp = new Shape(fr);
     double curPer = getPerimeter(shp);
     if (curPer > largestPerimeter) {
         largestPerimeter = curPer;
     }       
 }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter()
    {
            double largestPerimeter = 0.0;
            String fileName = null;
            DirectoryResource dr = new DirectoryResource();
            for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape shp = new Shape(fr);
            double curPer = getPerimeter(shp);
            if (curPer > largestPerimeter) {
            largestPerimeter = curPer;
            fileName = f.getName();
            }       
    }    
        return fileName;   
    }

    public void testavglength () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getAverageLength(s);
        System.out.println("getAverageLength = " + length);
    }
        public void testLongestSide () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getLargestSide(s);
        System.out.println("getlongeside = " + length);
    }
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
    }
    
    public void testPerimeterMultipleFiles() {
            double number = getLargestPerimeterMultipleFiles();
            System.out.println("The largest perimeter is: " + number);
    
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
         String FileWithLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("File with the largest perimeter is: " + FileWithLargestPerimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testLongestSide();
    }
}
