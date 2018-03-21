package StacksAndQueues.SecondImplementation;

public class Stack<Item> {

    private Item[] s;
    private int N = 0;

    public Stack(int size) {
        s = (Item[]) new Object[size];
    }

    public boolean isEmpty() { return N == 0; }

    public void push(Item item) {
        if(N == s.length){
            resize(N*2);
        }
        s[N++] = item;

    }

    public Item pop() {
        Item item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4) resize(s.length/2);
        return item;
    }

    private void resize(int size)
    {
        Item[] copy = (Item[]) new Object[size];
        for (int i = 0; i < N; i++)
            copy[i] = s[i];
        s = copy;
    }

}
