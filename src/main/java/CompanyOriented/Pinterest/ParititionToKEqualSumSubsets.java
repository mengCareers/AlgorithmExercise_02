package CompanyOriented.Pinterest;

import java.util.Arrays;

/*

 */
public class ParititionToKEqualSumSubsets {

    public static void main(String[] args) {
        ParititionToKEqualSumSubsets inst = new ParititionToKEqualSumSubsets();
        int[] nums = {1, 2, 2, 3, 3, 4, 5};
        System.out.println(inst.canPartitionKSubsets(nums, 4));
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {

        if (nums.length == 0 || k == 0)
            return false;

        int sum = 0;
        for (int num : nums) { // O(n) n is # of elements
            sum += num;
        }
        if (sum % k != 0)
            return false;

        int targetSum = sum / k;

        Arrays.sort(nums); // O(nlogn) n is # of elements

        if (nums[nums.length - 1] > targetSum) {
            return false;
        }

        if (nums[nums.length - 1] > targetSum)
            return false;

        int end = nums.length - 1;
        while (nums[end] == targetSum) {
            end--;
            k--;
        }

        boolean[] used = new boolean[end + 1];
        while (k > 0 && canFindSubsetSumTarget(end, nums, targetSum, used)) {
            k--;
        }

        return k == 0;
    }

    private boolean canFindSubsetSumTarget(int end, int[] nums, int targetSubsetSum, boolean[] used) {

        if (targetSubsetSum < 0) {
            return false;
        }

        if (targetSubsetSum == 0) {
            return true;
        }

        for (int i = end; i >= 0; i--) {
            if (used[i])
                continue;
            used[i] = true;
            if (canFindSubsetSumTarget(i - 1, nums, targetSubsetSum - nums[i], used))
                return true;
            used[i] = false;
        }

        return false;
    }
}
