package Facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
state: length[i] length of LIS ending at nums[i]
       count[i] # of LIS ending at n
aim state: index of max(length[i])
           sum(count[index])
state transition:
    we may append nums[j] to LIS ending at nums[i], length[j] = length[i] + 1
                                                    count[j] = count[i]
    or we donot, length[j] = 0;
e.g. 1 4 3 5 7 9
     1   2 3
     1   1 2
     135, 145 i.e., += 1
 */
public class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {

        int len = nums.length, result = 0;
        int[] length = new int[len], count = new int[len];
        Arrays.fill(count, 1);

        for (int j = 0; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (nums[i] < nums[j]) {
                    if (length[i] >= length[j]) {
                        length[j] = length[i] + 1;
                        count[j] = count[i];
                    }
                    else if (length[i] == length[j] - 1) {
                        count[j] += count[i];
                    }
                }
            }
        }

        int maxLength = 0;
        for (int i = 0; i < len; i++) {
            if (length[i] > maxLength) {
                maxLength = length[i];
            }
        }
        List<Integer> indicesMaxLength = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (length[i] == maxLength) {
                indicesMaxLength.add(i);
            }
        }
        for (int index : indicesMaxLength) {
            result += count[index];
        }

        return result;
    }
}
