package second_week.DequesandRandomizedQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    final private DoubleEndedStack<Item> firstItem; // virtual first
    final private DoubleEndedStack<Item> lastItem;  // virtual last
    private int size = 0;

    private class DoubleEndedStack<Item>{
        Item item;
        private DoubleEndedStack prev;
        private DoubleEndedStack next;

        DoubleEndedStack(Item item, DoubleEndedStack<Item> prev, DoubleEndedStack<Item> next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public Deque() { // construct an empty deque
        firstItem = new DoubleEndedStack<Item>(null, null, null);
        lastItem = new DoubleEndedStack<Item>(null, null, null);
        firstItem.next = lastItem;
        lastItem.prev = firstItem;
    }

    public boolean isEmpty() {// is the deque empty?
        return size == 0;
    }

    public int size(){// return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) {// add the item to the front
        if(item == null) {
                throw new IllegalArgumentException("Null argument");
        }

        if(isEmpty()){
            // create first real element
            final DoubleEndedStack<Item> newFirstItem = new DoubleEndedStack<Item>(item, firstItem, lastItem);
            firstItem.next = newFirstItem;
            lastItem.prev = newFirstItem;
            size++;
        }
        else{
            //  creat Object for old FirstElement
            final DoubleEndedStack<Item> newFirstItem = new DoubleEndedStack<Item>(item, firstItem, firstItem.next);
            // new parameters for old first element
            firstItem.next.prev = newFirstItem;
            //  change point for firstItem.next
            firstItem.next = newFirstItem;

            size++;
        }
    }

    public void addLast(Item item) {// add the item to the end
        if(item == null){
                throw new IllegalArgumentException("Null argument");
        }

        if(isEmpty()){
            // create last real element
            final DoubleEndedStack<Item> newLastItem = new DoubleEndedStack<>(item, firstItem, lastItem);
            firstItem.next = newLastItem;
            lastItem.prev = newLastItem;
            size++;
        }else{
            //  creat Object for old LastElement
            final DoubleEndedStack<Item> newLastItem = new DoubleEndedStack<>(item, lastItem.prev, lastItem);
            // new parameters for old last element
            lastItem.prev.next = newLastItem;
            //  change point for lastItem.next
            lastItem.prev = newLastItem;

            size++;
        }
    }

    public Item removeFirst() {// remove and return the item from the front
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }

        DoubleEndedStack<Item> currentFirstItem = firstItem.next;
        Item mustSave = currentFirstItem.item;
        //  closure
        firstItem.next = currentFirstItem.next;
        firstItem.next.prev = firstItem;

        //  remove element
        currentFirstItem = null;
        size--;

        return mustSave;
    }

    public Item removeLast() {// remove and return the item from the end
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }

        DoubleEndedStack<Item> currentLastItem = lastItem.prev;
        Item mustSave = currentLastItem.item;
        //  closure
        lastItem.prev = currentLastItem.prev;
        lastItem.prev.next = lastItem;

        //  remove element
        currentLastItem = null;
        size--;

        return mustSave;
    }

    private void swap (DoubleEndedStack<Item> oldElement, DoubleEndedStack<Item> newElement){

    }
    private class DequeIterator implements Iterator<Item> {
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
            return removeFirst();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("That operation wasn't implement");
        }
    }

    public Iterator<Item> iterator() {// return an iterator over items in order from front to end
        return new DequeIterator();
    }

    public static void main(String[] args) {
        Deque<Character> charDEQ = new Deque<Character>();
        Deque<Integer> intDEQ = new Deque<Integer>();

        String strTest = "EatMeDrinkMe";

        for(Character x:strTest.toCharArray()) {
            System.out.print(x);
            charDEQ.addFirst(x);
        }
        System.out.println();
        while(!charDEQ.isEmpty()) {
            System.out.print(charDEQ.removeFirst());
        }

        System.out.println();
        System.out.println("//------------------------------");

        for(int i = 0; i < 10; i++) {
            System.out.print(i);
            if(i == 0 || i%2 == 0)
                intDEQ.addLast(i);
            else
                intDEQ.addFirst(i);
        }
        System.out.println();
        Iterator<Integer> IteratorIntDeq = intDEQ.iterator();

        while(IteratorIntDeq.hasNext()) {
            System.out.println(IteratorIntDeq.next());
        }
    }
}
