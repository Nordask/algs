package second_week.DequesandRandomizedQueues;

import edu.princeton.cs.algs4.StdRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private ArrayList<Item> randQeueu;

    public RandomizedQueue(){// construct an empty randomized queue
        randQeueu = new ArrayList<Item>();
    }
    public boolean isEmpty(){// is the randomized queue empty?
        return randQeueu.isEmpty();
    }
    public int size(){// return the number of items on the randomized queue
        return randQeueu.size();
    }
    public void enqueue(Item item){// add the item
        if(item == null){
            throw new IllegalArgumentException("Null argument");
        }

        randQeueu.add(item);
    }
    public Item dequeue(){// remove and return a random item
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }

        return randQeueu.remove(StdRandom.uniform(0, size()));
    }
    public Item sample(){// return a random item (but do not remove it)
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }

        return randQeueu.get(StdRandom.uniform(0, size()));
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
            if(isEmpty()){
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

    public Iterator<Item> iterator(){// return an independent iterator over items in random order
        return new RandomizeIterator();
    }
    public static void main(String[] args){
        RandomizedQueue<Character> charDEQ = new RandomizedQueue<Character>();
        RandomizedQueue<Integer> intDEQ = new RandomizedQueue<Integer>();

        String strTest = "EatMeDrinkMe";

        for(Character x:strTest.toCharArray()){
            System.out.print(x);
            charDEQ.enqueue(x);
        }
        System.out.println();
        for(int i = 0; i < charDEQ.size(); i++){
            System.out.print(charDEQ.sample());
        }

        System.out.println();
        while(!charDEQ.isEmpty()){
            System.out.print(charDEQ.dequeue());
        }

        System.out.println();
        System.out.println("//------------------------------");

        for(int i = 0; i < 10; i++){
            intDEQ.enqueue(i);
        }
        System.out.println();
        Iterator<Integer> IteratorIntDeq = intDEQ.iterator();

        while(IteratorIntDeq.hasNext()){
            System.out.println(IteratorIntDeq.next());
        }
    }
}
