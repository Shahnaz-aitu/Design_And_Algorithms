package algo.geometry;

import algo.metrics.Metrics;
import java.util.*;

public class ClosestPair {
    public static class Point {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static double closestPair(Point[] points, Metrics m) {
        Point[] pts = points.clone();
        Arrays.sort(pts, Comparator.comparingDouble(p -> p.x));
        return closest(pts, 0, pts.length - 1, m);
    }

    private static double closest(Point[] pts, int lo, int hi, Metrics m) {
        if (hi - lo <= 3) {
            double min = Double.POSITIVE_INFINITY;
            for (int i = lo; i <= hi; i++) {
                for (int j = i+1; j <= hi; j++) {
                    m.incComparisons();
                    min = Math.min(min, dist(pts[i], pts[j]));
                }
            }
            Arrays.sort(pts, lo, hi+1, Comparator.comparingDouble(p -> p.y));
            return min;
        }

        int mid = (lo + hi)/2;
        double midx = pts[mid].x;
        double d1 = closest(pts, lo, mid, m);
        double d2 = closest(pts, mid+1, hi, m);
        double d = Math.min(d1, d2);

        Point[] buf = new Point[hi - lo + 1];
        mergeByY(pts, buf, lo, mid, hi);

        List<Point> strip = new ArrayList<>();
        for (int i = lo; i <= hi; i++) {
            if (Math.abs(pts[i].x - midx) < d) strip.add(pts[i]);
        }

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i+1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
                m.incComparisons();
                d = Math.min(d, dist(strip.get(i), strip.get(j)));
            }
        }
        return d;
    }

    private static void mergeByY(Point[] pts, Point[] buf, int lo, int mid, int hi) {
        int i = lo, j = mid+1, k = 0;
        while (i <= mid && j <= hi) {
            if (pts[i].y <= pts[j].y) buf[k++] = pts[i++];
            else buf[k++] = pts[j++];
        }
        while (i <= mid) buf[k++] = pts[i++];
        while (j <= hi) buf[k++] = pts[j++];
        System.arraycopy(buf, 0, pts, lo, k);
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
}
