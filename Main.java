package algo;

import algo.metrics.Metrics;
import algo.sort.MergeSort;
import algo.sort.QuickSort;
import algo.select.DeterministicSelect;
import algo.geometry.ClosestPair;
import algo.geometry.ClosestPair.Point;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Metrics m = new Metrics();

        // Пример: MergeSort
        int[] arr = {5,3,8,4,2,7};
        MergeSort.sort(arr, m);
        System.out.println("MergeSort result: " + Arrays.toString(arr) + " | depth=" + m.maxDepth);

        // Пример: QuickSort
        m.reset();
        int[] arr2 = {9,1,5,3,7,4,8};
        QuickSort.sort(arr2, m);
        System.out.println("QuickSort result: " + Arrays.toString(arr2) + " | depth=" + m.maxDepth);

        // Пример: Select
        m.reset();
        int[] arr3 = {12,5,7,3,19,1,2,6};
        int k = 3;
        int kth = DeterministicSelect.select(arr3, k, m);
        System.out.println(k+"-th smallest = " + kth);

        // Пример: Closest Pair
        m.reset();
        Point[] pts = {
                new Point(0,0), new Point(5,5), new Point(3,4),
                new Point(7,1), new Point(2,2), new Point(9,6)
        };
        double dist = ClosestPair.closestPair(pts, m);
        System.out.println("Closest pair distance = " + dist);
    }
}
