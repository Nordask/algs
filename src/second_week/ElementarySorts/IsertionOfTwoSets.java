package second_week.ElementarySorts;

public class IsertionOfTwoSets {

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Comparable first, Comparable second) {
        int check = first.compareTo(second);
        return check < 0;
    }

    private static boolean more(Comparable first, Comparable second) {
        int check = first.compareTo(second);
        return check > 0;
    }

    private static boolean equals(Comparable first, Comparable second) {
        int check = first.compareTo(second);
        return check == 0;
    }


    private static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++)
            for (int j = i; j > 0; j--)
                if (less(a[j], a[j-1]))
                    exch(a, j, j-1);
                else break;
    }

    public static int getNumberOfInsertions(Object[] firstArray, Object[] secondArray) {
        sort((Comparable[]) firstArray);
        sort((Comparable[]) secondArray);
        int firstArraySize = firstArray.length;
        int secondArraySize = secondArray.length;
        int i = 0, j = 0;
        int insertionCounter = 0;

        while(i < firstArraySize && j < secondArraySize) {
            if(less((Comparable)firstArray[i], (Comparable)secondArray[j])) {
                i++;
            } else if(more((Comparable)firstArray[i], (Comparable)secondArray[j])) {
                j++;
            } else if(equals((Comparable)firstArray[i], (Comparable)secondArray[j])) {
                insertionCounter++;
                i++;
            }
        }

        return insertionCounter;
    }

    public static void main(String[] args) {
        Integer arr1[] = {3, 1, 7, 5, 4};
        Integer arr2[] = {5, 6, 2, 3};

        for(int x: arr1) {
            System.out.print(x);
        }
        System.out.println();
        for(int x: arr2) {
            System.out.print(x);
        }
        System.out.println();
        //---------------------------------------------
        System.out.println(IsertionOfTwoSets.getNumberOfInsertions(arr1, arr2));
        //---------------------------------------------
        for(int x: arr1) {
            System.out.print(x);
        }
        System.out.println();
        for(int x: arr2) {
            System.out.print(x);
        }
    }
}
