package Mock;

/* Q:
n sorted numbers given, find the majority number appears more than n / 4 times
 */
public class GetMajority {
    public static void main(String[] args) {
        GetMajority inst = new GetMajority();
        int[] nums = {1, 1, 2, 2, 3, 3, 3, 4, 5, 6, 7};
        int answer = inst.getMajority(nums);
        System.out.println(answer);
    }

    public int getMajority(int[] nums) {
        int len = nums.length;
        int base = len / 4;
        int i = 0;
        while (i < len) {
            if (checkIndex(nums, i))
                return nums[i];
            i += base;
        }
        throw new IllegalArgumentException("Not Found");
    }

    private boolean checkIndex(int[] nums, int i) {
        int si = binarySearch(nums, nums[i], true);
        int ei = binarySearch(nums, nums[i], false);
        return (ei - si > nums.length / 4);
    }

    private int binarySearch(int[] nums, int target, boolean isLowerBound) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mi = lo + ((hi - lo) >> 1);
            if (isLowerBound) {
                if (nums[mi] < target) {
                    lo = mi + 1;
                } else {
                    hi = mi - 1;
                }
            } else {
                if (nums[mi] > target) {
                    hi = mi - 1;
                } else {
                    lo = mi + 1;
                }
            }
        }
        return lo;
    }

}
