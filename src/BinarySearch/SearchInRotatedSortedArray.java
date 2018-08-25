package BinarySearch;

/*
e.g. 1 2 3 4 5 6
to 3 4 5 6 1 2
       m
[3..5] sorted, but 5 > 2

e.g. 1 2 3 4 5 6
to 5 6 1 2 3 4
       m
[1..4] sorted, but 5 > 1
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {

        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {

            int mi = lo + (hi - lo) / 2;
            if (target == nums[lo]) return lo;
            if (target == nums[mi]) return mi;
            if (target == nums[hi]) return hi;

            if (nums[lo] < nums[mi]) { // sorted [lo, mi]
                if (nums[lo] < target && target < nums[mi]) { // [l < t < m]
                    hi = mi - 1;
                } else {
                    lo = mi + 1;
                }
            } else { // sorted [mi, hi]
                if (nums[mi] < target && target < nums[hi]) { //[m < t < h]
                    lo = mi + 1;
                } else { // t < mi
                    hi = mi - 1;
                }
            }
        }

        return -1;
    }
}
