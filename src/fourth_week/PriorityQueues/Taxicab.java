package fourth_week.PriorityQueues;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.LinkedList;

public class Taxicab {
    int first, second, cube;

    public Taxicab(int first, int second) {
        this.first = first;
        this.second =second;
        cube = first*first*first + second*second*second;
    }

    public int compareTo(Taxicab comparator) {
        if(this.cube > comparator.cube)
            return 1;
        else if(this.cube < comparator.cube)
            return -1;
        else
            return 0;
    }

    public boolean equals(Taxicab first, Taxicab second) {
        return first.compareTo(second) == 0;
    }

    public static Taxicab[] getTaxicabBrute(int n) {
        LinkedList<Taxicab> taxiCabs = new LinkedList<>();

        for(int i = 1;i < n;i++) {
            for(int j = i + 1;j < n;j++) {
                Taxicab tax = new Taxicab(i, j);
                if(tax.cube == n)
                    taxiCabs.add(tax);
            }
        }
        return taxiCabs.toArray(new Taxicab[taxiCabs.size()]);
    }

    public static void main(String[] args) {
        Stopwatch stp = new Stopwatch();
        Taxicab[] arr = Taxicab.getTaxicabBrute(1729);
        System.out.println(stp.elapsedTime());

        for(Taxicab x: arr)
            System.out.println(x.first + "  " + x.second);
    }
}
