package BruteForce;

/* 31. Next Permutation
which rearranges numbers into the lexicographically next greater permutation of numbers
The replacement must be in-place and use only constant extra memory.
 */
/*
list all, get next
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {

        int pivot = -1;

        for (int i = nums.length - 1; i >= 1; i--) {
            if (nums[i - 1] < nums[i]) {
                pivot = i - 1;
                for (int j = nums.length - 1; j > pivot; j--) {
                    if (nums[j] > nums[pivot]) {
                        swap(pivot, j, nums);
                        break;
                    }
                }
                break;
            }
        }

        reverse(pivot + 1, nums.length - 1, nums);
    }

    private void swap(int i, int j, int[] nums) {
        int tp = nums[i];
        nums[i] = nums[j];
        nums[j] = tp;
    }

    private void reverse(int start, int end, int[] nums) {
        while (start < end) {
            swap(start, end, nums);
            start++;
            end--;
        }
    }
}
