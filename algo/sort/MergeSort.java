package algo.sort;

import algo.metrics.Metrics;

public class MergeSort {
    private static final int CUTOFF = 16; // для insertion sort

    public static void sort(int[] a, Metrics m) {
        int[] buffer = new int[a.length];
        sort(a, buffer, 0, a.length - 1, m);
    }

    private static void sort(int[] a, int[] buf, int lo, int hi, Metrics m) {
        if (hi - lo + 1 <= CUTOFF) {
            insertionSort(a, lo, hi, m);
            return;
        }
        m.enterRecursion();
        int mid = (lo + hi) / 2;
        sort(a, buf, lo, mid, m);
        sort(a, buf, mid + 1, hi, m);
        merge(a, buf, lo, mid, hi, m);
        m.exitRecursion();
    }

    private static void merge(int[] a, int[] buf, int lo, int mid, int hi, Metrics m) {
        for (int i = lo; i <= hi; i++) {
            buf[i] = a[i];
            m.incAllocations();
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = buf[j++];
            else if (j > hi) a[k] = buf[i++];
            else if (less(buf[i], buf[j], m)) a[k] = buf[i++];
            else a[k] = buf[j++];
        }
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo && greater(a[j], key, m)) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    private static boolean less(int x, int y, Metrics m) {
        m.incComparisons();
        return x < y;
    }
    private static boolean greater(int x, int y, Metrics m) {
        m.incComparisons();
        return x > y;
    }
}