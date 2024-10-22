import edu.princeton.cs.algs4.*;

public class PercolationStats {
    private final double[] stats;
    private final int t;
    @SuppressWarnings("unused")
    private final int n;

    public PercolationStats(int n, int t) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        if (t <= 0) {
            throw new IllegalArgumentException("t must be greater than 0");
        }
        this.n = n;
        this.t = t;
        stats = new double[t];
        for (int i = 0; i < t; i++) {
            var percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int a = StdRandom.uniformInt(n);
                int b = StdRandom.uniformInt(n);
                percolation.open(a, b);
            }
            stats[i] = (double) percolation.openSites() / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(stats);
    }

    public double stddev() {
        return StdStats.stddev(stats);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(t);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(t);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        long start = System.currentTimeMillis();
        var stats = new PercolationStats(n, t);
        long end = System.currentTimeMillis();
        StdOut.println("Elapsed time: " + (end - start) + " ms");

        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println(
            "95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]"
        );
    }
}
