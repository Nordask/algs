package fourth_week.PriorityQueues;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandPriorQueue<Key> {
    private Key[] pq;                    // store items at indices 1 to n
    private int n;                       // number of items on priority queue
    private Comparator<Key> comparator;


    public RandPriorQueue(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public void insert(Key x) {

        // double size of array if necessary
        //if (n == pq.length - 1) resize(2 * pq.length);

        // add x, and percolate it up to maintain heap invariant
        pq[++n] = x;
        swim(n);
        assert isMaxHeap(1);
    }

    // is subtree of pq[1..n] rooted at k a max heap?
    private boolean isMaxHeap(int k) {
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && less(k, left))  return false;
        if (right <= n && less(k, right)) return false;
        return isMaxHeap(left) && isMaxHeap(right);
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        }
        else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    public Key sample() {
        Random rand = new Random();
        return pq[rand.nextInt(n) + 1];
    }

    public Key delRandom() {
        Random rand = new Random();
        int index = rand.nextInt(n) + 1;
        Key value = pq[index];
        exch(index, n--);
        sink(1);
        pq[n+1] = null;
        assert isMaxHeap(1);
        return value;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public static void main(String[] args) {
        Integer[] arr = {11, 9, 3, 5, 5};
        RandPriorQueue<Integer> rand = new RandPriorQueue<>(5);

        for(Integer x: arr)
            rand.insert(x);

        for(int i = arr.length * 2;i > 0;i--)
            System.out.println(rand.sample());
        System.out.println("//---------------------------------");
        for(int i = arr.length;i > 0;i--)
            System.out.println(rand.delRandom());
    }
}
