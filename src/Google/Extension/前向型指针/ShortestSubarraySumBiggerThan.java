package Google.Extension.前向型指针;

public class ShortestSubarraySumBiggerThan {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int s = 4;
        System.out.println(minSubArrayLen(s, nums));
    }


    public static int minSubArrayLen(int s, int[] nums) {

        int i = 0, j = 0, n = nums.length, result = Integer.MAX_VALUE, curSum = 0;

        for (; i < n; i++) {
            while (j <= n && curSum < s) {
                if (j < n)
                    curSum += nums[j];
                j++;
            }
            if (curSum >= s) {
                result = Math.min(result, j - i);
            }
            curSum -= nums[i];
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
