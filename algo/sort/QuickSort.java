package algo.sort;

import algo.metrics.Metrics;
import java.util.Random;

public class QuickSort {
    private static final Random rnd = new Random();

    public static void sort(int[] a, Metrics m) {
        quicksort(a, 0, a.length - 1, m);
    }

    private static void quicksort(int[] a, int lo, int hi, Metrics m) {
        while (lo < hi) {
            m.enterRecursion();
            int p = partition(a, lo, hi, m);
            if (p - lo < hi - p) {
                quicksort(a, lo, p - 1, m);
                lo = p + 1; // итерация на большом отрезке
            } else {
                quicksort(a, p + 1, hi, m);
                hi = p - 1;
            }
            m.exitRecursion();
        }
    }

    private static int partition(int[] a, int lo, int hi, Metrics m) {
        int pivotIndex = lo + rnd.nextInt(hi - lo + 1);
        int pivot = a[pivotIndex];
        swap(a, pivotIndex, hi);
        int i = lo;
        for (int j = lo; j < hi; j++) {
            m.incComparisons();
            if (a[j] < pivot) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, hi);
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
}