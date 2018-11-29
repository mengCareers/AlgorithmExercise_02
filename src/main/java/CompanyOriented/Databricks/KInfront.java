package CompanyOriented.Databricks;

import java.util.Deque;
import java.util.LinkedList;

/**
 * input: int[] nums, int k
 * <p>
 * output: int[] minElements, minElements[i] is minimum element within k indices prior to i
 */
public class KInfront {

    public static int[] findMinElements(int[] nums, int k) {
        final int[] minElements = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int minElement = Integer.MAX_VALUE;
            for (int j = i - 1; j >= Math.max(0, i - k); j--)
                minElement = Math.min(nums[j], minElement);
            minElements[i] = (minElement == Integer.MAX_VALUE) ? 0 : minElement;
        }

        return minElements;
    }

    public static int[] windowSliding(int[] nums, int k) {
        int n = nums.length, mi = 0;
        int[] minElements = new int[n - k + 1], result = new int[n];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < n; i++) {

            /* Remove the stale. */
            int curStart = i - k + 1;
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1)
                deque.removeFirst();

            /* Remove the smaller and add nums[i]. */
            while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i])
                deque.removeLast();
            deque.addLast(i);

            /* Build minElements[i]. */
            if (i >= k - 1)
                minElements[mi++] = nums[deque.peekFirst()];
        }

        int ri = 1, minInit = Integer.MAX_VALUE;
        while (ri <= k - 1) {
            minInit = Math.max(minInit, nums[ri - 1]);
            result[ri] = minInit;
            ri++;
        }

        mi = 0;
        while (ri < n) {
            result[ri++] = minElements[mi++];
        }

        return result;
    }
}