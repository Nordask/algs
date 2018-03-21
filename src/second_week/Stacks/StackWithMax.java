package second_week.Stacks;
import StacksAndQueues.FirstImplementation.Stack;

public class StackWithMax {

    Stack<Integer> originalStack = new Stack();
    private Stack<Integer> maxStack = new Stack();

    int max = 0;
    public void push(int item) {
        if(originalStack.isEmpty())
            max = item;
        if(item > max)
            max = item;
        originalStack.push(item);
    }

    public int pop() {

       int valueForPop = originalStack.pop();

       while(!originalStack.isEmpty()){
            maxStack.push(originalStack.pop());
        }

       if(!maxStack.isEmpty()){
           int currentValue = maxStack.pop();
           max = currentValue;// override max, cuz we can have max value from push or initialization value
           originalStack.push(currentValue);
           while(!maxStack.isEmpty()){
               currentValue = maxStack.pop();
               originalStack.push(currentValue);
               if(max < currentValue)
                   max = currentValue;
           }
       }

        return valueForPop;
    }

    public int getMax(){
        if(originalStack.isEmpty())
            return 0;
        return  max;
    }

    public boolean isEmpty() { return  originalStack.isEmpty(); }

    public static void main(String args[]){
        String items = "1234657";
        StackWithMax stack = new StackWithMax();

        System.out.println(items);

        for (int i = 0; i < items.length(); i++){
           stack.push(Character.getNumericValue(items.charAt(i)));
        }

        System.out.println();
        System.out.println("//-----------------------");

        System.out.println(stack.getMax());
        System.out.print(stack.pop());
        System.out.print(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.getMax());
        System.out.print(stack.pop());
        System.out.print(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.getMax());
        System.out.print(stack.pop());
        System.out.println(stack.getMax());
        /*
        for (int i = 0; i < items.length(); i++){
            System.out.print(stack.pop());
        }
        System.out.println();
        System.out.println(stack.getMax());
        */
    }
}
