package third_week.PatternRecognition;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    final private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points

        Point[] copyOfPoints = Arrays.copyOfRange(points, 0, points.length);
        Arrays.sort(copyOfPoints);
        int size = points.length;

        ArrayList<LineSegment> listOfSegments = new ArrayList<>();

        for (int i = 0; i < size - 3; i++)  // first point
            for (int j = i + 1; j < size - 2; j++) // second point
                for (int k = j + 1; k < size - 1; k++) // third point
                    for (int l = k + 1; l < size; l++) // fouth point
                        if (copyOfPoints[i].slopeTo(copyOfPoints[j]) == copyOfPoints[i].slopeTo(copyOfPoints[k]) &&
                                copyOfPoints[i].slopeTo(copyOfPoints[j]) == copyOfPoints[i].slopeTo(copyOfPoints[l]))
                            listOfSegments.add(new LineSegment(copyOfPoints[i], copyOfPoints[l]));

        segments = listOfSegments.toArray(new LineSegment[listOfSegments.size()]);
    }
    public int numberOfSegments() { // the number of line segments
        return  segments.length;
    }
    public LineSegment[] segments() { // the line segments
        return segments.clone();
    }
}
