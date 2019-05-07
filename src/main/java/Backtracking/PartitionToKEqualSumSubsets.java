package Backtracking;

import java.util.HashSet;
import java.util.Set;

public class PartitionToKEqualSumSubsets {
    public static void main(String[] args) {
        PartitionToKEqualSumSubsets inst = new PartitionToKEqualSumSubsets();
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        inst.canPartitionKSubsets(nums, 4);
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = getSum(nums);
        if (sum % k != 0) return false;
        else {
            int subsetSum = sum / k;
            return recur(nums, 0, subsetSum, new HashSet<>(), k, 0);
        }
    }

    private int getSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    private boolean recur(int[] nums, int curSum, int targetSum, Set<Integer> visited, int k, int ptrInSubset) {
        if (k == 0) return true;
        else {
            if (targetSum < curSum) return false;
            else if (targetSum == curSum) {
                return recur(nums, 0, targetSum, visited, k - 1, 0);
            } else {
                for (int i = ptrInSubset; i < nums.length; i++) {
                    if (visited.contains(i)) continue;
                    visited.add(i);
                    if (recur(nums, curSum + nums[i], targetSum, visited, k, i + 1)) {
                        return true;
                    }
                    visited.remove(i);
                }
            }
            return false;
        }
    }
}
