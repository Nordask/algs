package second_week.DequesandRandomizedQueues;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item []randQeueu;
    private final int minArraySize = 2;
    private int queueSize = 0;

    public RandomizedQueue() {// construct an empty randomized queue
        randQeueu = (Item[]) new Object[minArraySize];
    }

    public boolean isEmpty() {// is the randomized queue empty?
        return queueSize == 0;
    }

    public int size() {// return the number of items on the randomized queue
        return queueSize;
    }

    private void resize(int size)
    {
        Item[] copy = (Item[]) new Object[size];
        for (int i = 0; i < queueSize; i++)
            copy[i] = randQeueu[i];
        randQeueu = copy;
    }

    public void enqueue(Item item) {// add the item
        if(item == null) {
            throw new IllegalArgumentException("Null argument");
        }

        if(queueSize == randQeueu.length){
            resize(queueSize*2);
        }

        randQeueu[queueSize++] = item;
    }
    public Item dequeue() {// remove and return a random item
        if(isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int rand = queueSize > 1 ? StdRandom.uniform(0, queueSize - 1) : 0;
        if(rand != queueSize - 1){
            Item item = randQeueu[rand];
            randQeueu[rand] = randQeueu[queueSize - 1];
            queueSize--;
            return item;
        }

        return randQeueu[--queueSize];
    }
    public Item sample() {// return a random item (but do not remove it)
        if(isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        return queueSize > 1 ? randQeueu[(StdRandom.uniform(0, queueSize))]: randQeueu[0];
    }

    private class RandomizeIterator implements Iterator<Item> {
        int N = size();
        @Override
        public boolean hasNext() {
            int N = size();
            return N > 0;
        }

        @Override
        public Item next() {
            if(isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            N = size();
            return dequeue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("That operation wasn't implement");
        }
    }

    public Iterator<Item> iterator() {// return an independent iterator over items in random order
        return new RandomizeIterator();
    }
    public static void main(String[] args) {
        RandomizedQueue<Character> charDEQ = new RandomizedQueue<Character>();
        RandomizedQueue<Integer> intDEQ = new RandomizedQueue<Integer>();

        String strTest = "EatMeDrinkMe";

        for(Character x:strTest.toCharArray()) {
            System.out.print(x);
            charDEQ.enqueue(x);
        }
        System.out.println();
        for(int i = 0; i < charDEQ.size(); i++) {
            System.out.print(charDEQ.sample());
        }

        System.out.println();
        while(!charDEQ.isEmpty()) {
            System.out.print(charDEQ.dequeue());
        }

        System.out.println();
        System.out.println("//------------------------------");

        for(int i = 0; i < 10; i++) {
            intDEQ.enqueue(i);
        }
        System.out.println();
        Iterator<Integer> IteratorIntDeq = intDEQ.iterator();

        while(IteratorIntDeq.hasNext()) {
            System.out.println(IteratorIntDeq.next());
        }
    }
}
