package Backtracking;

public class ClumsyFactorial {
    public static void main(String[] args) {
        ClumsyFactorial inst = new ClumsyFactorial();
        System.out.println(inst.clumsy(10));
    }

    public int clumsy(int N) {
        int[] nums = buildNums(N);
        return clumsyUtil(nums);
    }

    private int[] buildNums(int N) {
        int[] nums = new int[N];
        int index = N - 1;
        for (int i = 1; i <= N; i++) {
            nums[index--] = i;
        }
        return nums;
    }

    public int clumsyUtil(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return nums[0] * nums[1];
        if (nums.length == 3) return nums[0] * nums[1] / nums[2];
        return nums[0] * nums[1] / nums[2] + nums[3] + eval(nums, 4);
    }

    private int eval(int[] nums, int i) {
        if (i >= nums.length) return 0;
        if (i + 1 >= nums.length) return -nums[i];
        if (i + 2 >= nums.length) return -nums[i] * nums[i + 1];
        if (i + 3 >= nums.length) return -nums[i] * nums[i + 1] / nums[i + 2];
        return -nums[i] * nums[i + 1] / nums[i + 2] + nums[i + 3] + eval(nums, i + 4);
    }
}
