package CompanyOriented.Google;

import java.util.ArrayList;
import java.util.List;

/**
 * input:  candidates[] unique
 * output: target
 * Start State: sum of numbers in curRes = 0
 * End State: sum of numbers in curRes = target
 * For each step,
 * we can add any num in nums (starting from curStart inclusive, to ensure the uniqueness) if sum of numbers in curRes <= target
 * We will terminate the Recursion if we meet the base case, i.e., End State
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        combinationSumFrom(new ArrayList<>(), 0, 0, result, candidates, target);
        return result;
    }

    private void combinationSumFrom(List<Integer> curRes, int curStart, int curSum, List<List<Integer>> result, int[] candidates, int target) {
        if (curSum == target) {
            result.add(new ArrayList<>(curRes));
            return;
        }
        for (int i = curStart; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (curSum + candidate > target) {
                continue;
            }
            curRes.add(candidate);
            if (curSum + candidate > target) {
                continue;
            }
            combinationSumFrom(curRes, i, curSum + candidate, result, candidates, target);
            curRes.remove(curRes.size() - 1);
        }
    }


}
