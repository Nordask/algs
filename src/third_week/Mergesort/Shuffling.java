package third_week.Mergesort;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Test;

public class Shuffling<Item> {

    private static class SinglyLinkedList <Item> {
        Item item;
        SinglyLinkedList next;
    }

    public static <Item> int push(Item item, SinglyLinkedList<Item> stack, int size) {
        if (item == null) {
            throw new IllegalArgumentException("Argument must have value");
        }

        if(isEmpty(size)) {
            stack.item = item;
            stack.next = null;
            size++;
        }
        else {
            SinglyLinkedList<Item> temporaryStack = new SinglyLinkedList<>();
            temporaryStack.item = stack.item;
            temporaryStack.next = stack.next;

            stack.next = temporaryStack;
            stack.item = item;
            size++;
        }

        return size;
    }

    public static <Item> Item pop(SinglyLinkedList<Item> stack, int size) {
        if(isEmpty(size)) {
            throw new NullPointerException("Stack is empty");
        }

        Item mustSave = stack.item;
        if (stack.next != null) {
            SinglyLinkedList <Item> temporary = stack.next;
            stack.item = temporary.item;
            stack.next = temporary.next;
        }

        size--;

        return mustSave;
    }

    public static boolean isEmpty(int size) {
        return size == 0;
    }

    public static <Item> Item[] shuffle(Item[] arr) {
        final SinglyLinkedList<Item> stack = new SinglyLinkedList<>();
        int size = 0;
        for(int i = 0;i < arr.length;i++) {
            size = push(arr[i], stack, size);
        }

        for (int i = 0; i < size; i++)
        {
            int r = StdRandom.uniform(i + 1);
            exch(stack, i, r, size);
        }

        int i = 0;
        while (size > 0) {
            arr[i++] = pop(stack, size);
            size --;
        }

        return arr;
    }

    public static<Item> void exch(SinglyLinkedList<Item> stack, int i, int r, int size) {
        SinglyLinkedList<Item> first = get(i, size, stack);
        SinglyLinkedList<Item> second = get(r, size, stack);

        Item temporary = first.item;

        first.item = second.item;
        second.item = temporary;
    }

    private static<Item> SinglyLinkedList get(int index, int size, SinglyLinkedList<Item> stack )
    {
        //forces the index to be valid
        assert (index >= 0 && index < size);

        SinglyLinkedList temp = stack; //start at last element of list

        //iterate to the correct node
        for(int i = 0; i < index; i++)
        {
            temp = temp.next;
        }

        return temp; //and return the corresponding element
    }

    @Test
    void shufflingTest() {

        Integer[] arr = {1,2,3,4,5,6,7,8,9,10};

        for(Integer x:arr) {
            System.out.print(x);

        }

        System.out.println();

        shuffle(arr);

        for(Integer x:arr) {
            System.out.print(x);

        }
    }
}
