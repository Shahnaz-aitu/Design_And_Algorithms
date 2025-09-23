package algo;

import algo.metrics.Metrics;
import algo.sort.MergeSort;
import algo.sort.QuickSort;
import algo.select.DeterministicSelect;
import algo.geometry.ClosestPair;
import algo.geometry.ClosestPair.Point;
import algo.util.CsvWriter;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try (CsvWriter csv = new CsvWriter("results.csv")) {
            csv.writeRow("algorithm", "n", "comparisons", "depth", "allocations", "timeMillis");

            int[] sizes = {100, 500, 1000, 5000, 10000}; // размеры входа
            Random rnd = new Random();

            for (int n : sizes) {
                int[] arr = rnd.ints(n, 0, 100000).toArray();
                Metrics m = new Metrics();

                // MergeSort
                int[] copy1 = Arrays.copyOf(arr, arr.length);
                long t1 = System.currentTimeMillis();
                MergeSort.sort(copy1, m);
                long t2 = System.currentTimeMillis();
                csv.writeRow("MergeSort", n, m.comparisons, m.maxDepth, m.allocations, (t2 - t1));

                // QuickSort
                m.reset();
                int[] copy2 = Arrays.copyOf(arr, arr.length);
                t1 = System.currentTimeMillis();
                QuickSort.sort(copy2, m);
                t2 = System.currentTimeMillis();
                csv.writeRow("QuickSort", n, m.comparisons, m.maxDepth, m.allocations, (t2 - t1));

                // DeterministicSelect (ищем k-й элемент)
                m.reset();
                int[] copy3 = Arrays.copyOf(arr, arr.length);
                int k = n / 2;
                t1 = System.currentTimeMillis();
                int kth = DeterministicSelect.select(copy3, k, m);
                t2 = System.currentTimeMillis();
                csv.writeRow("DeterministicSelect", n, m.comparisons, m.maxDepth, m.allocations, (t2 - t1));

                // Closest Pair
                m.reset();
                Point[] pts = new Point[n];
                for (int i = 0; i < n; i++) {
                    pts[i] = new Point(rnd.nextInt(100000), rnd.nextInt(100000));
                }
                t1 = System.currentTimeMillis();
                double dist = ClosestPair.closestPair(pts, m);
                t2 = System.currentTimeMillis();
                csv.writeRow("ClosestPair", n, m.comparisons, m.maxDepth, m.allocations, (t2 - t1));
            }

            System.out.println("✅ Results written to results.csv");
        }
    }
}
