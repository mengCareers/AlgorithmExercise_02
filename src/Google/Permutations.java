package Google;

import java.util.ArrayList;
import java.util.List;

/**
 * input: distinct
 * output: all possible permutations
 */
public class Permutations {
    public static void main(String[] args) {
        Permutations inst = new Permutations();
        int[] nums = {1, 2, 3};
        System.out.println(inst.permute(nums));
    }

    public List<List<Integer>> permute(int[] nums) {
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
