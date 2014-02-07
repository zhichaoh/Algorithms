public class PercolationStats {
  private double[] tries;
  public PercolationStats(int N, int T) {
    if (N < 1 || T < 2) throw new IllegalArgumentException();
    tries = new double[T];
    for (int i = 0; i < tries.length; ++i) {
      int totalOpened = 0;
      Percolation percolation = new Percolation(N);
      while (! percolation.percolates()) {
        int row = StdRandom.uniform(1, N+1);
        int col = StdRandom.uniform(1, N+1);
        if (! percolation.isOpen(row, col)) {
          percolation.open(row, col);
          totalOpened++;
        }
      }
      tries[i] = (double) totalOpened / (N*N);
    }

  }

  public double mean() {
    return StdStats.mean(tries);
  }

  public double stddev() {
    return StdStats.stddev(tries);
  }

  public double confidenceLo() {
    return mean() - (1.96 * stddev())/Math.sqrt(tries.length);
  }

  public double confidenceHi() {
    return mean() + (1.96 * stddev())/Math.sqrt(tries.length);
  }

  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);
    PercolationStats ps = new PercolationStats(N, T);
    StdOut.printf("%s=%f\n", "mean", ps.mean());
    StdOut.printf("%s=%f\n", "stddev", ps.stddev());
    StdOut.printf("%s=%f,%f\n", "95% confidence interval", ps.confidenceLo(),
      ps.confidenceHi());
  }
}

