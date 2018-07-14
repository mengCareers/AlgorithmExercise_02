package Amazon;

public class MaximumSumOf3NonOverlappingArray {

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

        int[] result = new int[]{-1, -1, -1};
        int numsLen = nums.length;
        int[] subarraySum = new int[numsLen];
        buildSubarraySum(nums, k, subarraySum);
        int[] xs = getLeftSubarrayStartIndexWithMaxSum(subarraySum);
        int[] ys = getRightSubarrayStartIndexWithMaxSum(subarraySum);

        for (int m = k; m < numsLen - k; m++) {
            if (result[0] == -1 || subarraySum[xs[m - k]] + subarraySum[m] + subarraySum[ys[m + k]] > subarraySum[result[0]] + subarraySum[result[1]] + subarraySum[result[2]]) {
                result[0] = xs[m - k];
                result[1] = m;
                result[2] = ys[m + k];
            }
        }

        return result;
    }

    private void buildSubarraySum(int[] nums, int k, int[] subarraySum) {

        int tmpSum = 0;
        for (int i = 0; i < nums.length; i++) {
            tmpSum += nums[i];
            if (i >= k) {
                tmpSum -= nums[i - k];
            }
            if (i >= k - 1) {
                subarraySum[i - k + 1] = tmpSum;
            }
        }
    }

    private int[] getLeftSubarrayStartIndexWithMaxSum(int[] subarraySum) {

        int[] xs = new int[subarraySum.length];
        int candidate = 0;
        for (int i = 0; i < subarraySum.length; i++) {
            if (subarraySum[candidate] < subarraySum[i]) {
                candidate = i;
            }
            xs[i] = candidate;
        }
        return xs;
    }

    private int[] getRightSubarrayStartIndexWithMaxSum(int[] subarraySum) {

        int[] ys = new int[subarraySum.length];
        int candidate = subarraySum.length - 1;
        for (int i = subarraySum.length - 1; i >= 0; i--) {
            if (subarraySum[candidate] < subarraySum[i]) {
                candidate = i;
            }
            ys[i] = candidate;
        }
        return ys;
    }
}
