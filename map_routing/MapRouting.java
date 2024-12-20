import edu.princeton.cs.algs4.*;

class MapRouting {
    public static void main(String[] args) {
        var in = new In(args[0]);

        int m = in.readInt();
        int n = in.readInt();

        var map = new Map(m);
        System.out.println("Map: " + m + " vertices, " + n + " edges");
        System.out.println("Adding vertices...");
        for (int i = 0; i < m; i++) {
            map.addVertex(in.readInt(), in.readInt(), in.readInt());
        }
        System.out.println("Adding edges...");
        for (int i = 0; i < n; i++) {
            map.addEdge(in.readInt(), in.readInt());
        }
        map.draw("map.png");

        var qin = new In("query.txt");
        while (!qin.isEmpty()) {
            var s = qin.readInt();
            var t = qin.readInt();
            var aStar = new AStar(map, s, t);
            var path = aStar.shortestPath();
            if (path == null) {
                System.out.println("No path");
            } else {
                System.out.println("Path found");
            }
            // String filename = new StringBuilder()
            //         .append("path-")
            //         .append(s)
            //         .append("-")
            //         .append(t)
            //         .append(".png")
            //         .toString();
            // map.draw(path, filename);
        }
    }
}
