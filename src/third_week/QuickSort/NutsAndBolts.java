package third_week.QuickSort;

public class NutsAndBolts {

    private Comparable[] nuts;
    private Comparable[] bolts;
    private Pair[] pairs;

    NutsAndBolts(Comparable[] nuts, Comparable[] bolts) {
        this.nuts = nuts;
        this.bolts = bolts;
    }

    private class Pair {
        Comparable nut;
        Comparable bolt;

        Pair(Comparable nut, Comparable bolt) {
            this.nut = nut;
            this.bolt = bolt;
        }
    }

    private static boolean less(Comparable compared, Comparable comparable) {
        return compared.compareTo(comparable) < 0;
    }

    private static boolean equals(Comparable compared, Comparable comparable) {
        return compared.compareTo(comparable) == 0;
    }

    private static void exch(Comparable[] array, int i, int j) {
        Comparable temporary = array[i];
        array[i] = array[j];
        array[j] = temporary;
    }

    private static int partition(Comparable[] a, int lo, int hi, Comparable pivot)
    {
        int i = lo, j = lo;
        for (;j < hi;j++) {
            if(less(a[j], pivot)) {
                exch(a, i, j);
                i++;
            }else if(a[j].equals(pivot)){
                exch(a, j, hi);
                j--;
            }
        }
        exch(a, i, hi);
        return i;
        /*
        while (true)
        {
            while (less(a[++i], a[lo]))
                if (i == hi) break;
            while (less(pivot, a[--j]))
                if (j == hi) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
        */
    }

    public Pair[] getPairs() {
        return pairs.clone();
    }

    public static void fitting(Comparable[] nuts, Comparable[] bolts, int low, int high) {
        if (high <= low)
            return;
        int j = partition(nuts, low, high, bolts[high]);
        partition(bolts, low, high, nuts[j]);
        fitting(nuts, bolts, low, j-1);
        fitting(nuts, bolts, j+1, high);
    }

    public static void printArray(Comparable[] array) {
        for (Comparable x: array) {
            System.out.print(x + " ");
        }
    }
    public static void main(String[] args) {
        Character nuts[] = {'3', '5', '2', '1', '4', '6'};//{'@', '#', '$', '%', '^', '&'};
        Character bolts[] = {'5', '4', '1', '6', '3', '2'};//{'$', '%', '&', '^', '@', '#'};

        NutsAndBolts.fitting(nuts, bolts, 0, 5);
        printArray(nuts);
        System.out.println();
        printArray(bolts);
    }
}
