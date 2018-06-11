package Google;

import java.util.ArrayList;
import java.util.List;

/**
 * nums sorted, lower <= num <= upper
 * output : list of ranges
 * Input: nums = [ 3, 50, 52, 75], lower = 0 and upper = 99,
 * Output: ["2", "4->49", "51->74", "76->99"]
 */
public class MissingRanges {
    public static void main(String[] args) {
        int[] nums = {2147483647};
        MissingRanges inst = new MissingRanges();
        List<String> result = inst.findMissingRanges(nums, -2147483648, 2147483647);
        System.out.println(result);
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {

        long[] newNums = new long[nums.length + 2];
        int ni = 0;
        newNums[ni++] = (long)lower - 1;
        for (int num : nums) {
            newNums[ni++] = num;
        }
        newNums[ni] = (long)upper + 1;

        List<String> ranges = new ArrayList<>();
        StringBuilder range = new StringBuilder();
        long low = 0, up = 0;
        for (int i = 0; i < newNums.length - 1; i++) {
            low = newNums[i] + 1;
            up = newNums[i + 1] - 1;
            if (low == up) {
                ranges.add(String.valueOf(low));
            } else if (low < up) {
                range.append(low).append("->").append(up);
                ranges.add(range.toString());
                range.delete(0, range.length());
            }
        }
        return ranges;

    }
}

/*
[2147483647]
0
2147483647
* */