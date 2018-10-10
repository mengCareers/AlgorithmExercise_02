package CompanyOriented.Google;


import java.util.ArrayList;
import java.util.List;

/**
 * 228. Summary Ranges
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * <p>
 * Example 1:
 * Input:  [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
 * Example 2:
 * Input:  [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 * <p>
 * e.g. 0 2 3 4
 */
public class SummaryRanges {
    public static void main(String[] args) {
        SummaryRanges inst = new SummaryRanges();
        int[] nums = {0, 2, 4, 5, 7, 9};
        List<String> result = inst.summaryRanges(nums);
        System.out.println(result);
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int start = 0, end = 0, i = 0;
        while (i < nums.length) {
            if (i == nums.length - 1 || nums[i] + 1 != nums[i + 1]) {
                if (start == end) {
                    result.add(String.valueOf(nums[start]));
                } else {
                    result.add(nums[start] + "->" + nums[end]);
                }
                start = end = i + 1;
            } else {
                end = i + 1;
            }
            i++;
        }
        return result;
    }
}
