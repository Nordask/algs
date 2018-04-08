package third_week.PatternRecognition;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x, y;

    public Point(int x, int y) { // constructs the point (x, y)
        this.x = x;
        this.y = y;
    }

    public   void draw() { // draws this point
        StdDraw.point(x, y);
    }
    public   void drawTo(Point that) { // draws the line segment from this point to that point
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() { // string representation
        return ("x = " + this.x + " y = " + this.y);
    }

    public int compareTo(Point that) { // compare two points by y-coordinates, breaking ties by x-coordinates
        int comp = this.y - that.y;
        if (comp == 0) {
            comp = this.x - that.x;
        }
            return comp;
    }

    public double slopeTo(Point that) { // the slope between this point and that point
        if((that.y - this.y == 0) && (that.x - this.x == 0)) {
                return Double.NEGATIVE_INFINITY; // -1.0 / 0.0
        } else if (that.x - this.x == 0) {
            return Double.POSITIVE_INFINITY; // 1.0 / 0.0
        }
        return (that.y - this.y) / (double)(that.x - this.x);
    }

    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                double compare = slopeTo(o1) - slopeTo(o2);
                if(compare > 0)
                    return 1;
                else if (compare < 0)
                    return -1;
                else
                    return 0;
            }
        };
    }
}
