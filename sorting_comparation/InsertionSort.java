import edu.princeton.cs.algs4.*;

public class InsertionSort {
    private InsertionSort() {
    }

    public static void sort(int[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                exch(a, j, j - 1);
            }
        }
    }

    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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
        //show(a);
    }
}

