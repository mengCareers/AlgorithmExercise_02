package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumUsedMany {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        // corner cases to add

        List<List<Integer>> result = new ArrayList<>();
        combinationSumFrom(candidates, target, result, new ArrayList<>(), 0);

        return result;
    }

    private void combinationSumFrom(int[] candidates, int target, List<List<Integer>> result, List<Integer> temp, int start) {

        if (target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            temp.add(candidates[i]);
            combinationSumFrom(candidates, target - candidates[i], result, temp, i);
            temp.remove(temp.size() - 1);
        }
    }
}
