package 编程之美;

/*
input: nums without order, lenth 2n
output: divide nums[] into two arrays such that each length n, and share the most similar sum
 */
/*
sort them, divide into two from the middle, and swap them if better
e.g. 1 2 5 4 9 3
1 + 2 + 5 + 4 + 9 + 3 = 24 / 2 = 12
target sum 12

 */
public class 数组分割 {

    public static void main(String[] args) {
        数组分割 inst = new 数组分割();
        int[] nums = {1, 2, 3, 5, 7, 8, 9};
        inst.divideArrayEqualSum(nums);
    }

    public void divideArrayEqualSum(int[] nums) {

        int sum = 0, n = nums.length, halfn = n / 2;
        for (int num : nums) {
            sum += num;
        }
        boolean[][] state = new boolean[nums.length][sum / 2 + 1];
        state[0][0] = true;

        for (int k = 0; k < n; k++) {
            for (int i = k > halfn ? halfn : k; i >= 1; i--) {
                for (int s = 0; s <= sum / 2; s++) {
                    if (s >= nums[k] && state[i - 1][s - nums[k]]) {
                        state[i][s] = true;
                    }
                }
            }
        }

        for (int i = sum / 2; i >= 0; i--) {
            if (state[halfn][i]) {
                System.out.println("sum is " + sum);
                System.out.println("sum/2 is " + sum / 2);
                System.out.println("i is " + i);
                System.out.println("minimum delta is " + Math.abs(2 * i - sum));
                break;
            }
        }

    }
}
