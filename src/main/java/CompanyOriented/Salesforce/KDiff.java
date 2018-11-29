package CompanyOriented.Salesforce;

import java.util.Arrays;

/**
 * input: int[] arr, int targetDiff
 * output: # of pairs of elements in arr that have a diff == targetDiff
 */
public class KDiff {
    public static int countKDiffPairs(int[] nums) {
        Arrays.sort(nums);
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= Math.min(nums.length - 1, i + 2); j++) {
                if (nums[j] == nums[i] + 2) {
                    count++;
                }
            }
        }

        return count;
    }

}

/*
e.g. 1 5 3 4 2
1 2 3 4 5

1,3
2,4
3,5

sort, see (n + 2) is in set or not
 */