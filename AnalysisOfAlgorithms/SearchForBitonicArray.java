package Percolation;

import java.util.ArrayList;
import java.util.List;

public class SearchForBitonicArray {
hhdfhdfh
    public static boolean isInArray(int [] array, int x) {
        //int pivot
        int low = 0;
        int high = array.length - 1;

        while(low <= high){
            int mid = (high - low)/2;
            if(x < array[mid])
                high = mid - 1;
            else if(x > array[mid])
                low = mid + 1;
            else
                return true;
        }
        return false;
    }

    public static int findPivotIndex(int []array, int lowIndex, int highIndex){
        int size = (highIndex - lowIndex) + 1; //get current lengh
        int realSize = array.length;
        int midIndex = size/2;
        int pivotIndex = 0;

        if(midIndex == 0)   //array first element
            pivotIndex = 0;
        else if(midIndex == (realSize - 1))
            pivotIndex = realSize -1;
        else if((array[midIndex] > array[midIndex - 1]) && (array[midIndex] < array[midIndex + 1])) //increasing
            pivotIndex = findPivotIndex(array, midIndex + 1, highIndex);
        else if((array[midIndex] < array[midIndex - 1]) && (array[midIndex] > array[midIndex + 1])) //decreasing
        pivotIndex = findPivotIndex(array, lowIndex, midIndex - 1);
        else
            pivotIndex = midIndex;

        return  pivotIndex;
    }

    public static void main (String args[]) {
        List<int[]> arrList = new ArrayList<int[]>();

        int arr1[] = {1, 2, 3, 4, 5, 0, -1, -2, -3};
        int arr2[] = {1, 3, 5, 7, 9, 8, 6, 4, 2, 0}	;
        int arr3[] = {1, 2, 3, 4, 5};
        int arr4[] = {5, 4, 3, 2, 1};
        int arr5[] = {1, 3, 5, 7, 9, 11, 8, 6, 4, 2, 0}	;
        int test[] = {1, 3, 8, 6, 4, 2, 0}	;

        int check = findPivotIndex(test, 0, test.length - 1);
        System.out.println(check);

        arrList.add(arr1);
        arrList.add(arr2);
        arrList.add(arr3);
        arrList.add(arr4);
        arrList.add(arr5);

        for(int[] x:arrList){
            for(int y: x){
                System.out.print(y + " ");
            }
            System.out.println();
            System.out.println(findPivotIndex(x, 0, x.length - 1));
        }

        //System.out.println(isInArray(arr, 4));
    }
}
