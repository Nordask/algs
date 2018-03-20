package StacksAndQueues;

public class Stack {
    private Node node;
    private class Node{
        String item;
        Node next;
    }

    public void push(String item) {
        Node oldfirst = node;
        node = new Node();
        node.item = item;
        node.next = oldfirst;
    }

    public String pop() {
        String item = node.item;
        node = node.next;
        return item;
    }

    public boolean isEmpty() { return node == null; }
}
