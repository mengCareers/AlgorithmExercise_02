package Facebook;

/*
    12
    34
    __
    48
   360
   ___
   408
 */
public class MultiplyStrings {
    public static void main(String[] args) {
        MultiplyStrings inst = new MultiplyStrings();
        String ans = inst.multiply("12", "345");
        System.out.println(ans);
    }

    public String multiply(String num1, String num2) {

        int[] result = new int[num1.length() + num2.length()];
        StringBuilder finalResult = new StringBuilder();
        int tmpMulti = 0, tmpSum = 0, p1 = 0, p2 = 0;

        for (int j = num2.length() - 1; j >= 0; j--) {
            for (int i = num1.length() - 1; i >= 0; i--) {
                p1 = i + j;
                p2 = i + j + 1;
                tmpMulti = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                tmpSum = tmpMulti + result[p2];
                result[p1] += tmpSum / 10;
                result[p2] = tmpSum % 10;
            }
        }

        for (int num : result) {
            if (finalResult.length() == 0 && num == 0) {
                continue;
            }
            finalResult.append(num);
        }

        return finalResult.length() == 0 ? "0" : finalResult.toString();
    }

    public String multiply_00(String num1, String num2) {

        long tmpMulti = 0, sum = 0;
        int ti = 0;
        double k = 0;
        long[] toAdd = new long[num2.length()];

        for (int j = num2.length() - 1; j >= 0; j--) {
            tmpMulti = 0;
            for (int i = 0; i < num1.length(); i++) {
                tmpMulti = tmpMulti * 10 + (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
            toAdd[ti++] = tmpMulti * (long) Math.pow(10, k++);
        }
        for (long num : toAdd) {
            sum += num;
        }

        return String.valueOf(sum);
    }
}
