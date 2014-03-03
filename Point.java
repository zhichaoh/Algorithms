/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
      public int compare(Point p1, Point p2) {
        double slope1 = Point.this.slopeTo(p1);
        double slope2 = Point.this.slopeTo(p2);
        double diff = slope1 - slope2;
        if (diff < 0.0) return -1;
        if (diff > 0.0) return 1;
        else return 0;
      }
    };

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
      if (y == that.y) return +0.0;
      if (x == that.x) return Double.POSITIVE_INFINITY;
      if ((y == that.y) && (x == that.x)) return Double.NEGATIVE_INFINITY;
      return (double) (that.y - y) / (that.x - x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
      if (that.y < y) return 1;
      if (that.y > y) return -1;
      if (that.x < x) return 1;
      if (that.x > x) return -1;
      return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
      Point p1 = new Point(0, 0);
      Point p2 = new Point(100, 200);
      Point p3 = new Point(200, 200);
      Point p4 = new Point(200, 200);
      Point p5 = new Point(200, 300);
      Point[] pts = new Point[] {p1, p2, p3, p4, p5};
      java.util.Arrays.sort(pts, p1.SLOPE_ORDER);
      for(Point p: pts) {
        StdOut.println(p);
      }
      java.util.Arrays.sort(pts);
      StdOut.println("-----");
      for (Point p: pts) {
        StdOut.println(p);
      }
         
    }
}
