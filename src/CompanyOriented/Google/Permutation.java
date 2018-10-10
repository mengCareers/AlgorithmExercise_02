package CompanyOriented.Google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * input : distinct int
 * output: possible permutations
 * e.g. 123
 * 1 2 3
 * 1 3 2
 * 2 1 3
 * 2 3 1
 * 3 1 2
 * 3 2 1
 * If we define List<Integer> curRes as one possible permutation,
 * Start State: curRes.size() = 0
 * End State: curRes.size() = nums.length
 * For each step,
 * we can append any one from nums to curRes, and move on
 * if we did not add it before
 * We terminate the recursion until we meet the base case, i.e., the End State
 */
public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        permuteFrom(nums, new ArrayList<>(), result);
        return result;
    }

    private void permuteFrom(int[] nums, List<Integer> curRes, List<List<Integer>> result) {
        if (curRes.size() == nums.length) {
            result.add(new ArrayList<>(curRes));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (isAdded(curRes, nums[i])) {
                continue;
            }
            curRes.add(nums[i]);
            permuteFrom(nums, curRes, result);
            curRes.remove(curRes.size() - 1);
        }
    }

    private boolean isAdded(List<Integer> curRes, int num) {
        for (int tmp : curRes) {
            if (tmp == num) {
                return true;
            }
        }
        return false;
    }
}
