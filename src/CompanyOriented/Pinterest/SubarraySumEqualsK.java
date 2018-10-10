package CompanyOriented.Pinterest;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>(); // Key: cummulative sum starting at 0, value: frequency of sum.
        map.put(0, 1);
        int cummulativeSum = 0, count = 0;

        for (int num : nums) {
            cummulativeSum += num;
            count += map.getOrDefault(cummulativeSum - k, 0);
            map.put(cummulativeSum, map.getOrDefault(cummulativeSum, 0) + 1);
        }

        return count;
    }
}
