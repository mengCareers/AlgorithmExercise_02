package CompanyOriented.Amazon;

public class InsertionSort {
    //  insert unsorted next to proper position of sorted

    public static void insertionSortAsec(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                int temp = nums[i];
                int j = i;
                while (j > 0 && nums[j - 1] > temp) {
                    nums[j] = nums[j - 1];
                    j--;
                }
                nums[j] = temp;
            }
        }
    }


    public static void insertionSortDesc(int[] nums) {
        for (int i = 1; i < nums.length; i++) { // _ 4 3
            if (nums[i - 1] < nums[i]) {
                int temp = nums[i];
                int j = i;
                while (j > 0 && nums[j - 1] < temp) {
                    nums[j] = nums[j - 1];
                    j--;
                }
                nums[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {11, 22, 15, 1, 2, 5, 6};
        insertionSortDesc(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
