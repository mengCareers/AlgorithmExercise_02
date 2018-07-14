package Facebook;

/**
 * output[] such that output[i] is equal to
 *  te product of all elements of nums except nums[i]
 *  e.g. 1  2  3  4
 *       24 12 8  6
 * tmp[0]              = 2 * 3 * 4
 * tmp[1] 1            = 1 * 3 * 4
 * tmp[2] 1 * 2        = 2 * 4
 * tmp[3] 1 * 2 * 3    = 6
 */
public class ProductOfArrayExceptSelf {
    /*
    Iteration
    or we can get all product, and /, without division
     */
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int leftProduct = 1, rightProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] = leftProduct;
            leftProduct *= nums[i];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }
        return result;
    }
}
