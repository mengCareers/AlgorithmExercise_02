package DynamicProgramming;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
goal: maximum # of points can earn
avoid: # of points can earn in total if delete nums[i]
using: # of points can earn in total if use nums[i]

if use nums[i], nums[i] + avoid             if prev + 1 == nums[i]
                nums[i] + max(avoid, using) if prev + 1 != nums[i]
if avoid nums[i], max(avoid, using)
 */

/**
 * state[i][2] // 0 not use, 1 use
 * use nums[i]
 * state[i][0] = nums[i] + state[i - 1][0] if nums[i - 1] + 1 = nums[i]
 * = nums[i] + max (state[i - 1][0], state[i - 1][1])
 * abort nums[i] state[i][1] = max (state[i - 1][0], state[i - 1][1])
 * <p>
 * return max(nums[n - 1][0], nums[n - 1][1])
 */
public class DeleteAndEarn {

    public static void main(String[] args) {

        DeleteAndEarn inst = new DeleteAndEarn();
        int[] nums = {2, 2, 3, 3, 3, 4};
        int result = inst.deleteAndEarnDiscover(nums);
        System.out.println(result);

    }

    public int deleteAndEarnDiscover(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] count = new int[10001];
        List<Integer> list = new LinkedList<>();
        for (int num : nums) {
            if (count[num] == 0) {
                list.add(num);
            }
            count[num]++;
        }
        Collections.sort(list);
        int[] uniqueNums = new int[list.size()];
        int ni = 0;
        for (int num : list) {
            uniqueNums[ni++] = num;
        }

        int n = uniqueNums.length;
        int[][] state = new int[n][2];
        state[0][0] = 0;
        state[0][1] = uniqueNums[0] * count[uniqueNums[0]];
        for (int i = 1; i < n; i++) {
            state[i][0] = Math.max(state[i - 1][0], state[i - 1][1]);
            if (uniqueNums[i] == uniqueNums[i - 1] + 1) {
                state[i][1] = uniqueNums[i] * count[uniqueNums[i]] + state[i - 1][0];
            } else {
                state[i][1] = uniqueNums[i] * count[uniqueNums[i]] + Math.max(state[i - 1][0], state[i - 1][1]);
            }
        }

        return Math.max(state[n - 1][0], state[n - 1][1]);
    }

    public int deleteAndEarn(int[] nums) {

        int[] count = new int[10001];
        for (int num : nums) {
            count[num]++;
        }
        int using = 0, abort = 0, prev = 0, bigger = 0;

        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                bigger = Math.max(using, abort);
                if (prev + 1 != i) {
                    using = count[i] * i + bigger;
                } else {
                    using = count[i] * i + abort;
                }
                abort = bigger;
                prev = i;
            }
        }

        return Math.max(using, abort);
    }

}
