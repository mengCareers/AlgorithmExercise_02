package CompanyOriented.Amazon;

public class KthLargestElementInArray {

    public int findKthLargest(int[] nums, int k) {
        int pivot = partition(nums, 0, nums.length - 1);
        // now pivot is at the right position
        return 1;
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = l;
        l = l + 1;
        while (l <= r) {
            while (nums[l] < nums[pivot]) {
                l++;
            }
            while (nums[r] > nums[pivot]) {
                r--;
            }
            swap(nums, l, r);
        }
        swap(nums, r, pivot);
        return pivot;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
