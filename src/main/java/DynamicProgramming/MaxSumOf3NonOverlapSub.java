package DynamicProgramming;

public class MaxSumOf3NonOverlapSub {
    public static void main(String[] args) {
        MaxSumOf3NonOverlapSub inst = new MaxSumOf3NonOverlapSub();
        int[] nums = {1, 2, 1, 2, 6, 7, 5, 1};

        int[] res = inst.maxSumOfThreeSubarrays(nums, 2);
        System.out.println(res[0] + " " + res[1] + " " + res[2]);
    }

    private int[] result;
    private int[] sumSubarray;
    private int k;

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        result = new int[3];
        this.k = k;
        sumSubarray = buildSumSubarray(nums);
        System.out.println(maxSumOfSubarrays(nums, 3, 0, result));
        return result;
    }

    private int[] buildSumSubarray(int[] nums) {
        int[] sumSubarray = new int[nums.length - k + 1];
        int subarrayStart = 0;
        for (int i = 0; i < nums.length && subarrayStart < sumSubarray.length; i++) {
            if (i < k - 1) {
                sumSubarray[subarrayStart] += nums[i];
            } else if (i == k - 1) {
                sumSubarray[subarrayStart++] += nums[i];
            } else {
                sumSubarray[subarrayStart] = sumSubarray[subarrayStart - 1] + nums[i] - nums[subarrayStart - 1];
                subarrayStart++;
            }
        }
        return sumSubarray;
    }

    private int maxSumOfSubarrays(int[] nums, int numSubarrays, int startIndex, int[] result) {
        if (numSubarrays == 0) { // Base case.
            return 0;
        }
        int maxSum = 0, maxIndex = -1;
        for (int i = startIndex; i + k * (numSubarrays - 1) < nums.length; i++) {
            if (i + k > sumSubarray.length) break;
            int curSum = sumSubarray[i] + maxSumOfSubarrays(nums, numSubarrays - 1, i + k, result);
            if (maxSum < curSum) {
                maxSum = curSum;
                maxIndex = i;
            }
        }
        if (maxIndex != -1) {
            result[Math.abs(numSubarrays - 3)] = maxIndex;
        }
        return maxSum;
    }
}
