package Facebook;

public class SplitArrayLargestSum {

    public int splitArray(int[] nums, int m) {

        int minVal = Integer.MIN_VALUE, maxVal = 0;
        for (int num : nums) {
            minVal = Math.max(minVal, num);
            maxVal += num;
        }

        while (minVal < maxVal) {
            int mid = minVal + (maxVal - minVal) / 2;
            if (canSplit(mid, nums, m)) {
                maxVal = mid;
            } else {
                minVal = mid + 1;
            }
        }

        return minVal;
    }

    private boolean canSplit(int upperBoundSubarraySum, int[] nums, int m) {

        int curSubarraySum = 0, cntSubarray = 1;
        for (int num : nums) {
            curSubarraySum += num;
            if (curSubarraySum > upperBoundSubarraySum) {
                cntSubarray++;
                curSubarraySum = num;
                if (cntSubarray > m) {
                    return false;
                }
            }
        }

        return true;
    }
}
