package second_week.StacksAndQueues.SecondImplementation;

public class QueueWithTwoStacks <Item>{

    private Stack<Item> originalStack;// working as stack LIFO
    private Stack<Item> additionalStack;// for implementation queue order FIFO

    public QueueWithTwoStacks(int size){
        originalStack = new Stack(size);
        additionalStack = new Stack(size);
    }

    public boolean isEmpty(){// are all stacks empty?
        return originalStack.isEmpty() && additionalStack.isEmpty();
    }

    public void enqueue(Item item){
        while(!originalStack.isEmpty()){// push all to additionalStack
            additionalStack.push(originalStack.pop());
        }

        originalStack.push(item);// push new Item

        while(!additionalStack.isEmpty()){// push all items back to originalStack
            originalStack.push(additionalStack.pop());
        }

    }

    public Item dequeue(){
        if(originalStack.isEmpty() && additionalStack.isEmpty()){
            System.out.print("All stacks are empty");
            System.exit(0);
        }
        return originalStack.pop();
    }

    public static void main(String args[]) {
        Stack test = new Stack(100);
        QueueWithTwoStacks q_test = new QueueWithTwoStacks(100);

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
