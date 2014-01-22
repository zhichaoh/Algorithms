import java.io.*;
import java.util.*;

/**
 * class that implements quick union using quick find algorithm
 */
public class UnionQU {
  private int[] arr;
  private int count;
  public UnionQU(int N) {
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
    arr[pID] = qID;
    count--;
  }

  public int find(int p) {
    while (p != arr[p]) p = arr[p];
    return p;
  }

  public boolean connected(int p, int q) {
    return (find(p) == find(q));
  }
  
  public int count() {
    return count;
  }


  public static void main(String[] args) {
    int N = StdIn.readInt();
    UnionQU qu = new UnionQU(N);
    while (!StdIn.isEmpty()) {
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if (qu.connected(p, q)) continue;
      qu.union(p, q);
      StdOut.println(p + " " + q);
    }
  }
}
