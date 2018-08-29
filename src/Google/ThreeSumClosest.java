package Google;

import java.util.Arrays;

public class ThreeSumClosest {

    public static void main(String[] args) {
        ThreeSumClosest inst = new ThreeSumClosest();
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        inst.threeSumClosest(nums, target);
    }

    public int threeSumClosest(int[] nums, int target) {

        // corner cases to add
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException();
        }

        Arrays.sort(nums);
        int n = nums.length, minDiff = Integer.MAX_VALUE, sumClosest = -1;

        for (int pre = 0; pre < n; pre++) {
            int lo = pre + 1;
            int hi = n - 1;
            while (lo < hi) {
                int curThreeSum = nums[pre] + nums[lo] + nums[hi];
                if (curThreeSum < target) { // too low
                    if (target - curThreeSum < minDiff) {
                        minDiff = target - curThreeSum;
                        sumClosest = curThreeSum;
                    }
                    lo++;
                    continue;
                }
                if (curThreeSum > target) {
                    if (curThreeSum - target < minDiff) {
                        minDiff = curThreeSum - target;
                        sumClosest = curThreeSum;
                    }
                    hi--;
                    continue;
                }
                return curThreeSum;
            }
        }

        return sumClosest;
    }
}
