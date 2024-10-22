public class Percolation {
    private final int n;
    private final int virtualTop;
    private final int virtualBottom;
    private int openSites;
    private boolean[][] grid;
    private UnionFind uf;
    

    public Percolation(int n) {
        this.n = n;
        this.virtualTop = n * n;
        this.virtualBottom = n * n + 1;
        this.openSites = 0;
        this.grid = new boolean[n][n];
        this.uf = new UnionFind(n * n + 2);
    }

    public void open(int i, int j) {
        if (!inRange(i, j)) {
            throw new IllegalArgumentException("i and j must be in range");
        }
        if (isOpen(i, j)) {
            return;
        }
        grid[i][j] = true;
        openSites++;
        
        tryUnion(i, j, i - 1, j);
        tryUnion(i, j, i + 1, j);
        tryUnion(i, j, i, j - 1);
        tryUnion(i, j, i, j + 1);

        if (i == 0) {
            uf.union(index(i, j), virtualTop);
        }
        if (i == n - 1) {
            uf.union(index(i, j), virtualBottom);
        }
    }

    public boolean isOpen(int i, int j) {
        if (!inRange(i, j)) {
            throw new IllegalArgumentException("i and j must be in range");
        }
        return grid[i][j];
    }

    public boolean isFull(int i, int j) {
        if (!inRange(i, j)) {
            throw new IllegalArgumentException("i and j must be in range");
        }
        return uf.find(index(i, j)) == uf.find(virtualTop);
    }

    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    public int openSites() {
        return openSites;
    }
    
    private void tryUnion(int i1, int j1, int i2, int j2) {
        if (!inRange(i1, j1) || !inRange(i2, j2)) {
            return;
        }
        if (isOpen(i1, j1) && isOpen(i2, j2)) {
            uf.union(index(i1, j1), index(i2, j2));
        }
    }

    private int index(int i, int j) {
        return i * n + j;
    }

    private boolean inRange(int i, int j) {
        return 0 <= i && i < n && 0 <= j && j < n;
    }
}
