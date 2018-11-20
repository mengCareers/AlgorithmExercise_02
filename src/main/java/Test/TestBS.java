package Test;

public class TestBS {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(bs(nums, 6));
    }

    public static int bs(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        // [ l, r + 1]
        // r = m - 1
        // l = m + 1
        // if (== target) return m
        // 1 2 3 4 5 / 4
        while (l < r) {
            int m = (l + r) / 2; // x
            if (nums[m] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
//            if (nums[m] > target) {
//                r = m - 1;
//            } else {
//                l = m + 1;
//            }
        }
        if (nums[l] == target) {
            return l;
        }
        return -1;
    }
}
