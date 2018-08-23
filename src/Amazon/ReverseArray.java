package Amazon;

public class ReverseArray {

    public static void reverse(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        reverse(nums);
        for (int num : nums)
            System.out.print(num + " ");
    }
}
