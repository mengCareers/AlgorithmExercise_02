package 算法竞赛入门经典;

public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch inst = new BinarySearch();
        int[] nums = {0, 1, 2, 3};
        int answer = inst.bSUpperBound(nums, 4);
        System.out.println(answer);
    }

    /**
     * ask for upper bound
     *
     * @param nums
     * @param target
     * @return last index of target in nums + 1 || index to insert
     */
    public int bSUpperBound(int[] nums, int target) {

        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mi = lo + (hi - lo) / 2;
            if (nums[mi] <= target) {
                lo = mi + 1;
            } else {
                hi = mi - 1;
            }
        }

        return lo;
    }

    /**
     * ask for lower bound
     *
     * @param nums
     * @param target
     * @return first index of target in nums || index to insert
     * 第一个大于等于target的位置
     */
    public int bSLowerBound(int[] nums, int target) {

        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mi = lo + (hi - lo) / 2;
            if (nums[mi] < target) {
                lo = mi + 1; // [mi + 1, hi]
            } else {
                hi = mi - 1;
            }
        }

        return lo;
    }


    // 元素空间没有重复值
    // we are maintaining lo all the time
    public int bSStandardBothInclusive(int[] nums, int target) {

        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) { // there is still search space
            int mi = lo + (hi - lo) / 2;
            if (nums[mi] == target) {
                return mi;
            } else if (nums[mi] > target) {
                hi = mi - 1;
            } else { // lo move forward only if num[mi] < target, never move back, until reach the target
                lo = mi + 1;
            }
        }

        return -1;
    }
}
