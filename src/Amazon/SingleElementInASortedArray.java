package Amazon;

/**
 * input: sorted array every element appear twice except for one appears once
 * output: the single element
 * e.g. 1 1 2 3 3
 */
public class SingleElementInASortedArray {
    /**
     * x ^ x = 0
     * x ^ y = 1
     * 1 ^ 0 = 1,
     * 0 ^ 1 = 1,
     * 1 ^ 1 = 0.
     * 0 ^ 0 = 0
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        int value = 0;
        for (int i = 0; i < nums.length; i++) {
            value = value ^ nums[i];
        }
        return value;
    }
}
