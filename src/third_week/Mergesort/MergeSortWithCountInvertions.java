package third_week.Mergesort;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MergeSortWithCountInvertions {

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        int i;
        for(i = lo;
            i < hi && (less(a[i], a[i + 1]) || a[i].equals(a[i + 1]));
            i++);

        return (i == hi);
    }

    public static int getInvertionCounter(Comparable[] a) {
        int size = a.length;
        int counter = 0;
        for(int i = 0;i < (size - 1); i++)
            for(int j = i + 1;j < size;j++)
                if(less(a[j], a[i]))
                    counter++;

        return counter;
    }

    private static int merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
    {
        int invertionCounter = 0;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++)
        {
            if (i > mid)
                a[k] = aux[j++];

            else if (j > hi)
                a[k] = aux[i++];

            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
                invertionCounter += (mid - i + 1);
            }

            else
                a[k] = aux[i++];
        }
        return invertionCounter;
    }

    private static boolean less(Comparable a, Comparable b) {
        return (a.compareTo(b) < 0);
    }

    private static int sort(Comparable[] a, Comparable[] aux, int lo, int hi)
    {
        int insertionCounter = 0;
        if (hi <= lo) return 0;

        int mid = lo + (hi - lo) / 2;
        insertionCounter += sort(a, aux, lo, mid);
        insertionCounter += sort(a, aux, mid+1, hi);
        insertionCounter += merge(a, aux, lo, mid, hi);
        return insertionCounter;
    }
    public static int sort(Comparable[] a)
    {
        Comparable[] aux = new Comparable[a.length];
        return sort(a, aux, 0, a.length - 1);
    }

    @Test
    void MergeSortCountTest() {

        Integer[] arr = {56,2,3,37,12,12,0,-5,12,66,4,2,79,45,98,7};
        int arrSize = arr.length;
        int i = 0;

        for(Integer x: arr) {
            if(i != arrSize - 1)
                System.out.print(x + ",");
            else
                System.out.print(x);
            i++;
        }

        assertEquals(MergeSortWithCountInvertions.getInvertionCounter(arr),
                MergeSortWithCountInvertions.sort(arr));
        assertTrue(isSorted(arr, 0, arrSize - 1));

        System.out.println();

        i = 0;
        for(Integer x: arr) {
            if(i != arrSize - 1)
                System.out.print(x + ",");
            else
                System.out.print(x);
            i++;
        }

        System.out.println();

        Integer[] smallArr = {1, 20, 6, 4, 5};
        assertEquals(MergeSortWithCountInvertions.getInvertionCounter(smallArr),
                MergeSortWithCountInvertions.sort(smallArr));
        assertTrue(isSorted(smallArr, 0, smallArr.length - 1));
    }
}
