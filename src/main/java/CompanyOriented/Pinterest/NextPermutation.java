package CompanyOriented.Pinterest;

/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
The replacement must be in-place and use only constant extra memory.
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {

        int n = nums.length;
        int firstInc = n - 2; // The first index which is smaller than its next element.

        while (firstInc >= 0 && nums[firstInc] >= nums[firstInc + 1]) {
            firstInc--;
        }

        if (firstInc != -1) { // firstInc == -1 if nums is decreasing.
            for (int i = n - 1; i > firstInc; i--)
                if (nums[i] > nums[firstInc]) {
                    swap(nums, firstInc, i);
                    break;
                }
        }

        reverse(nums, firstInc + 1, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
