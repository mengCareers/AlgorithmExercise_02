package CompanyOriented.Amazon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * # of unique k-diff pairs : pair(i, j) where i and j are both in arr and absolute dif is k
 */
/*
pair(1, 2) & pair(2, 1) are the same? YES
sort, 1, 1, 3, 4, 5
3 is, (1, 3)
5 is, (3, 5)
 */
public class KDiffPairsInAnArray {

    public static void main(String[] args) {
        KDiffPairsInAnArray inst = new KDiffPairsInAnArray();
        int[] nums = {1, 3, 1, 5, 4};
        int k = 0;
        inst.findPairs(nums, k);
    }

    public int findPairs(int[] nums, int k) {

        if (nums.length == 0 || k < 0) {
            return 0;
        }

        int cntPairs = 0;

        if (k == 0) {
            Arrays.sort(nums);
            int i = 1;
            while (i < nums.length) {
                if (nums[i] == nums[i - 1]) {
                    cntPairs++;
                    i++;
                    while (i < nums.length && nums[i] == nums[i - 1]) {
                        i++;
                    }
                } else {
                    i++;
                }
            }
            return cntPairs;
        }

        Set<Integer> uniqueNums = new HashSet<>();
        for (int num : nums) {
            uniqueNums.add(num);
        }
        for (int num : uniqueNums) {
            int target = num + k;
            if (uniqueNums.contains(target)) {
                cntPairs++;
            }
        }

        return cntPairs;
    }
}
