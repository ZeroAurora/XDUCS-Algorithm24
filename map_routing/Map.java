import edu.princeton.cs.algs4.*;

public class Map {
    private class Coordination {
        private int x;
        private int y;
    }

    public final int V;
    public EdgeWeightedGraph G;
    public Coordination[] C;

    public Map(int V) {
        this.V = V;
        G = new EdgeWeightedGraph(V);
        C = new Coordination[V];
    }

    public void addVertex(int n, int x, int y) {
        C[n] = new Coordination();
        C[n].x = x;
        C[n].y = y;
    }

    public void addEdge(int v, int w, double weight) {
        G.addEdge(new Edge(v, w, weight));
    }

    public void addEdge(int v, int w) {
        addEdge(v, w, dist(v, w));
    }

    public double dist(int v, int w) {
        return Utils.dist(C[v].x, C[v].y, C[w].x, C[w].y);
    }

    public void draw() {
        StdDraw.clear();
        for (int i = 0; i < V; i++) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(C[i].x, C[i].y, 0.01);
            StdDraw.setPenColor(StdDraw.BLACK);
        }
        for (Edge e : G.edges()) {
            StdDraw.setPenColor(StdDraw.BLUE);
            int v = e.either();
            int w = e.other(v);
            double vx = C[v].x / 10000;
            double vy = C[v].y / 10000;
            double wx = C[w].x / 10000;
            double wy = C[w].y / 10000;
            StdDraw.line(vx, vy, wx, wy);
        }
        StdDraw.show();
    }
}
