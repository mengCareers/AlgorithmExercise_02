package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetDup {

    public static void main(String[] args) {
        SubsetDup inst = new SubsetDup();
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> result = inst.subsetsWithDup(nums);
        for (List<Integer> tmp : result) {
            if (tmp.size() == 2) {
                System.out.println(tmp);
            }
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        // corner cases to add

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        subsetsWithDupFrom(0, nums, result, new ArrayList<>());

        return result;
    }

    private void subsetsWithDupFrom(int start, int[] nums, List<List<Integer>> result, List<Integer> curRes) { // subset, named from
        result.add(new ArrayList<>(curRes));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            curRes.add(nums[i]);
            subsetsWithDupFrom(i + 1, nums, result, curRes);
            curRes.remove(curRes.size() - 1);
        }
    }
}
