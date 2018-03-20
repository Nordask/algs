package StacksAndQueues.FirstImplementation;

public class Stack <Item>{
    private Node node;
    private class Node{
        Item item;
        Node next;
    }

    public void push(Item item) {
        Node oldfirst = node;
        node = new Node();
        node.item = item;
        node.next = oldfirst;
    }

    public Item pop() {
        Item item = node.item;
        node = node.next;
        return item;
    }

    public boolean isEmpty() { return node == null; }
}
