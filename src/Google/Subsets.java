package Google;

import java.util.ArrayList;
import java.util.List;

/**
 * distinct integers
 * output: distinct power set
 * e.g. 1 2 3
 * 1   2   2
 * 22  2
 * 2
 * Start State: curPos = 0
 * can pick any number after curPos
 * End(Aim) State: curPos = nums.length #THERE IS NO NEED FOR THAT
 */
public class Subsets {
    public static void main(String[] args) {
        Subsets inst = new Subsets();
        int[] nums = {1, 2, 3};

        System.out.println(inst.subsets(nums));
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsetsFrom(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void subsetsFrom(int[] nums, int curPos, List<Integer> curRes, List<List<Integer>> result) {
        result.add(new ArrayList<>(curRes));

        for (int i = curPos; i < nums.length; i++) {
            curRes.add(nums[i]);
            subsetsFrom(nums, i + 1, curRes, result);
            curRes.remove(curRes.size() - 1);
        }
    }
}
