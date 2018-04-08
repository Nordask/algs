package third_week.PatternRecognition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

    final private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) { // finds all line segments containing 4 or more points
        if(points == null)
            throw new NullPointerException("Point array doesn't exist");
        if(hasNullPoint(points))
            throw new IllegalArgumentException("Point array has empty element");
        if(hasDuplicate(points))
            throw new IllegalArgumentException("Point array has duplicated values");

        Point[] copyOfPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(copyOfPoints);

        ArrayList<LineSegment> listOfSegments = new ArrayList<>();

        for(int i = 0;i < copyOfPoints.length - 3;i++) {
            Arrays.sort(copyOfPoints);
            Point[] auxPoints = new Point[copyOfPoints.length];
            sort(copyOfPoints, auxPoints, 0, copyOfPoints.length - 1, copyOfPoints[i].slopeOrder());

            int first = 1, last = 2;
            for(;last < copyOfPoints.length;last++) {
                while(last < copyOfPoints.length &&
                        copyOfPoints[0].slopeTo(copyOfPoints[first]) == copyOfPoints[0].slopeTo(copyOfPoints[last])) {
                    last ++;
                }
                if (last - first >= 3 && copyOfPoints[0].compareTo(copyOfPoints[first]) < 0) {
                    listOfSegments.add(new LineSegment(copyOfPoints[0], copyOfPoints[last - 1]));
                }
                first = last;
            }
        }
        segments = listOfSegments.toArray(new LineSegment[listOfSegments.size()]);
    }

    private boolean hasDuplicate(Point[] points) {
        for(int i = 0;i < points.length - 1;i++) {
            if(points[i].compareTo(points[i + 1]) == 0)
                return true;
        }
        return false;
    }

    private boolean hasNullPoint(Point[] points) {
        for(int i = 0;i < points.length;i++) {
            if(points[i] == null)
                return true;
        }
        return false;
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
        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, comparator);
        sort(a, aux, mid+1, hi, comparator);
        merge(a, aux, lo, mid, hi, comparator);
    }

    private static boolean less(Comparator<Point>comparator, Point first, Point second) {
        return comparator.compare(first, second) < 0;
    }

    public int numberOfSegments() { // the number of line segments
        return segments.length;
    }
    public LineSegment[] segments() {
        return segments.clone();
    }
}