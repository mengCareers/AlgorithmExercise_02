package CompanyOriented.Pinterest;

/*
Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {

        long ldividend = (long) dividend, ldivisor = (long) divisor;

        boolean signNegative = false;
        if (ldividend < 0) {
            signNegative = !signNegative;
            ldividend = -ldividend;
        }
        if (ldivisor < 0) {
            signNegative = !signNegative;
            ldivisor = -ldivisor;
        }

        long result = divideRecur(ldividend, ldivisor);

        if (result > Integer.MAX_VALUE && !signNegative) {
            result = Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            result = Integer.MIN_VALUE;
        }

        return signNegative ? (int) -result : (int) result;
    }

    private long divideRecur(long dividend, long divisor) {

        if (dividend < divisor)
            return 0;

        long sum = divisor, quotient = 1;
        while (sum + sum < dividend) {
            sum += sum;
            quotient += quotient;
        }

        return quotient + divideRecur(dividend - sum, divisor);
    }
}
