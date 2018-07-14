package Facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
input: nums
output: list of a,b,c such that a+b+c = 0
 */
public class ThreeSum {
    /*
    e.g. [-1, 0, 1, 2, -1, -4]
    sort to avoid duplicates but when, during binary search
    that is, a + b = -c, find two sum which equals to -c
    we can use HashMap

    [0, 1, 2, -1, -4] target = 1
       ok     ok
     -4, -1, 0, 1, 2, 2
            i    j
     1
     ok ok
     */

    public static void main(String[] args) {
        ThreeSum inst = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = inst.threeSum(nums);
        System.out.println(result);
    }

    List<List<Integer>> result;

    public List<List<Integer>> threeSum(int[] nums) {
        result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            twoSum(nums, -nums[i], i + 1);
        }
        return result;
    }

    private void twoSum(int[] nums, int target, int indesStartFrom) {
        int l = indesStartFrom, r = nums.length - 1;
        List<Integer> answer = new ArrayList<>();
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum < target) {
                l++;
                while (l < r && nums[l] == nums[l - 1]) {
                    l++;
                }
            } else if (sum > target) {
                r--;
                while (l < r && nums[r] == nums[r + 1]) {
                    r--;
                }
            } else {
                answer.add(nums[l]);
                answer.add(nums[r]);
                answer.add(-target);
                result.add(new ArrayList<>(answer));
                answer.clear();
                l++;
                while (l < r && nums[l] == nums[l - 1]) {
                    l++;
                }
                r--;
                while (l < r && nums[r] == nums[r + 1]) {
                    r--;
                }
            }
        }
    }
}
