package second_week.DequesandRandomizedQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<String>();

        while(!StdIn.hasNextLine() && StdIn.isEmpty());
        String str = StdIn.readLine();
        String[] splited = str.split(" ");

        for(String x: splited) {
            q.enqueue(x);
        }

        for(int i = 0;i < k;i++) {
            StdOut.println(q.dequeue());
        }
    }
}
