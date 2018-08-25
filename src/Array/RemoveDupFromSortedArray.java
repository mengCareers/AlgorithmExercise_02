package Array;

public class RemoveDupFromSortedArray {

    public static void main(String[] args) {

        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};

        RemoveDupFromSortedArray inst = new RemoveDupFromSortedArray();
        inst.removeDuplicates(nums);
    }

    public int removeDuplicates(int[] nums) {

        // corner cases to add

        int ptr = 1, i = 1, cnt = 0;
        while (i < nums.length) {
            if (nums[i] == nums[i - 1]) {
                cnt++;
                if (cnt >= 2) {
                    i++;
                    continue;
                }
            } else {
                cnt = 0;
            }
            nums[ptr++] = nums[i++];
        }

        return ptr;
    }
}
