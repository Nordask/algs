package first_week.AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Calendar;
import java.util.Random;

public class ThreeSumWorstCase{

    public static int count(int []array) {
        int count = 0;
        int size = array.length;
        for(int i = 0; i < size; i++){
            for(int j = i+1; j < size; j++){
                for(int k = j+1; k < size; k++){
                    if((array[i] + array[j] + array[k]) == 0){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String args[]){
        Random rand = new Random();
        rand.setSeed(Calendar.getInstance().getTimeInMillis());
        //rand.setSeed(33);
        int arr[] = new int[8000];
        for(int i = 0; i < arr.length;i++){
            arr[i] = rand.nextInt();
        }
        for(int x: arr){
            System.out.print(x + ",");
        }
        System.out.println();
        Stopwatch stp = new Stopwatch();
        System.out.println(ThreeSumWorstCase.count(arr));
        System.out.println(stp.elapsedTime());
    }
}
