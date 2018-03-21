package second_week.DequesandRandomizedQueues;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private LinkedList<Item> doubleEndedQ;

    public Deque(){ // construct an empty deque
        doubleEndedQ = new LinkedList<Item>();
    }

    public boolean isEmpty(){// is the deque empty?
        return doubleEndedQ.isEmpty();
    }

    public int size(){// return the number of items on the deque
        return  doubleEndedQ.size();
    }

    public void addFirst(Item item){// add the item to the front
        if(item == null){
                throw new IllegalArgumentException("Null argument");
        }

        doubleEndedQ.addFirst(item);
    }

    public void addLast(Item item){// add the item to the end
        if(item == null){
                throw new IllegalArgumentException("Null argument");
        }

        doubleEndedQ.addLast(item);
    }

    public Item removeFirst(){// remove and return the item from the front
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }

        return doubleEndedQ.removeFirst();
    }

    public Item removeLast(){// remove and return the item from the end
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }

        return  doubleEndedQ.removeLast();
    }

    private class DequeIterator implements Iterator<Item>{
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
            return removeFirst();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("That operation wasn't implement");
        }
    }

    public Iterator<Item> iterator(){// return an iterator over items in order from front to end
        return new DequeIterator();
    }

    public static void main(String[] args){
        Deque<Character> charDEQ = new Deque<Character>();
        Deque<Integer> intDEQ = new Deque<Integer>();

        String strTest = "EatMeDrinkMe";

        for(Character x:strTest.toCharArray()){
            System.out.print(x);
            charDEQ.addFirst(x);
        }
        System.out.println();
        while(!charDEQ.isEmpty()){
            System.out.print(charDEQ.removeLast());
        }

        System.out.println();
        System.out.println("//------------------------------");

        for(int i = 0; i < 10; i++){
            System.out.print(i);
            if(i == 0 || i%2 == 0)
                intDEQ.addLast(i);
            else
                intDEQ.addFirst(i);
        }
        System.out.println();
        Iterator<Integer> IteratorIntDeq = intDEQ.iterator();

        while(IteratorIntDeq.hasNext()){
            System.out.println(IteratorIntDeq.next());
        }
    }
}
