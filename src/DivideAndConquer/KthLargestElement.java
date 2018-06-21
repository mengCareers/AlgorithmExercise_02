package DivideAndConquer;

/**
 * How do we get the Kth largest element without sorting the whole array?
 * It is the feature of Quick Sort that if set a pivot, after partition(), all the elements to the left of pivot are no bigger than pivot, and all the elements to the right of pivot are no smaller than pivot.
 * For the simplicity, we do not calculate the Kth largest but the (nums.length - K)th smallest.
 * That is, pivot is (number of elements to its left + 1)th smallest element in the array. Following the pattern of the Binary Search, we shrink the searching space through two pointers.
 */
public class KthLargestElement {

    public static void main(String[] args) {
        KthLargestElement inst = new KthLargestElement();
        int[] nums = {1, 3, 2, 6, 4, 5};
        System.out.println(inst.findKthLargest(nums, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        int lo = 0, hi = nums.length - 1;
        int pivotPos = 0;
        k = nums.length - k;
        while (lo <= hi) {
            pivotPos = partition(nums, lo, hi);
            if (pivotPos < k) {
                lo = pivotPos + 1;
            } else if (pivotPos > k) {
                hi = pivotPos - 1;
            } else {
                break;
            }
        }
        return nums[pivotPos];
    }

    private int partition(int[] nums, int l, int r) {
        int st = l, gt = r;
        int i = l + 1;
        int pivot = nums[l];
        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, st, i);
                st++;
                i++;
            } else if (nums[i] > pivot) {
                swap(nums, gt, i);
                gt--;
            } else {
                i++;
            }
        }
        return i - 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
