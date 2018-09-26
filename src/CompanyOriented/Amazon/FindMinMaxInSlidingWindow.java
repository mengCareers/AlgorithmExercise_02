package CompanyOriented.Amazon;

import java.util.ArrayDeque;
import java.util.Deque;

/*
if we iterate each sliding window, exactly O is (numsLength - size + 1) * size, O(n^2)
OR
if we spend more space to save time, i.e., using min heap, exactly O is (logn + logn) * (numsLength - size + 1), 0(nlogn)
OR
we can use deque, which can be manipulated head and tail, its feature is natural for sliding window
        exactly 0 is (1 + 1 + 1) * (numsLength - size + 1), 0(n)
 */
/*
deque-way design:
deque store candidate for current and future sliding window
    candidate must satisfy : if currently i,
        - within the possible index range [i - size + 1, i]
        - <= / >= nums[i]
in this way, iterate nums and add nums[i] to deque, maintain:
    head of deque is the minimum/maximum of current sliding window
we store index rather than value for comparison
 */
public class FindMinMaxInSlidingWindow {

    public static void main(String[] args) {
        FindMinMaxInSlidingWindow inst = new FindMinMaxInSlidingWindow();
        int[] nums = {4, 2, 12, 11, -5};
        int[] result = inst.findMinimumInSlidingWindow(2, nums);
        inst.printResult(result);
        result = inst.findMaximumInSlidingWindow(2, nums);
        inst.printResult(result);
    }

    private void printResult(int[] result) {
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public int[] findMinimumInSlidingWindow(int size, int[] nums) {

        int[] result = new int[nums.length - size + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        int ri = 0;

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - size + 1) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= size - 1) {
                result[ri++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }


    public int[] findMaximumInSlidingWindow(int size, int[] nums) {

        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums.length - size + 1];
        int ri = 0;

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - size + 1) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= size - 1) {
                result[ri++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }
}

