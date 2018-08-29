package Array;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {

    public static void main(String[] args) {
        FractionToRecurringDecimal inst = new FractionToRecurringDecimal();
        System.out.println(inst.fractionToDecimal(4, 333));
    }

    public String fractionToDecimal(int numerator, int denominator) {

        Map<Long, Integer> valToPos = new HashMap<>();
        StringBuilder result = new StringBuilder();
        long num = numerator, denom = denominator;

        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
            num = Math.abs(num);
            denom = Math.abs(denom);
            result.append("-");
        }

        result.append(num / denom);

        long partRest = num % denom;
        if (partRest == 0) {
            return result.toString();
        }

        result.append(".");

        while (partRest != 0) {
            if (valToPos.containsKey(partRest)) {
                result.insert(valToPos.get(partRest), "(");
                result.append(")");
                break;
            }
            valToPos.put(partRest, result.length());
            partRest *= 10;
            long partResult = partRest / denom;
            result.append(partResult);
            partRest = partRest % denom;
        }

        return result.toString();
    }
}
