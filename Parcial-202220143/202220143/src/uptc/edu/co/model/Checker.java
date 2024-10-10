package uptc.edu.co.model;

import uptc.edu.co.structure.MyQueue;
import uptc.edu.co.structure.MyStack;

public class Checker {

    public Checker() {
    }

    public MyQueue<Integer> sumBinary(MyStack<Integer> stack1, MyStack<Integer> stack2) {
        MyQueue<Integer> resultQueue = new MyQueue<>();
        int carry = 0;

        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int digit1 = (stack1.isEmpty()) ? 0 : stack1.pop();
            int digit2 = (stack2.isEmpty()) ? 0 : stack2.pop();

            int sum = digit1 + digit2 + carry;
            resultQueue.push(sum % 2);
            carry = sum / 2;
        }

        return resultQueue;
    }

    public MyQueue<Integer> sumWithQueue(MyStack<Integer> stack, MyQueue<Integer> resultQueue) {
        MyQueue<Integer> newResultQueue = new MyQueue<>();
        MyStack<Integer> tempQueueStack = new MyStack<>();

        while (!resultQueue.isEmpty()) {
            tempQueueStack.push(resultQueue.poll());
        }

        int carry = 0;

        while (!stack.isEmpty() || !tempQueueStack.isEmpty() || carry != 0) {
            int digit1 = (stack.isEmpty()) ? 0 : stack.pop();
            int digit2 = (tempQueueStack.isEmpty()) ? 0 : tempQueueStack.pop();

            int sum = digit1 + digit2 + carry;
            newResultQueue.push(sum % 2);
            carry = sum / 2;
        }

        return newResultQueue;
    }

    public String result(MyQueue<Integer> queue) {
        StringBuilder result = new StringBuilder();
        MyStack<Integer> tempStack = new MyStack<>();

        while (!queue.isEmpty()) {
            tempStack.push(queue.poll());
        }

        while (!tempStack.isEmpty()) {
            result.append(tempStack.pop());
        }

        return result.toString();
    }
}
