package core;

public class FLARectangle2D extends FLAAnnotation2D {

     private double x;
     private double y;
     private double width;
     private double height;
    
     public FLARectangle2D(double x, double y, double width, double height) {
         this.x = x;
         this.y = y;
         this.width = width;
         this.height = height;
     }
     public double getX() {
         return x;
     }
     public double getY() {
          return y;
     }
    
        public double getWidth() {
            return width;
        }
        public double getHeight() {
            return height;
        }
        public double getArea() {
            return width * height;
        }
        public boolean contains(double pointX, double pointY) {
            return pointX >= x && pointX <= x + width && pointY >= y && pointY <= height;
        }
        public String toString() {
            return "Rectangle2D [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
        }
        public boolean equals(Object obj) {
         if(this == obj) {
             return true;
         }
         if(obj == null || getClass() != obj.getClass()) {
             return false;
         }
        FLARectangle2D other = (FLARectangle2D) obj;
         return Double.compare(other.x, x) == 0 && Double.compare(other.y, y) == 0 &&
                 Double.compare(other.width, width) == 0 && Double.compare(other.height, height) == 0;
    }
    
    }
    
    

