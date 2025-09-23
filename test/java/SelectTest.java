package test.java;

import algo.metrics.Metrics;
import algo.select.DeterministicSelect;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectTest {

    @Test
    void testDeterministicSelectSmall() {
        int[] arr = {12, 5, 7, 3, 19, 1, 2, 6};
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        for (int k = 0; k < arr.length; k++) {
            int val = DeterministicSelect.select(arr.clone(), k, new Metrics());
            assertEquals(sorted[k], val);
        }
    }

    @Test
    void testDeterministicSelectRandom() {
        Random rnd = new Random();
        for (int t = 0; t < 50; t++) {
            int[] arr = rnd.ints(20, -100, 100).toArray();
            int[] sorted = arr.clone();
            Arrays.sort(sorted);

            int k = rnd.nextInt(arr.length);
            int val = DeterministicSelect.select(arr.clone(), k, new Metrics());
            assertEquals(sorted[k], val);
        }
    }
}
