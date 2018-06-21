package Implementation;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort inst = new QuickSort();
        int[] nums = {4, 5, 2, 3, 8, 9, 1};
        inst.quickSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public void quickSort(int[] nums) {
        quickSortUtil(nums, 0, nums.length - 1);
    }

    private void quickSortUtil(int[] nums, int l, int r) {
        if (l <= r) {
            int pivotPos = partition(nums, l, r);
            quickSortUtil(nums, l, pivotPos - 1);
            quickSortUtil(nums, pivotPos + 1, r);
        }
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[l];
        int st = l, gt = r;
        int i = l;
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

    private int partitionLo(int[] nums, int l, int r) {
        int wall = r + 1;
        int pivot = nums[l];
        for (int i = r; i >= l + 1; i--) {
            if (nums[i] > pivot) {
                wall--;
                swap(nums, i, wall);
            }
        }
        swap(nums, wall - 1, l);
        return wall - 1;
    }

    private int partitionHi(int[] nums, int l, int r) {
        int wall = l - 1;
        int pivot = nums[r];
        for (int i = l; i < r; i++) {
            if (nums[i] < pivot) {
                wall++;
                swap(nums, i, wall);
            }
        }
        swap(nums, wall + 1, r);
        return wall + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
