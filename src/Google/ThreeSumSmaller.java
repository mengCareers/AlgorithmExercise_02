package Google;

import java.util.Arrays;

/**
 * output : the number of index triplet i, j, k with 0 <= i < j < k < n
 * that satisfies nums[i] + nums[j] + nums[k] < target.
 */
public class ThreeSumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int cnt = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            cnt += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return cnt;
    }

    private static int twoSumSmaller(int[] nums, int minIdx, int target) {
        int cnt = 0;
        for (int i = nums.length - 1; i > minIdx; i--) {
            int maxNumsJ = target - nums[i];
            for (int j = i - 1; j >= minIdx; j--) {
                if (nums[j] < maxNumsJ) {
                    cnt += j - minIdx + 1;
                    break;
                }
            }
        }
        return cnt;
    }
}
