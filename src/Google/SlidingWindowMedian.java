package Google;

import java.util.Collections;
import java.util.PriorityQueue;

public class SlidingWindowMedian {

    public static void main(String[] args) {
        SlidingWindowMedian inst = new SlidingWindowMedian();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        double[] result = inst.medianSlidingWindow(nums, k);
        for (double tmp : result) {
            System.out.print(tmp + " ");
        }
    }

    PriorityQueue<Long> minPQ; // bigger half, always poll min
    PriorityQueue<Long> maxPQ; // smaller half, always poll max

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        int ri = 0;
        minPQ = new PriorityQueue<>();
        maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < nums.length; i++) { // i as right end of the window
            if (i < k - 1) {
                addNum(nums[i]);
                continue;
            }
            if (i == k - 1) {
                addNum(nums[i]);
                result[ri++] = findMedian();
                continue;
            }
            if (minPQ.contains((long)nums[i - k])) {
                minPQ.remove((long) (nums[i - k]));
            } else {
                maxPQ.remove((long) (nums[i - k]));
            }
            addNum(nums[i]);
            result[ri++] = findMedian();
        }
        return result;
    }

    private void addNum(long num) {
        minPQ.add(num);
        maxPQ.add(minPQ.poll());
        if (minPQ.size() < maxPQ.size()) {
            minPQ.add(maxPQ.poll());
        }
    }

    private double findMedian() {
        if (minPQ.size() > maxPQ.size()) {
            return minPQ.peek();
        }
        return (minPQ.peek() + maxPQ.peek()) / 2.0;
    }
}
