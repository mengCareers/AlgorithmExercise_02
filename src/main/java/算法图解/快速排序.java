package 算法图解;

public class 快速排序 {
    public static void main(String[] args) {
        快速排序 inst = new 快速排序();
        int[] nums = new int[]{6, 5, 3, 1, 2,1, 2, 7, 8, 4};
        inst.quickSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public void quickSort(int[] nums) {
        quickSortUtil(nums, 0, nums.length - 1);
    }

    private void quickSortUtil(int[] nums, int s, int e) {
        if (s >= e) {
            return;
        }
        int pivotIndex = partitionPivotS(nums, s, e);
        quickSortUtil(nums, s, pivotIndex - 1);
        quickSortUtil(nums, pivotIndex + 1, e);
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private int partitionPivotE(int[] nums, int s, int e) {
        int pivot = nums[e], wall = s - 1;
        for (int i = s; i < e; i++) {
            if (nums[i] < pivot) {
                wall++;
                swap(nums, wall, i);
            }
        }
        wall++;
        swap(nums, wall, e);
        return wall;
    }

    private int partitionPivotS(int[] nums, int s, int e) {
        int pivot = nums[s], wall = e + 1;
        for (int i = e; i > s; i--) {
            if (nums[i] > pivot) {
                wall--;
                swap(nums, wall, i);
            }
        }
        wall--;
        swap(nums, wall, s);
        return wall;
    }

    private int partitionPivotSTwoWalls(int[] nums, int s, int e) {
        int pivot = nums[s], lwall = s - 1, rwall = e + 1;
        int i = s + 1;
        while (i < rwall) {
            if (nums[i] < pivot) {
                lwall++;
                swap(nums, lwall, i);
                i++;
            } else if (nums[i] > pivot) {
                rwall--;
                swap(nums, rwall, i);
            } else {
                i++;
            }
        }
        return i - 1;
    }
}

