package Google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * * non-distinct integers
 * * output: distinct power set
 */
public class SubsetsII {

    public static void main(String[] args) {
        SubsetsII inst = new SubsetsII();
        int[] nums = {1, 2, 3};
        inst.subsetsWithDup(nums);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        subsetsWithDupFrom(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void subsetsWithDupFrom(int[] nums, int curPos, List<Integer> curRes, List<List<Integer>> result) {
        result.add(new ArrayList<>(curRes));

        for (int i = curPos; i < nums.length; i++) {
            curRes.add(nums[i]);
            subsetsWithDupFrom(nums, i + 1, curRes, result);
            curRes.remove(curRes.size() - 1);
            i++;
            while (i < nums.length && nums[i] == nums[i - 1])
                i++;
            i--;
        }
    }
}
