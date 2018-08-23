package Facebook;

/*
Intuitive to loop through array, and time complexity will be O(n)
Since it is sorted, let's take advantage of it, we may apply Binary Search.
e.g. [1, 1, 2, 2, 2, 3], target number is 2
we can do two binary search, one lower-bound and one higher-bound

does it must exist in nums
 */
class CountTargetNumberInSortedArray {

    public static void main(String[] args) {

        CountTargetNumberInSortedArray inst = new CountTargetNumberInSortedArray();
        int[] nums = {1, 1, 2, 2, 2, 4};
        int target = 4;
        System.out.println(inst.countTargetNumber(nums, target));
    }

    public int countTargetNumber(int[] nums, int target) {
        // target exist in [L, R)
        int L = lowerBoundBinarySearch(nums, target);
        int R = higherBoundBinarySearch(nums, target);
        return R - L;
    }

    private int lowerBoundBinarySearch(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private int higherBoundBinarySearch(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

}
