package StacksAndQueues;
public class QueueWithTwoStacks {

    private Node originalStack, additionalStack;

    private class Node{
        String item;
        Node next;
    }

    public boolean isEmptyOriginal() { return originalStack == null; }
    public boolean isEmptyAdditional() { return additionalStack == null; }

    public void push(String item) {
        Node oldfirst = originalStack;
        originalStack = new Node();
        originalStack.item = item;
        originalStack.next = oldfirst;
    }
    public String pop() {
        String item = originalStack.item;
        originalStack = originalStack.next;
        return item;
    }

    public void enqueue(String item) {
        push(item);
    }

    public String dequeue() {
        if(isEmptyOriginal() && isEmptyAdditional()){
            System.out.print("All stacks are empty");
            System.exit(0);
        }

        while(isEmptyAdditional()){
            Node oldfirst = additionalStack;
            additionalStack = new Node();
            additionalStack.item = pop();
            additionalStack.next = oldfirst;
        }

        String item = additionalStack.item;
        additionalStack = additionalStack.next;
        return item;
    }


    public static void main(String args[]) {
        QueueWithTwoStacks test = new QueueWithTwoStacks();
        QueueWithTwoStacks q_test = new QueueWithTwoStacks();

        String items[] = {"1", "2", "3", "4", "5", "6", "7"};

        for (String x : items) {
            System.out.print(x);
            test.push(x);
        }

        System.out.println();

        while (!test.isEmptyOriginal()) {
            System.out.print(test.pop());
        }

        System.out.println("//----------------------------------");
        for (String x : items) {
            System.out.print(x);
            q_test.enqueue(x);
        }

        System.out.println();

        do {
            System.out.print(q_test.dequeue());
        }while (!q_test.isEmptyAdditional());

        System.out.println("//--------------");
        q_test.enqueue("3");
        q_test.enqueue("44");
        q_test.enqueue("77");

        System.out.println(q_test.dequeue());
        q_test.enqueue("133");
        System.out.println(q_test.dequeue());
        System.out.println(q_test.dequeue());
        System.out.println(q_test.dequeue());
    }
}
