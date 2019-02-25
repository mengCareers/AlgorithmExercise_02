package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumUsedOnce {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        // corner cases to add

        Arrays.sort(candidates);
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
            if (i > start && candidates[i - 1] == candidates[i]) {
                continue;
            }
            temp.add(candidates[i]);
            combinationSumFrom(candidates, target - candidates[i], result, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
