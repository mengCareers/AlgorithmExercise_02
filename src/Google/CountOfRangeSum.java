package Google;

import java.util.Map;
import java.util.TreeMap;

/*
TreeMap
return NavigableMap<K,V>
subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive)
Returns a view of the portion of this map whose keys range from fromKey to toKey.
 */
public class CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {

        TreeMap<Long, Long> sumToCnt = new TreeMap<>();
        sumToCnt.put(0L, 1L);
        long accumuSum = 0, cntRangeSum = 0;

        for (int j = 0; j < nums.length; j++) {
            accumuSum += nums[j];
            Map<Long, Long> viewSumToCnt = sumToCnt.subMap(accumuSum - upper, true, accumuSum - lower, true);
            for (Long cnt : viewSumToCnt.values()) {
                cntRangeSum += cnt;
            }
            sumToCnt.put(accumuSum, sumToCnt.getOrDefault(accumuSum, 0L) + 1);
        }

        return (int) cntRangeSum;
    }

    public static void main(String[] args) {
        CountOfRangeSum inst = new CountOfRangeSum();
        int[] nums = {1, -2, 2};
        int lower = -2;
        int upper = 2;
        System.out.println(inst.countRangeSum(nums, lower, upper));
    }
}
