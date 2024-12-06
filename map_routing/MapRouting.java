import edu.princeton.cs.algs4.*;

class MapRouting {
    public static void main(String[] args) {
        var in = new In(args[0]);

        int m = in.readInt();
        int n = in.readInt();

        var map = new Map(n);
        for (int i = 0; i < m; i++) {
            map.addVertex(in.readInt(), in.readInt(), in.readInt());
        }
        for (int i = 0; i < n; i++) {
            map.addEdge(in.readInt(), in.readInt());
        }
    }
}
