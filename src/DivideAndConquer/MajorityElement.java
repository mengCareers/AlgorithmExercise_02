package DivideAndConquer;

/**
 * majority element: appears more than n/2 times
 * m1. hashing
 * m2.if appears > n/2, what will happen
 * dif --
 * same++
 * e.g.[2,2,1,1,1,2,2]
 * >0, majority element; we dk 2 is major in the beginning
 * 2 1
 * # what about abc
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int key = nums[0], frequency = 0;
        for (int i = 0; i < nums.length; i++) {
            if (frequency == 0) {
                key = nums[i];
                frequency = 1;
            }
            if (nums[i] == key) {
                frequency++;
            } else {
                frequency--;
            }
        }
        return key;
    }
}
