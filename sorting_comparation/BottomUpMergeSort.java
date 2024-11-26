import edu.princeton.cs.algs4.*;

public class BottomUpMergeSort {
    private BottomUpMergeSort() {
    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void sort(int[] a) {
        int N = a.length;
        int[] aux = new int[N];
        for (int sz = 1; sz < N; sz *= 2) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                int mid = lo + sz - 1;
                int hi = Math.min(lo + sz + sz - 1, N - 1);
                merge(a, aux, lo, mid, hi);
            }
        }
    }

    public static void show(int[] a) {
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
