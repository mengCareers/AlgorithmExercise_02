package Amazon;

public class SelectionSort {
    // select the minimum/maximum of  unsorted rest to put in one end of sorted
    // ascending - select minimum of rest to put in last of sorted
    // descending - select maximum of rest to put in front of sorted
    public static void selectionSortDes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int max = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (max < nums[j]) {
                    max = nums[j];
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
    }

    public static void selectionSortAsc(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int min = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (min > nums[j]) {
                    min = nums[j];
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 1, 6, 3, 16, 7, 10};
//        selectionSortDes(nums);
        selectionSortAsc(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
