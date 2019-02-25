package Design;

import java.util.ArrayDeque;
import java.util.Deque;

class MinStack {
    private final Deque<Integer> dataStack; // Store all elements.
    private final Deque<Integer> minStack; // Its top is min element so far.

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        dataStack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int x) {
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            minStack.push(Math.min(minStack.peek(), x));
        }
        dataStack.push(x);
    }

    public void pop() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        minStack.pop();
        dataStack.pop();
    }

    public int top() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        return dataStack.peek();
    }

    public int getMin() {
        if (isEmpty())
            throw new NullPointerException();
        return minStack.peek();
    }

    private boolean isEmpty() {
        return dataStack.isEmpty();
    }
}
/*
constant time: O(1)
for top/top/push, stack normal
for getMin, minStack
-2 0 -3
-2 -2
*/
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */