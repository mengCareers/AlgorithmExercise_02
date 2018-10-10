package CompanyOriented.Google;

/**
 * find the first one who is bigger than its right if exist
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

    public int findPeakElementBS(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mid == 0 || nums[mid] > nums[mid - 1]) {
                if (mid == nums.length - 1 || nums[mid] > nums[mid + 1]) {
                    return mid;
                }
                if (nums[mid] < nums[mid + 1]) {
                    l = mid + 1;
                }
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

}
