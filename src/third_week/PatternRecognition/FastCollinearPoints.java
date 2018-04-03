package third_week.PatternRecognition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints {

    LineSegment[] segments;

    public FastCollinearPoints(Point[] points) { // finds all line segments containing 4 or more points
        Point[] copyOfPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(copyOfPoints);

        Point[] auxPoints = new Point[points.length];
        ArrayList<LineSegment> listOfSegments = new ArrayList<>();

        for(Point x: copyOfPoints) {
            sort(copyOfPoints, auxPoints, 0, points.length - 1, x.slopeOrder());
            List<Point> slopePoints = new ArrayList<>();

            double slope = 0;
            double matchSlope = Double.NEGATIVE_INFINITY;

            for(int j = 1;j < points.length - 1;j++) {
                slope = x.slopeTo(copyOfPoints[j]);
                if (slope == matchSlope) {
                    slopePoints.add(copyOfPoints[j]);
                }
                else {
                    if(slopePoints.size() >= 3) {
                        listOfSegments.add(new LineSegment(x, copyOfPoints[j + 1]));
                    }
                }
                //else if (x.slopeTo(copyOfPoints[j]) == x.slopeTo(copyOfPoints[j + 1])) {
                //    listOfSegments.add(new LineSegment(x, copyOfPoints[j]));
                //    listOfSegments.add(new LineSegment(x, copyOfPoints[j + 1]));
            }
        }
        segments = listOfSegments.toArray(new LineSegment[listOfSegments.size()]);
    }



    private static void merge(Point[] a, Point[] aux, int lo, int mid, int hi, Comparator<Point>comparator)
    {
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++)
        {
            if (i > mid)
                a[k] = aux[j++];

            else if (j > hi)
                a[k] = aux[i++];

            else if (less(comparator,aux[j], aux[i]))
                a[k] = aux[j++];

            else
                a[k] = aux[i++];
        }
    }

    private static void sort(Point[] a, Point[] aux, int lo, int hi, Comparator<Point>comparator)
    {
        int insertionCounter = 0;
        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, comparator);
        sort(a, aux, mid+1, hi, comparator);
        merge(a, aux, lo, mid, hi, comparator);
    }

    public static boolean less(Comparator<Point>comparator, Point first, Point second) {
        return comparator.compare(first, second) < 0;
    }

    public int numberOfSegments() { // the number of line segments
        return segments.length;
    }
    public LineSegment[] segments() {
        return segments;
    }
}