import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SearchForBitonicArray {

    public static boolean isInArray(int [] array, int x) {
        int low, high;
        int pivotIndex = findPivotIndex(array);
        if(x > array[pivotIndex]){

        }
        //  left
        low = 0;
        high = pivotIndex;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(x < array[mid])
                high = mid - 1;
            else if(x > array[mid])
                low = mid + 1;
            else if(x == array[mid])
                return true;
        }

        //  right
        low = pivotIndex;
        high = array.length - 1;
        while (low <= high){
            int mid = low + (high - low)/2;
            if(x < array[mid])
                low = mid + 1;
            else if(x > array[mid])
                high = mid - 1;
            else if(x == array[mid])
                return true;
        }

        return false;
    }

    public static int findPivotIndex(int []array, int lowIndex, int highIndex){ //  recurcion
        int size = (highIndex - lowIndex) + 1; //   get current lengh
        int realSize = array.length;
        int midIndex = lowIndex + size/2;
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

    public static int findPivotIndex(int []array){  //  loop
        int lowIndex = 0;
        int highIndex = array.length - 1;
        int pivotIndex = 0;
        while(lowIndex <= highIndex){
            int midIndex = lowIndex + (highIndex - lowIndex)/2;

            if(midIndex == 0)   //  array first element
                return pivotIndex = 0;
            else if(midIndex == (array.length - 1))
                return pivotIndex = array.length -1;
            else if((array[midIndex] > array[midIndex - 1]) && (array[midIndex] < array[midIndex + 1])) //  increasing
                lowIndex = midIndex + 1;
            else if((array[midIndex] < array[midIndex - 1]) && (array[midIndex] > array[midIndex + 1])) //  decreasing
                highIndex = midIndex - 1;
            else
                return pivotIndex = midIndex;
        }

        return pivotIndex;
    }

    public static void main (String args[]) {
    // test for find pivot index
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
        arrList.add(test);

        for(int[] x:arrList){
            for(int y: x){
                System.out.print(y + " ");
            }
            System.out.println();
            System.out.println(findPivotIndex(x, 0, x.length - 1));
            System.out.println(findPivotIndex(x));
        }

        int testConsole[] = {1, 3, 8, 6, 4, 2, 0, -5, -7, -100}	;
        System.out.println("//-------------------------------------------");
        for(int x:testConsole) {
            System.out.print(x + " ");
        }

        BufferedReader br = null;

        try {

            br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {

                String input = br.readLine();

                if ("q".equals(input)) {
                    System.out.println("Exit!");
                    System.exit(0);
                }
                int x = Integer.parseInt(input);
                System.out.println("input : " + x);
                System.out.println(isInArray(testConsole, x));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
