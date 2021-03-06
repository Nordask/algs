package second_week.StacksAndQueues.FirstImplementation;

public class QueueWithTwoStacks <Item>{
    private Stack<Item> originalStack = new Stack();
    private Stack<Item> additionalStack = new Stack();

    public void enqueue(Item item) {
        originalStack.push(item);
    }

    public boolean isEmpty(){
        return originalStack.isEmpty() && additionalStack.isEmpty();
    }

    public Item dequeue() {
        if(originalStack.isEmpty() && additionalStack.isEmpty()){
            System.out.print("All stacks are empty");
            System.exit(0);
        }
        if(additionalStack.isEmpty()) {//   push to additionalStack only when it's empty
            while (!originalStack.isEmpty()) {// push to additionalStack from originalStack
                additionalStack.push(originalStack.pop());
            }
        }

        return additionalStack.pop();
    }

    public static void main(String args[]) {
        Stack test = new Stack();
        QueueWithTwoStacks q_test = new QueueWithTwoStacks();

        String items[] = {"1", "2", "3", "4", "5", "6", "7"};

        for (String x : items) {
            System.out.print(x);
            test.push(x);
        }

        System.out.println();

        while (!test.isEmpty()) {
            System.out.print(test.pop());
        }

        System.out.println();
        System.out.println("//----------------------------------");
        for (String x : items) {
            System.out.print(x);
            q_test.enqueue(x);
        }

        System.out.println();

        do {
            System.out.print(q_test.dequeue());
        }while (!q_test.isEmpty());

        System.out.println();
        System.out.println("//-----------------------------------");
        q_test.enqueue(3);
        q_test.enqueue(44);
        q_test.enqueue(77);

        System.out.println(q_test.dequeue());
        q_test.enqueue(133);
        System.out.println(q_test.dequeue());
        System.out.println(q_test.dequeue());
        System.out.println(q_test.dequeue());
    }
}
