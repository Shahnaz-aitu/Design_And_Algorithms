package test.java;

import algo.metrics.Metrics;
import algo.sort.MergeSort;
import algo.sort.QuickSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortTest {

    @Test
    void testMergeSort() {
        int[] arr = {5, 3, 8, 1, 2};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        MergeSort.sort(arr, new Metrics());
        assertArrayEquals(expected, arr);
    }

    @Test
    void testQuickSort() {
        int[] arr = {10, -1, 7, 3, 2};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        QuickSort.sort(arr, new Metrics());
        assertArrayEquals(expected, arr);
    }
}
