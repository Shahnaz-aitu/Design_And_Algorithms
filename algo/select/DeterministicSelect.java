package algo.select;

import algo.metrics.Metrics;
import java.util.Arrays;

public class DeterministicSelect {
    public static int select(int[] a, int k, Metrics m) {
        return select(a, 0, a.length - 1, k, m);
    }

    private static int select(int[] a, int lo, int hi, int k, Metrics m) {
        while (true) {
            if (lo == hi) return a[lo];
            int pivot = medianOfMedians(a, lo, hi, m);
            int p = partition(a, lo, hi, pivot, m);
            if (k == p) return a[k];
            else if (k < p) hi = p - 1;
            else lo = p + 1;
        }
    }

    private static int medianOfMedians(int[] a, int lo, int hi, Metrics m) {
        int n = hi - lo + 1;
        if (n <= 5) {
            Arrays.sort(a, lo, hi + 1);
            return a[lo + n/2];
        }
        int numMedians = (n + 4) / 5;
        for (int i = 0; i < numMedians; i++) {
            int subLo = lo + i*5;
            int subHi = Math.min(subLo + 4, hi);
            Arrays.sort(a, subLo, subHi + 1);
            int median = a[subLo + (subHi - subLo)/2];
            swap(a, lo + i, subLo + (subHi - subLo)/2);
        }
        return medianOfMedians(a, lo, lo + numMedians - 1, m);
    }

    private static int partition(int[] a, int lo, int hi, int pivot, Metrics m) {
        int i = lo, j = hi;
        while (i <= j) {
            while (a[i] < pivot) { i++; m.incComparisons(); }
            while (a[j] > pivot) { j--; m.incComparisons(); }
            if (i <= j) {
                swap(a, i, j);
                i++; j--;
            }
        }
        return i - 1;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
}