import algo.metrics.Metrics;
import algo.sort.MergeSort;
import algo.sort.QuickSort;
import algo.select.DeterministicSelect;
import algo.geometry.ClosestPair;
import algo.geometry.ClosestPair.Point;
import util.CsvWriter;

import java.util.*;
import java.util.Random;   // 👈 добавил это

public class Main {
    public static void main(String[] args) throws Exception {
        // Алгоритмы для тестов
        String[] algos = {"mergesort", "quicksort", "select", "closest"};
        int[] ns = {100, 500, 1000, 5000}; // размеры входа
        int trials = 3; // количество повторов

        try (CsvWriter writer = new CsvWriter("results.csv")) {
            writer.writeHeader("algo,n,time,comparisons,allocations,depth");

            for (String algo : algos) {
                for (int n : ns) {
                    for (int t = 0; t < trials; t++) {
                        Metrics m = new Metrics();
                        long start = System.nanoTime();

                        switch (algo) {
                            case "mergesort" -> {
                                int[] arr = randomArray(n);
                                MergeSort.sort(arr, m);
                            }
                            case "quicksort" -> {
                                int[] arr = randomArray(n);
                                QuickSort.sort(arr, m);
                            }
                            case "select" -> {
                                int[] arr = randomArray(n);
                                int k = new Random().nextInt(n);
                                DeterministicSelect.select(arr, k, m);
                            }
                            case "closest" -> {
                                Point[] pts = randomPoints(n);
                                ClosestPair.closestPair(pts, m);
                            }
                        }

                        long end = System.nanoTime();
                        long timeMs = (end - start) / 1_000_000;

                        writer.writeRow(algo, n, timeMs, m.comparisons, m.allocations, m.maxDepth);
                    }
                }
            }
        }

        System.out.println("✅ Experiment finished. Results saved in results.csv");
    }

    // Генератор случайных массивов
    private static int[] randomArray(int n) {
        Random rnd = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(1_000_000);
        return arr;
    }

    // Генератор случайных точек
    private static Point[] randomPoints(int n) {
        Random rnd = new Random();
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new Point(rnd.nextInt(10_000), rnd.nextInt(10_000));
        }
        return pts;
    }
}
