import edu.princeton.cs.algs4.*;

public class AStar {
    private Map M;

    private double[] distTo;
    private Edge[] edgeTo;
    private IndexMinPQ<Double> pq;

    @SuppressWarnings("unused")
    private int s;
    private int t;

    public AStar(Map M, int s, int t) {
        this.M = M;
        this.s = s;
        this.t = t;

        distTo = new double[M.V];
        edgeTo = new Edge[M.V];
        pq = new IndexMinPQ<>(M.V);

        for (int i = 0; i < M.V; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
            edgeTo[i] = null;
        }

        distTo[s] = 0;

        // recursively relax edges, until path is found
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge E : M.G.adj(v)) {
                relax(E, v);
                if (distTo[t] != Double.POSITIVE_INFINITY) {
                    break;
                }
            }
        }
    }

    public Iterable<Edge> shortestPath() {
        if (distTo[t] == Double.POSITIVE_INFINITY) {
            return null;
        }

        var path = new Stack<Edge>();
        int x = t;
        for (Edge e = edgeTo[t]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;   
    }
    
    private void relax(Edge e, int v) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight() + heuristic(v, w)) {
            distTo[w] = distTo[v] + e.weight() + heuristic(v, w);
            edgeTo[w] = e;
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }

    // when path v->w makes the euclidean distance to terminal shorter,
    // this function returns a negative value, which makes distTo[w] smaller.
    // effectively, the function makes the search more heuristic.
    private double heuristic(int v, int w) {
        return M.dist(w, t) - M.dist(v, t);
    }
}
