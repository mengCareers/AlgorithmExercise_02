package 编程之美;

/**
 * 0.25
 * 以分母最小的分数形式来表示小数
 X = 0.2(5)
 10X = 2.(5) = 2 + 0.(5)
 X = (2 + 0.(5))/10

 Y = O.(5)
 10Y = 5.(5) = 5 + 0.(5) = 5 + Y
 9Y = 5
 Y = 5/9

 X = (2 + Y) / 10
   = (2 + 5 / 9) / 10
   = (2 * 9 + 5 ) / 10 * 9
 */
/*
一个整数和一个纯小数之和
一个纯小数
 */
public class 以分母最小的分数形式来表示小数 {

    public String toRepresentDecimalPrecisely(String decimalStr) {

        // corner cases special cases to add
        StringBuilder decimalSB = new StringBuilder(decimalStr);
        StringBuilder result = new StringBuilder();
        int numerator, denumerator, gcd, decimalLen, numInteger;
        String pureDecimalStr;

        if (decimalSB.indexOf("(") == -1) { // no loop 0.25
            pureDecimalStr = decimalSB.substring(decimalSB.indexOf(".") + 1);
            numerator = Integer.valueOf(pureDecimalStr); // 25
            decimalLen = pureDecimalStr.length();
            denumerator = (int) Math.pow(10, decimalLen); // 100
        } else {
            String circulated = decimalStr.substring(decimalStr.indexOf("(") + 1, decimalStr.indexOf(")")); // 5
            String noncirculated = decimalStr.substring(decimalStr.indexOf(".") + 1, decimalStr.indexOf("(")); // 2
            int factor = (int) Math.pow(10, noncirculated.length()); // 10
            numerator = Integer.valueOf(noncirculated) * (factor - 1) + Integer.valueOf(circulated);
            denumerator = factor * (factor - 1);
        }
        gcd = getGCD(numerator, denumerator);
        result.append(numerator / gcd).append("/").append(denumerator / gcd);
        return result.toString();
    }

    public static void main(String[] args) {
        以分母最小的分数形式来表示小数 inst = new 以分母最小的分数形式来表示小数();
        String result = inst.toRepresentDecimalPrecisely("0.2(5)");
        System.out.println(result);
    }

    public String toRepresentDecimalPrecisely_vq(String decimalStr) {
        // assume integer part is 0

        StringBuilder result = new StringBuilder();
        String denominator, numerator;
        int valGCD;

        if (!decimalStr.contains("(")) {
            numerator = decimalStr.substring(decimalStr.indexOf(".") + 1); // 25
            denominator = String.valueOf((int) Math.pow(10, numerator.length()));
        } else {
            // X = 0.2(5), 10X = 2.(5) = 2 + 0.(5); X = (2 + 0.(5)) / 10
            // Y = 0.(5), 10Y = 5.(5) = 5 + 0.5(5); 9Y = 5; Y = 5 / 9
            // X = (2 + 5 / 9) / 10 = (2 * 9 + 5) / 10 * 9
            String circulated = decimalStr.substring(decimalStr.indexOf("(") + 1, decimalStr.indexOf(")")); // 5
            String noncirculated = decimalStr.substring(decimalStr.indexOf(".") + 1, decimalStr.indexOf("(")); // 2
            int factor = (int) Math.pow(10, noncirculated.length()); // 10
            numerator = String.valueOf(Integer.valueOf(noncirculated) * (factor - 1) + Integer.valueOf(circulated));
            denominator = String.valueOf(factor * (factor - 1));

        }

        int valNumerator = Integer.valueOf(numerator);
        int valDenominator = Integer.valueOf(denominator);
        valGCD = getGCD(valNumerator, valDenominator);
        result.append(valNumerator / valGCD).append("/").append(valDenominator / valGCD);

        return result.toString();
    }

    private int getGCD(int a, int b) {
        if (a > b) {
            return getGCD(b, a);
        }
        if (a == 0) {
            return b;
        }
        return getGCD(a, b - a);
    }
}


