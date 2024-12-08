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


    public void draw(String filename) {
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(1000, 500);
        StdDraw.setXscale(0, 10000);
        StdDraw.setYscale(0, 5000);

        System.out.println("Drawing vertices...");
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.001);
        for (int i = 0; i < V; i++) {
            StdDraw.point(C[i].x, C[i].y);
        }

        System.out.println("Drawing edges...");
        StdDraw.setPenColor(StdDraw.BLUE);
        for (Edge e : G.edges()) {
            int v = e.either();
            int w = e.other(v);
            StdDraw.line(C[v].x, C[v].y, C[w].x, C[w].y);
        }

        StdDraw.show();
        StdDraw.save(filename);
        StdDraw.close();
    }

    public void draw(Iterable<Edge> edges, String filename) {
        if (edges == null) {
            System.out.println("No edges to draw");
            return;
        }

        StdDraw.clear();
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(1000, 500);
        StdDraw.setXscale(0, 10000);
        StdDraw.setYscale(0, 5000);

        System.out.println("Drawing other vertices...");
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.001);
        for (int i = 0; i < V; i++) {
            StdDraw.point(C[i].x, C[i].y);
        }

        System.out.println("Drawing other edges...");
        StdDraw.setPenColor(StdDraw.GRAY);
        for (Edge e : G.edges()) {
            int v = e.either();
            int w = e.other(v);
            StdDraw.line(C[v].x, C[v].y, C[w].x, C[w].y);
        }

        System.out.println("Drawing shortest path...");
        StdDraw.setPenColor(StdDraw.RED);
        for (Edge e : edges) {
            int v = e.either();
            int w = e.other(v);
            StdDraw.line(C[v].x, C[v].y, C[w].x, C[w].y);
        }

        StdDraw.show();
        StdDraw.save(filename);
        StdDraw.close();
    }
}
