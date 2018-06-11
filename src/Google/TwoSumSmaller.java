package Google;

import java.util.Arrays;

/**
 * Given a num array,
 * find the number of index pairs i, j with 0≤i<j<n
 * that satisfy the condition nums[i] + nums[j] < target
 */
public class TwoSumSmaller {
    public int twoSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int cnt = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            int maxNumsJ = target - nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < maxNumsJ) {
                    cnt += j + 1;
                    break;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        TwoSumSmaller inst = new TwoSumSmaller();
        int ans = inst.twoSumSmaller(nums, 9);
        System.out.println(ans);
    }
}
