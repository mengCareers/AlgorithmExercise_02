package 剑指offer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MaximumOfQ {

    public static void main(String[] args) {
        MaximumOfQ inst = new MaximumOfQ();
        inst.offer(1);
        System.out.println(inst.max());
        inst.offer(3);
        System.out.println(inst.max());
        inst.offer(2);
        System.out.println(inst.max());
        inst.offer(4);
        System.out.println(inst.max());
    }

    int currentIndex;
    Deque<InternalData> maxs;
    Deque<InternalData> data;

    public MaximumOfQ() {

        currentIndex = 0;
        maxs = new ArrayDeque<>();
        data = new ArrayDeque<>();
    }

    int max() {

        return maxs.peekFirst().num;
    }

    void offer(int x) {

        while (!maxs.isEmpty() && x >= maxs.peekLast().num) {
            maxs.pollLast();
        }
        InternalData internalData = new InternalData(x, currentIndex);
        maxs.offerLast(internalData);
        data.offerLast(internalData);
        currentIndex++;

    }

    int poll() {

        if (maxs.peekFirst().index == data.peekFirst().index) {
            maxs.pollFirst();
        }
        return data.pollFirst().num;
    }

    class InternalData {
        int num;
        int index;

        public InternalData(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }
}
