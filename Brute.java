public class Brute {
  private static void sortByPoint(Point[] pts) {
    StdRandom.shuffle(pts);
    quick3Sort(pts, 0, pts.length-1);
  }

  private static void exch(Point[] pts, int a, int b) {
    Point temp = pts[a];
    pts[a] = pts[b];
    pts[b] = temp;
  }

  private static void quick3Sort(Point[] pts, int lo, int hi) {
    if (hi <= lo) return;
    int lt = lo;
    int i = lo+1;
    int gt = hi;
    Point p = pts[lo];
    while (i <= gt) {
      int cmp = pts[i].compareTo(p);
      if (cmp < 0) {exch(pts, lt, i); lt++; i++;}
      else if (cmp > 0) {exch(pts, i, gt); gt--;}
      else i++;
    }
    quick3Sort(pts, lo, lt-1);
    quick3Sort(pts, gt+1, hi);
  }

  public static void main(String[] args) throws Exception{
    In in = new In(args[0]);
    int numOfPoints = Integer.parseInt(in.readLine());
    Point[] pts = new Point[numOfPoints];
    for (int i = 0; i < numOfPoints; ++i) {
      int x = in.readInt();
      int y = in.readInt();
      pts[i] = new Point(x, y);
    }

    sortByPoint(pts);
    for (Point p: pts) {
      StdOut.println(p);
    }

    StdDraw.setXscale(0.0, 32768.0);
    StdDraw.setYscale(0.0, 32768.0);

    for (int i = 0; i < pts.length-3; ++i) {
      for (int j = i+1; j < pts.length-2; ++j) {
        for (int k = j+1; k < pts.length-1; ++k) {
          for (int l = k+1; l < pts.length; ++l) {
            double slopeIJ = pts[i].slopeTo(pts[j]);
            double slopeIK = pts[i].slopeTo(pts[k]);
            double slopeIL = pts[i].slopeTo(pts[l]);
            if (slopeIJ == slopeIK && slopeIJ == slopeIL) {
              StdOut.printf("%s->%s->%s->%s\n", pts[i], pts[j],
                  pts[k], pts[l]);
            }
          }
        }
      }
    }
  }
}
