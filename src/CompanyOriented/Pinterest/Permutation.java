package CompanyOriented.Pinterest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a collection of distinct integers, return all possible permutations.
 */
/* permute
Backtracking
Search tree
e.g.[1, 2, 3]
    1 -2 -3
      -3 -2

    2 -1-3
      -3-1

    3 -1-2
      -2-1
 to make a number used once, use a set visited / boolean[] visited
 */
/* permuteUnique
when nums contains duplicate
permutations are duplicated
we want the unique {1, 1, 2}
1 - 1 - 2
  - 2 - 1

X 1 - 1 - 2 x
    - 2 - 1 x

2 - 1 - 1
  X - 1 - 1 x

 how do we remove duplicates?

 */
public class Permutation {

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<>();
        Arrays.sort(nums);
        List<List<Integer>> finalResult = new ArrayList<>();
        permuteUniqueRecur(nums, finalResult, new ArrayList<>(), new boolean[nums.length]);
        return finalResult;
    }

    private void permuteUniqueRecur(int[] nums, List<List<Integer>> finalResult, List<Integer> currResult, boolean[] used) {

        if (currResult.size() == nums.length) {
            finalResult.add(new ArrayList<>(currResult));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && !used[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            currResult.add(nums[i]);
            used[i] = true;
            permuteUniqueRecur(nums, finalResult, currResult, used);
            used[i] = false;
            currResult.remove(currResult.size() - 1);
        }
    }


    public List<List<Integer>> permute(int[] nums) {

        if (nums == null || nums.length == 0)
            return new ArrayList<>();

        List<List<Integer>> finalResult = new ArrayList<>();
        permuteRecur(nums, finalResult, new ArrayList<>(), new boolean[nums.length]);
        return finalResult;
    }

    private void permuteRecur(int[] nums, List<List<Integer>> finalResult, List<Integer> currResult, boolean[] used) {

        if (currResult.size() == nums.length) {
            finalResult.add(new ArrayList<>(currResult));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue;
            currResult.add(nums[i]);
            used[i] = true;
            permuteRecur(nums, finalResult, currResult, used);
            used[i] = false;
            currResult.remove(currResult.size() - 1);
        }
    }
}
