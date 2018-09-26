package CompanyOriented.Amazon;

import java.util.Arrays;

/*
sort
a counter

 */
public class SetMismatch {
    public static void main(String[] args) {
        SetMismatch inst = new SetMismatch();
        inst.findErrorNums(new int[]{5, 3, 6, 1, 5, 4, 7, 8});
    }

    public int[] findErrorNums(int[] nums) {
        Arrays.sort(nums);
        int[] result = new int[2];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                result[0] = nums[i];
                break;
            }
        }
        int ni = 0;
        for (int i = 1; i <= nums.length; i++) {
            if (ni + 1 < nums.length && nums[ni] != i && nums[ni + 1] != i) {
                result[1] = i;
                return result;
            }
            ni++;
        }
        result[1] = nums.length;
        return result;
    }
}
