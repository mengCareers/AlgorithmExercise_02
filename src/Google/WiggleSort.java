package Google;

import java.util.Arrays;

/**
 * i =
 * 0 <= 1 >= 2
 * [1, 2, 3, 4, 5, 6]
 * ↑  ↑  ↑  ↑
 * swap  swap
 */
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums); // 1 2 3 4 5 6
        for (int i = 1; i < nums.length - 1; i = i + 2) {
            swap(nums, i, i + 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
