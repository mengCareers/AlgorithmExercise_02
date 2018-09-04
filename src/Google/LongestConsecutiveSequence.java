package Google;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        LongestConsecutiveSequence inst = new LongestConsecutiveSequence();
        int[] nums = {1, 3, 4};
        System.out.println(inst.longestConsecutive(nums));
    }

    public int longestConsecutive(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>(); // key: num, value: consecutive sequence containing it
        int maxLength = 0;

        for (int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }
            int leftSequenceLength = map.getOrDefault(num - 1, 0);
            int rightSequenceLength = map.getOrDefault(num + 1, 0);
            int sequenceLength = leftSequenceLength + 1 + rightSequenceLength;
            map.put(num, sequenceLength);
            maxLength = Math.max(maxLength, sequenceLength);
            map.put(num - leftSequenceLength, sequenceLength);
            map.put(num + rightSequenceLength, sequenceLength);
        }

        return maxLength;
    }
}
