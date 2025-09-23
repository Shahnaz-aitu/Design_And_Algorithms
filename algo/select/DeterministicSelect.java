package algo.select;

import algo.metrics.Metrics;
import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] a, int k, Metrics m) {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("k must be between 0 and " + (a.length - 1));
        }
        return select(a, 0, a.length - 1, k, m);
    }

    private static int select(int[] a, int lo, int hi, int k, Metrics m) {
        m.enterRecursion();
        try {
            if (lo == hi) {
                return a[lo];
            }

            int pivotIndex = pivot(a, lo, hi, m);
            pivotIndex = partition(a, lo, hi, pivotIndex, m);

            if (k == pivotIndex) {
                return a[k];
            } else if (k < pivotIndex) {
                return select(a, lo, pivotIndex - 1, k, m);
            } else {
                return select(a, pivotIndex + 1, hi, k, m);
            }
        } finally {
            m.exitRecursion();
        }
    }

    private static int pivot(int[] a, int lo, int hi, Metrics m) {
        // Base case: for small arrays, return median directly
        int n = hi - lo + 1;
        if (n <= 5) {
            Arrays.sort(a, lo, hi + 1);
            return lo + n / 2;
        }

        // Divide into groups of 5 and find their medians
        int numGroups = (n + 4) / 5;
        int[] medians = new int[numGroups];

        for (int i = 0; i < numGroups; i++) {
            int groupLo = lo + i * 5;
            int groupHi = Math.min(groupLo + 4, hi);
            int medianIndex = pivot(a, groupLo, groupHi, m); // Recursively find median of this group
            medians[i] = a[medianIndex];
        }

        // Find median of medians recursively
        return selectMedian(medians, 0, numGroups - 1, numGroups / 2, m);
    }

    private static int selectMedian(int[] a, int lo, int hi, int k, Metrics m) {
        // Simple selection for small arrays
        if (hi - lo + 1 <= 10) {
            int[] copy = Arrays.copyOfRange(a, lo, hi + 1);
            Arrays.sort(copy);
            int result = copy[k];
            // Find index of result in original array
            for (int i = lo; i <= hi; i++) {
                if (a[i] == result) return i;
            }
            return lo + k;
        }
        return select(a, lo, hi, k, m);
    }

    private static int partition(int[] a, int lo, int hi, int pivotIndex, Metrics m) {
        int pivotValue = a[pivotIndex];
        swap(a, pivotIndex, hi); // Move pivot to end

        int i = lo;
        for (int j = lo; j < hi; j++) {
            m.incComparisons();
            if (a[j] <= pivotValue) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, hi); // Move pivot to final position
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}