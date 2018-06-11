package Google;

import java.util.HashMap;
import java.util.Map;

/**
 * input:  numerator, denominator
 * output: the fraction in string format
 * str = (double)numerator / (double)denominator
 * 2 3 5
 */
public class FractiontoRecurringDecimal {
    public static void main(String[] args) {
        FractiontoRecurringDecimal inst = new FractiontoRecurringDecimal();
        inst.fractionToDecimal(1, 3);
    }

    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder result = new StringBuilder();
        if ((numerator < 0) ^ (denominator < 0)) {
            result.append("-");
        }

        long lnumerator = Long.valueOf(numerator), ldenominator = Long.valueOf(denominator);
        long partInteger = lnumerator / ldenominator;
        result.append(partInteger);
        long remainder = lnumerator % ldenominator;
        if (remainder == 0) {
            return String.valueOf(partInteger);
        }
        result.append(".");
        Map<Long, Integer> remainderToAppearIdx = new HashMap<>();
        while (remainder != 0) {
            if (!remainderToAppearIdx.containsKey(remainder)) {
                remainderToAppearIdx.put(remainder, result.length());
                remainder *= 10;
                result.append(remainder / ldenominator);
                remainder %= ldenominator;
            } else {
                result.insert(remainderToAppearIdx.get(remainder), "(");
                result.append(")");
                break;
            }

        }
        return result.toString();
    }
}
