import java.io.*;
import java.util.*;

/**
 * class that implements quick union using quick find algorithm
 */
public class UnionQF {
  private int[] arr;
  private int count;
  public UnionQF(int N) {
    arr = new int[N];
    count = N;
    for (int i = 0; i < N; ++i) {
      arr[i] = i;
    }
  }

  public void union(int p, int q) {
    int pID = find(p);
    int qID = find(q); 

    if (pID == qID) return;
    for (int i = 0; i < arr.length; ++i) {
      if (arr[i] == pID) arr[i] = qID;
    }
    count--;
  }

  public int find(int p) {
    return arr[p];
  }

  public boolean connected(int p, int q) {
    return (find(p) == find(q));
  }
  
  public int count() {
    return count;
  }


  public static void main(String[] args) {
    int N = StdIn.readInt();
    UnionQF qf = new UnionQF(N);
    while (!StdIn.isEmpty()) {
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if (qf.connected(p, q)) continue;
      qf.union(p, q);
      StdOut.println(p + " " + q);
    }
  }
}
