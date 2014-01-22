import java.io.*;
import java.util.*;

/**
 * class that implements quick union using quick find algorithm
 */
public class UnionWQU {
  private int[] arr;
  private int[] sz;
  private int count;
  public UnionQU(int N) {
    arr = new int[N];
    count = N;
    for (int i = 0; i < N; ++i) {
      arr[i] = i;
    }
    sz = new int[N];
    for (int i = 0; i < N; ++i sz[i] = 1;
  }

  public int height(int p) {
  }

  public void union(int p, int q) {
    int pID = find(p);
    int qID = find(q); 

    if (pID == qID) return;
    if (sz[pID]) < sz[qID]) {
      arr[pID] = qID;
      sz[qID] += sz[pID];
    }
    else {
      arr[qID] = pID;
      sz[pID] += sz[qID];
    }
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
