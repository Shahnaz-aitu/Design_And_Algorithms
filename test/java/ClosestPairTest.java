package test.java;

import algo.geometry.ClosestPair;
import algo.geometry.ClosestPair.Point;
import algo.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    // Brute force O(n^2) для проверки
    private double bruteForce(Point[] pts) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                double dx = pts[i].x - pts[j].x;
                double dy = pts[i].y - pts[j].y;
                min = Math.min(min, Math.sqrt(dx * dx + dy * dy));
            }
        }
        return min;
    }

    @Test
    void testClosestPairSmall() {
        Point[] pts = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(5, 1),
                new Point(9, 6)
        };
        double fast = ClosestPair.closestPair(pts, new Metrics());
        double slow = bruteForce(pts);
        assertEquals(slow, fast, 1e-9);
    }

    @Test
    void testClosestPairRandom() {
        Random rnd = new Random();
        Point[] pts = new Point[200];
        for (int i = 0; i < pts.length; i++) {
            pts[i] = new Point(rnd.nextInt(1000), rnd.nextInt(1000));
        }

        double fast = ClosestPair.closestPair(pts, new Metrics());
        double slow = bruteForce(pts);

        assertEquals(slow, fast, 1e-9);
    }
}
