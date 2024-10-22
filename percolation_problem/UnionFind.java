public class UnionFind {
    private int[] parent;
    private int[] rank;
    private int count;
    
    public UnionFind(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        parent = new int[n];
        rank = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // path compression
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        
        // make sure to connect the smaller ranked tree to the larger one
        if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }

    public int count() {
        return count;
    }

    private void validate(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of range");
        }
    }
}
