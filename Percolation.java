public class Percolation {
  private int sizeOfRowCol;
  private WeightedQuickUnionUF pqu;
  private byte[] status;
  private static final int CLOSE = 0;
  private static final int OPEN = 1;
  private static final int CONNECTED_TO_BOTTOM = 2;

  public Percolation(int N) {
    sizeOfRowCol = N;
    int numOfCells = N*N;
    pqu = new WeightedQuickUnionUF(numOfCells + 1);
    status = new byte[numOfCells+1];
    for (int i = 0; i < numOfCells; ++i) {
      status[i] = CLOSE;
    }
    // bottom row is connected by default
    for (int i = 1; i <= N; ++i) {
      status[getCellIndex(N, i)] = CONNECTED_TO_BOTTOM;
    }
    
    // virtual site is OPEN
    status[N*N] = OPEN;
  }

  private void validateCellIndex(int i, int j) {
    if (i < 1 || j < 1 || i > sizeOfRowCol || j > sizeOfRowCol)
      throw new IndexOutOfBoundsException();
  }

  private int getCellIndex(int i, int j) {
    return (sizeOfRowCol * (i-1)) + j - 1;
  }

  private void connectAbove(int i, int j) {
    if (i != 1) {
      int cellAbove = getCellIndex(i-1, j);
      if (isOpen(i-1, j)) {
        union(cellAbove, getCellIndex(i, j));
      }
    }
    else {
      union(getCellIndex(i, j), sizeOfRowCol * sizeOfRowCol);
    }
  }

  private void connectBelow(int i, int j) {
    if (i != sizeOfRowCol) {
      int cellBelow = getCellIndex(i+1, j);
      if (isOpen(i+1, j)) {
        union(cellBelow, getCellIndex(i, j));
      }
    }
  }

  private void connectLeft(int i, int j) {
    if (j != 1) {
      int cellLeft = getCellIndex(i, j-1);
      if (isOpen(i, j-1)) {
        union(cellLeft, getCellIndex(i, j));
      }
    }
    else {
      // nothing to connect here
    }
  }

  private void connectRight(int i, int j) {
    if (j != sizeOfRowCol) {
      int cellRight = getCellIndex(i,  j+1);
      if (isOpen(i, j+1)) {
        union(cellRight, getCellIndex(i, j));
      }
    }
    else {
      // nothing to connect here
    }
  }
  
  private void union(int cell1, int cell2) {
    int cell1Root = pqu.find(cell1);
    int cell2Root = pqu.find(cell2);

    if (((status[cell1Root] & CONNECTED_TO_BOTTOM) == CONNECTED_TO_BOTTOM)
        || ((status[cell2Root] & CONNECTED_TO_BOTTOM) == CONNECTED_TO_BOTTOM)) {
      status[cell1Root] |= CONNECTED_TO_BOTTOM;
      status[cell2Root] |= CONNECTED_TO_BOTTOM;
    }
    pqu.union(cell1, cell2);
  }

  public void open(int i, int j) {
    validateCellIndex(i, j);
    if (isOpen(i, j)) return;
    status[getCellIndex(i, j)] |= OPEN;

    connectAbove(i, j);
    connectBelow(i, j);
    connectLeft(i, j);
    connectRight(i, j);
  }

  

  public boolean isOpen(int i, int j) {
    validateCellIndex(i, j);
    return (status[getCellIndex(i, j)] & OPEN) == OPEN;
  }

  public boolean isFull(int i, int j) {
    validateCellIndex(i, j);
    return isOpen(i, j) && pqu.connected(sizeOfRowCol * sizeOfRowCol, getCellIndex(i, j));
  }

  public boolean percolates() {
    int rootOfVirtualSite = pqu.find(sizeOfRowCol * sizeOfRowCol);
    return (status[rootOfVirtualSite] & CONNECTED_TO_BOTTOM) == CONNECTED_TO_BOTTOM;
  }

  public static void main(String[] args) {
    /*
    Percolation percolation = new Percolation(7);
    percolation.open(1,5);
    StdOut.println(percolation.isFull(1, 1));
    percolation.open(2,5);
    percolation.open(3,5);
    percolation.open(4,5);
    percolation.open(5,5);
    percolation.open(6,5);
    percolation.open(7,5);
    percolation.open(5,3);
    percolation.open(6,3);
    percolation.open(6,4);
    percolation.open(6,1);
    percolation.open(7,1);
    StdOut.println(percolation.isFull(6,1));
    StdOut.println(percolation.isFull(7,1));
    StdOut.println(percolation.isFull(5,3));
    StdOut.println(percolation.percolates());
    percolation.open(3, 3);
    percolation.open(2, 3);
    percolation.open(1, 3);
    percolation.open(3, 1);
    StdOut.println(percolation.isFull(3,1));
    StdOut.println(percolation.percolates());
    */
  }

}
