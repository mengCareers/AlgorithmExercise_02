package CompanyOriented.Facebook;

/**
 * input: sorted then rotated nums without duplicates
 * output: the index of target OR -1
 */

/**
 * e.g. 0 1 2 3 4 5 6
 * 2 3 4 5 6 0 1
 * nums[l] < nums[r], [l, r] sorted
 * check if nums[l] < target < nums[r], if it is, BinarySearch
 * else,
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {1, 3};
        SearchInRotatedSortedArray inst = new SearchInRotatedSortedArray();
        inst.search(nums, 3);

    }

    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (target == nums[l]) {
                return l;
            }
            if (target == nums[m]) {
                return m;
            }
            if (target == nums[r]) {
                return r;
            }
            if (nums[l] < nums[m]) { // [l, m] sorted
                if (nums[l] < target && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else { // [m, r] sorted
                if (nums[m] < target && target < nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }

        }
        return -1;
    }
}
