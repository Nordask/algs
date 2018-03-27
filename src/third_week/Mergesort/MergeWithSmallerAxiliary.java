package third_week.Mergesort;

public class MergeWithSmallerAxiliary {

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
    {
        // precondition: a[mid+1..hi] sorted
        for (int k = lo; k <= mid; k++)
            aux[k] = a[k];
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++)
        {
            if      (i > mid)              a[k] = a[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(a[j], aux[i]))   a[k] = a[j++];
            else                           a[k] = aux[i++];
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        Integer[] arr = {1,3,5,7,9,2,4,6,8,10};
        int arrSize = arr.length;
        Integer[] auxArr = new Integer[arrSize/2];

        int i = 0;
        for(Integer x: arr) {
            if(i != arrSize - 1)
                System.out.print(x + ",");
            else
                System.out.print(x);
            i++;
        }

        System.out.println();
        MergeWithSmallerAxiliary.merge(arr, auxArr, 0, 4, 9);
        i = 0;
        for(Integer x: arr) {
            if(i != arrSize - 1)
                System.out.print(x + ",");
            else
                System.out.print(x);
            i++;
        }

        System.out.println();

        Integer[] largerArr = {1,2,3,5,7,9,11,12,13,14,15,2,4,6,8,10,16,17,18,18,19,20};
        int largerArrSize = largerArr.length;
        Integer[] largerAuxArr = new Integer[largerArrSize/2];

        i = 0;
        for(Integer x: largerArr) {
            if(i != largerArrSize - 1)
                System.out.print(x + ",");
            else
                System.out.print(x);
            i++;
        }

        System.out.println();
        MergeWithSmallerAxiliary.merge(largerArr, largerAuxArr, 0, 10, 20);

        i = 0;
        for(Integer x: largerArr) {
            if(i != largerArrSize - 1)
                System.out.print(x + ",");
            else
                System.out.print(x);
            i++;
        }
    }
}
