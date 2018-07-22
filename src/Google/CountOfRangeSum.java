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

        // corner cases to add

        TreeMap<Long, Long> rangesumToCnt = new TreeMap<>();
        rangesumToCnt.put(0L, 1L);
        long accumuSum = 0;
        int rangeSumCnt = 0;

        for (int j = 0; j < nums.length; j++) {
            accumuSum += nums[j];
            Map<Long, Long> viewRangesumToCnt = rangesumToCnt.subMap(accumuSum - upper, true, accumuSum - lower, true);
            for (Long cnt : viewRangesumToCnt.values()) {
                rangeSumCnt += cnt;
            }
            rangesumToCnt.put(accumuSum, rangesumToCnt.getOrDefault(accumuSum, 0L) + 1);
        }

        return rangeSumCnt;
    }
}
