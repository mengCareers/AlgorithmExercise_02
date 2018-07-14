package Amazon;

/**
 * input: nums
 * output: third max number
 * or first max number
 */
/*
keep track of first 3 max
3 var
 */
public class ThirdMaximumNumber {
    public static void main(String[] args) {
        ThirdMaximumNumber inst = new ThirdMaximumNumber();
        int[] nums = {-2147483648, 1, 2};
        inst.thirdMax(nums);

    }

    public int thirdMax(int[] nums) {
        int maxFirst = Integer.MIN_VALUE, maxSecond = Integer.MIN_VALUE, maxThird = Integer.MIN_VALUE;
        boolean isSetMaxFirst = false, isSetMaxSecond = false, isSetMaxThird = false;
        for (int num : nums) {
            if ((isSetMaxFirst && num == maxFirst) || (isSetMaxSecond && num == maxSecond))
                continue;
            if (num >= maxFirst) {
                if (isSetMaxFirst) {
                    if (isSetMaxSecond) {
                        maxThird = maxSecond;
                        isSetMaxThird = true;
                    }
                    maxSecond = maxFirst;
                    isSetMaxSecond = true;
                }
                maxFirst = num;
                isSetMaxFirst = true;
            } else if (num >= maxSecond) {
                if (isSetMaxSecond) {
                    maxThird = maxSecond;
                    isSetMaxThird = true;
                }
                maxSecond = num;
                isSetMaxSecond = true;
            } else if (num >= maxThird) {
                maxThird = num;
                isSetMaxThird = true;
            }
        }
        return isSetMaxThird ? maxThird : maxFirst;
    }
}
