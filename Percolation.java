public class Percolation {
  private int sizeOfRowCol;
  private WeightedQuickUnionUF wqu;
  private boolean[] status;

  public Percolation(int N) {
    sizeOfRowCol = N;
    int numOfCells = N*N;
    wqu = new WeightedQuickUnionUF(numOfCells + 2);
    status = new boolean[numOfCells+2];
    for (int i = 0; i < numOfCells; ++i) {
      status[i] = false;
    }
    status[N*N] = true;
    status[N*N+1] = true;
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
        wqu.union(cellAbove, getCellIndex(i, j));
      }
    }
    else {
      wqu.union(getCellIndex(i, j), sizeOfRowCol * sizeOfRowCol);
    }
  }

  private void connectBelow(int i, int j) {
    if (i != sizeOfRowCol) {
      int cellBelow = getCellIndex(i+1, j);
      if (isOpen(i+1, j)) {
        wqu.union(cellBelow, getCellIndex(i, j));
      }
    }
    else {
      if (! isFull(i, j)) {
        wqu.union(getCellIndex(i, j), (sizeOfRowCol * sizeOfRowCol)+1);
      }
    }
  }

  private void connectLeft(int i, int j) {
    if (j != 1) {
      int cellLeft = getCellIndex(i, j-1);
      if (isOpen(i, j-1)) {
        wqu.union(cellLeft, getCellIndex(i, j));
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
        wqu.union(cellRight, getCellIndex(i, j));
      }
    }
    else {
      // nothing to connect here
    }
  }

  public void open(int i, int j) {
    validateCellIndex(i, j);
    if (isOpen(i, j)) return;
    status[getCellIndex(i, j)] = true;

    connectAbove(i, j);
    connectBelow(i, j);
    connectLeft(i, j);
    connectRight(i, j);
  }

  public boolean isOpen(int i, int j) {
    validateCellIndex(i, j);
    return status[getCellIndex(i, j)];
  }

  public boolean isFull(int i, int j) {
    validateCellIndex(i, j);
    if (i == 1 && isOpen(i, j)) { return true; }
    return wqu.connected(sizeOfRowCol * sizeOfRowCol, getCellIndex(i, j));
  }

  public boolean percolates() {
    // check if the 2 virtual sites are connected
    for (int col = 1; col <= sizeOfRowCol; ++col) {
      if (isOpen(sizeOfRowCol, col) && isFull(sizeofRowCol, col)) {
        return true;
      }
    return wqu.connected(sizeOfRowCol*sizeOfRowCol, sizeOfRowCol*sizeOfRowCol + 1);
  }

  public static void main(String[] args) {
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
  }

}
