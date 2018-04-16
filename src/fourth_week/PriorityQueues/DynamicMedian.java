package fourth_week.PriorityQueues;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

public class DynamicMedian {

    private MaxPQ<Integer> minHeap;
    private MinPQ<Integer> maxHeap;
    private int minSize;
    private int maxSize;

    DynamicMedian() {
        minHeap = new MaxPQ<Integer>();
        maxHeap = new MinPQ<Integer>();
        minSize = 0;
        maxSize = 0;
    }

    public Integer getMedian() {
        minSize = minHeap.size();
        maxSize = maxHeap.size();

        if (minSize == 0 && maxSize != 0)
            return maxHeap.min();
        else if(minSize != 0 && maxSize == 0)
            return minHeap.max();
        else if(minSize == 0 && maxSize == 0)
            return 0;

        if(minSize == maxSize)
            return (maxHeap.min() + minHeap.max()) / 2;
        else if(minSize > maxSize)
            return minHeap.max();
        else if(minSize < maxSize)
            return maxHeap.min();

        return 0;
    }

    public void insert(Integer value) {
        minSize = minHeap.size();
        maxSize = maxHeap.size();

        if(value >= getMedian()) {
            maxHeap.insert(value);
            if((maxSize - minSize) > 1) {
                minHeap.insert(maxHeap.delMin());
            }
        }
        else if(value < getMedian()) {
            minHeap.insert(value);
            if((minSize - maxSize) > 1) {
                maxHeap.insert(minHeap.delMax());
            }
        }
    }

    public void remove() {
        Integer median = getMedian();
        minSize = minHeap.size();
        maxSize = maxHeap.size();

        if(minSize <= maxSize)
            maxHeap.delMin();
        else
            minHeap.delMax();
    }

    public static void main(String[] args) {
        Integer[] arr = {11, 9, 3, 5, 5};
        DynamicMedian med = new DynamicMedian();

        for(Integer x: arr)
            med.insert(x);

        System.out.println(med.getMedian());
    }
}
