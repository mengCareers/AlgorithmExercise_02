package CompanyOriented.Google;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * input: nums, window size k
 * output: maximums of all possible windows
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7      3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Deque:
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindowDebug(int[] nums, int k) {

        if (nums == null || nums.length == 0 || nums.length < k) {
            return nums;
        }

        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(0);
        int i = 1, ri = 0;
        while (i < nums.length) {
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) { // remove expired from window start, k = 3, i = 3
                deque.removeFirst();
            }
            if (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) { // remove impossible maximum from window end,
                deque.removeLast();
            }
            deque.add(i);
            if (i >= k - 1) { // time to return, k = 3, i = 2
                result[ri++] = nums[deque.peekFirst()];
            }
            i++;
        }

        return result;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || nums == null) {
            return nums;
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length - (k - 1)];
        int ri = 0;
        for (int i = 0; i < nums.length; i++) { // i ought to be the end
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.add(i);
            if (i >= k - 1) {
                result[ri++] = nums[deque.peek()];
            }
        }
        return result;
    }
}
