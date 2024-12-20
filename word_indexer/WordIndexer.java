import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import edu.princeton.cs.algs4.*;

public class WordIndexer {
    private HashMap<String, List<Integer>> hm;

    public WordIndexer(String filename) {
        hm = new HashMap<>();
        var in = new In(filename);
        int index = 1;
        while (!in.isEmpty()) {
            String word = in.readString();
            if (!hm.containsKey(word)) {
                hm.put(word, new ArrayList<>());
            }
            hm.get(word).add(index);
            index++;
        }
        in.close();
    }

    public List<Integer> get(String word) {
        List<Integer> indexes = hm.get(word);
        return indexes == null ? new ArrayList<>() : indexes;
    }

    public List<Integer> index(String phrase) {
        var words = phrase.split(" ");
        var occurrences = new ArrayList<Integer>();
        for (Integer first : get(words[0])) {
            if (search(words, first, 1) != -1) {
                occurrences.add(first);
            }
        }
        return occurrences;
    }

    private int search(String[] words, int first, int index) {
        if (index == words.length) {
            return first;
        }
        // after the constructor, we can assume that get(words[index]) is sorted in
        // ascending order
        // this allows us to do binary search
        if (Collections.binarySearch(get(words[index]), first + index) >= 0) {
            return search(words, first, index + 1);
        }
        return -1;
    }

    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        String corpus = args[0];
        var idxer = new WordIndexer(corpus);
        var end = System.currentTimeMillis();
        StdOut.println("Index time taken: " + (end - start) + " ms");

        System.gc();
        long usedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        StdOut.println("Memory used: " + usedMem / 1024 + " KB");

        start = System.currentTimeMillis();
        String query = args[1];
        var qin = new In(query);
        while (!qin.isEmpty()) {
            String phrase = qin.readLine();
            var occurrences = idxer.index(phrase);

            StringBuilder sb = new StringBuilder()
                    .append(phrase)
                    .append(" ")
                    .append(occurrences.size());
            if (occurrences.size() > 0) {
                sb.append(" (first: ")
                        .append(occurrences.get(0))
                        .append(")");
            }
            StdOut.println(sb);
        }
        end = System.currentTimeMillis();
        StdOut.println("Query time taken: " + (end - start) + " ms");
    }
}