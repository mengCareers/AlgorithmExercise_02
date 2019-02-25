package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationDup {
    public List<List<Integer>> permuteUnique(int[] nums) {

        // corner cases to add

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        permuteUniqueFrom(nums, new ArrayList<>(), result, new int[nums.length]);

        return result;
    }

    private void permuteUniqueFrom(int[] nums, List<Integer> curRes, List<List<Integer>> result, int[] used) {

        if (curRes.size() == nums.length) {
            result.add(new ArrayList<>(curRes));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0) {
                continue;
            }

            curRes.add(nums[i]);
            used[i] = 1;
            permuteUniqueFrom(nums, curRes, result, used);
            curRes.remove(curRes.size() - 1);
            used[i] = 0;
        }
    }
}
