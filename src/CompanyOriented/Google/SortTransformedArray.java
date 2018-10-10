package CompanyOriented.Google;

import java.util.Arrays;

/**
 * input : sorted nums,
 * apply form f(x) = ax2 + bx + c to each element x in nums.
 * output:result[] sorted
 * The graph of a quadratic function is symmetric with respect to a vertical line through the turning point. If numbers of nums[] are all to the left of the vertical line or all to the right of the vertical line, we may calculate them without optimization. Or else, we can optimize the calculation.
 *
 * According to the graph below, we define the ends as the the minimum and the maximum of nums[],
 * image
 * it's intuitive that when x gets further from two ends，
 * y gets smaller when a > 0;
 * y gets bigger when a < 0;
 * Since we want all y's, i.e., result[], to be sorted increasingly,
 * we build the result[] from the end when a > 0;
 * we build the result[] from the start when a < 0;
 * How do we build result[]?
 * We can simply compare the values calculated from x's which are pointed by the pointer l, starting from the left end, and the pointer r, starting from the right end, and choose the proper one to put in the result[].
 */
public class SortTransformedArray {

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        return a > 0 ? upwardFormula(nums, a, b, c) : downwardFormula(nums, a, b, c);
    }

    private static int[] upwardFormula(int[] nums, int a, int b, int c) {
        int[] result = new int[nums.length];
        int l = 0, r = nums.length - 1;
        int ri = result.length - 1;
        while (l <= r) {
            int leftEnd = getRes(nums[l], a, b, c);
            int rightEnd = getRes(nums[r], a, b, c);
            if (leftEnd > rightEnd) {
                result[ri] = leftEnd;
                l++;
            } else {
                result[ri] = rightEnd;
                r--;
            }
            ri--;
        }
        return result;
    }

    private static int[] downwardFormula(int[] nums, int a, int b, int c) {
        int[] result = new int[nums.length];
        int l = 0, r = nums.length - 1;
        int ri = 0;
        while (l <= r) {
            int leftEnd = getRes(nums[l], a, b, c);
            int rightEnd = getRes(nums[r], a, b, c);
            if (leftEnd > rightEnd) {
                result[ri] = rightEnd;
                r--;
            } else {
                result[ri] = leftEnd;
                l++;
            }
            ri++;
        }
        return result;
    }

    private static int getRes(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
