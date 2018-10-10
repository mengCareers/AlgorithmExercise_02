package CompanyOriented.Pinterest;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {
    Deque<Integer> stackNum;
    Deque<Integer> stackMin;

    public MinStack() {
        stackNum = new ArrayDeque<>();
        stackMin = new ArrayDeque<>();
    }

    public void push(int x) {
        stackNum.push(x);
        if (stackMin.isEmpty() || stackMin.peek() > x)
            stackMin.push(x);
        else
            stackMin.push(stackMin.peek());
    }

    public int pop() {
        stackMin.pop();
        return stackNum.pop();
    }

    public int top() {
        return stackNum.peek();
    }

    public int peekMin() {
        return stackMin.peek();
    }

    public int popMin() {
        int curMin = stackMin.peek();
        Deque<Integer> stackHelper = new ArrayDeque<>();
        while (stackNum.peek() != curMin) {
            stackHelper.push(stackNum.pop());
            stackMin.pop();
        }
        stackNum.pop();
        stackMin.pop();
        while (!stackHelper.isEmpty()) {
            int curNum = stackHelper.pop();
            stackNum.push(curNum);
            if (stackMin.isEmpty() || stackMin.peek() > curNum)
                stackMin.push(curNum);
            else
                stackMin.push(stackMin.peek());
        }
        return curMin;
    }
}

/*
Max Stack
 */
class MaxStack {
    Deque<Integer> stackNum;
    Deque<Integer> stackMax;

    public MaxStack() {
        stackNum = new ArrayDeque<>();
        stackMax = new ArrayDeque<>();
    }

    public void push(int x) {
        stackNum.push(x);
        if (stackMax.isEmpty() || stackMax.peek() < x)
            stackMax.push(x);
        else
            stackMax.push(stackMax.peek());
    }

    public int pop() {
        stackMax.pop();
        return stackNum.pop();
    }

    public int top() {
        return stackNum.peek();
    }

    public int peekMax() {
        return stackMax.peek();
    }

    public int popMax() {
        int curMax = stackMax.peek();
        Deque<Integer> stackHelper = new ArrayDeque<>();
        while (stackNum.peek() != curMax) {
            stackHelper.push(stackNum.pop());
            stackMax.pop();
        }
        stackNum.pop();
        stackMax.pop();
        while (!stackHelper.isEmpty()) {
            int curNum = stackHelper.pop();
            stackNum.push(curNum);
            if (stackMax.isEmpty() || stackMax.peek() < curNum)
                stackMax.push(curNum);
            else
                stackMax.push(stackMax.peek());
        }
        return curMax;
    }
}