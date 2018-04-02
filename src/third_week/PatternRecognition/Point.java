package third_week.PatternRecognition;

import edu.princeton.cs.algs4.StdDraw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        if((this.y - that.y <= 0) && (this.x - that.x == 0)) {
            return Double.NEGATIVE_INFINITY; // -1.0 / 0.0
        } else if (this.x - that.x == 0) {
            return Double.POSITIVE_INFINITY; // 1.0 / 0.0
        }
        return (this.y - that.y) / (this.x - that.x);
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

    public static void main(String[] args) {
        String filename = args[0];
          String readedFromFile = "";
          try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;

            StringBuilder sb = new StringBuilder();
            do {
                line = br.readLine();
                if(line == null)
                    break;
                sb.append(line);
            } while(true);

            readedFromFile = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(readedFromFile);
        String [] splittedReadedFromFile = readedFromFile.trim().split("\\s* \\s*");

        Integer[] arrayX = new Integer[splittedReadedFromFile.length/2];
        Integer[] arrayY = new Integer[splittedReadedFromFile.length/2];

        int xi = 0;
        int yi = 0;
        for(int i = 0;i < splittedReadedFromFile.length; i++) {
            if(i%2 == 0) {
                arrayY[yi++] = Integer.parseInt(splittedReadedFromFile[i]);
            }
            else {
                arrayX[xi++] = Integer.parseInt(splittedReadedFromFile[i]);
            }
        }
/*
        for(Integer x: arrayX) {
            System.out.print(x);
        }

        System.out.println();

        for(Integer x: arrayY) {
            System.out.print(x);
        }
*/
        /*
        int n = 1000;//splittedReadedFromFile.length;
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setXscale(-0.05*n, 1.05*n);
        StdDraw.setYscale(-0.05*n, 1.05*n);   // leave a border to write text

        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        //StdDraw
        StdDraw.point(arrayX[0], arrayY[0]);
        */
    }
}
