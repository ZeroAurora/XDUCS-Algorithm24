import edu.princeton.cs.algs4.*;

public class ThreeWayQuickSort {
    private ThreeWayQuickSort() {
    }

    public static void sort(int[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo, gt = hi;
        int i = lo + 1;
        int v = a[lo];
        while (i <= gt) {
            int cmp = a[i] - v;
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static void exch(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        int[] a = StdIn.readAllInts();

        long start = System.currentTimeMillis();
        sort(a);
        long end = System.currentTimeMillis();
        long memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        StdOut.println("Elapsed time: " + (end - start) + " ms");
        StdOut.println("Memory used: " + memory / 1024 + " KB");
        // show(a);
    }
}
