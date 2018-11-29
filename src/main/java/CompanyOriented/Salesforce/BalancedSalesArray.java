package CompanyOriented.Salesforce;

/**
 * input: int[] nums
 * balancer is arr[i] such that sum(arr[0..i-1]) == sum(arr[i+1, n-1])
 * output: index of balancer
 */
public class BalancedSalesArray {
    public int findBalancer(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n], postSum = new int[n];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                preSum[i] = nums[i];
            } else {
                preSum[i] = preSum[i - 1] + nums[i];
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                postSum[i] = nums[i];
            } else {
                postSum[i] = postSum[i + 1] + nums[i];
            }
        }

        for (int i = 0; i < n; i++) {
            if (preSum[i] == postSum[i])
                return i;
        }

        return -1;
    }
}
/*
1 3 2 4 5 1
1  4  6  10 15 16
16 15 12 10 6  1
equals
 */