package Google;

/**
 * peak : for an element, if nums[i - 1] < nums[i] > nums[i + 1], then num[i] is peak
 *        for an element, the first nums[i] > nums[i + 1], then nums[i] is peak
 * input: nums
 * output:index of one of the peaks
 * we can iterate nums, for every num we check if it is peak
 * for i - 1 >= 0 && i + 1 < len
 * 1 <= i < len - 1
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        if (nums.length == 2) {
            return nums[0] > nums[1] ? 0 : 1;
        }
        int maxVal = Integer.MIN_VALUE, maxIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxVal < nums[i]) {
                maxVal = nums[i];
                maxIdx = i;
            }
        }
        int peakIndex = -1; // index of the maximum
        for (int i = 1; i < nums.length - 1; i++) {
            if ((nums[i - 1] < nums[i]) && (nums[i] > nums[i + 1])) {
                peakIndex = i;
                break;
            }
        }
        return peakIndex == -1 ? maxIdx : peakIndex;
    }
}
