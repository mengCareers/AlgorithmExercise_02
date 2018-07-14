package Google;

/**
 * s containing +, -, *, /, ' '
 * there is not parenthesis, so no need to use recursion or stack
 * we can do Brute Force simply
 * except for ... + 2 * 3, when + 2 we increase the result by 2, but that's wrong
 * we need to save preNum, and if + 2; fine, or else, result - preNum + 2 * 3
 * DFS
 * start state: curPos = 0
 * state transformation:
 * if
 * + a, result += a, lastNum = a
 * - a, result -= a, lastNum = -a
 * * a, result -= lastNum, result += lastNum * a,
 * / a, result -= lastNum, result += lastNum / a,
 * ' ', do nothing and move on
 * number
 * end state: curPos = s.length()
 */
public class BasicCalculatorII {

    public static void main(String[] args) {
        BasicCalculatorII inst = new BasicCalculatorII();
        int result = inst.calculate("-7*8+9/10");
        System.out.println(result);
    }

    public int calculate(String s) {
        int i = 0, result = 0, preNum = 0, preSign = 1, lastNum = 0;
        boolean waitToMultiply = false, waitToDivide = false;
        while (i < s.length()) {
            char c = s.charAt(i);
            switch (c) {
                case ' ':
                    break;
                case '+':
                    result += preSign * preNum;
                    preNum = 0;
                    preSign = 1;
                    break;
                case '-':
                    result += preSign * preNum;
                    preNum = 0;
                    preSign = -1;
                    break;
                case '*':
                    lastNum = preSign * preNum;
                    waitToMultiply = true;
                    preNum = 0;
                    preSign = 1;
                    break;
                case '/':
                    lastNum = preSign * preNum;
                    waitToDivide = true;
                    preNum = 0;
                    preSign = 1;
                    break;
                default:
                    preNum = preNum * 10 + (c - '0');
                    while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                        i++;
                        preNum = preNum * 10 + (s.charAt(i) - '0');
                    }
                    if (waitToMultiply) {
                        if (i + 1 < s.length() && s.charAt(i + 1) != '*' && s.charAt(i + 1) != '/') {
                            result += lastNum * preNum;
                            preNum = 0;
                        } else {
                            preNum = lastNum * preNum;
                        }
                        waitToMultiply = false;
                    } else if (waitToDivide) {
                        if (i + 1 < s.length() && s.charAt(i + 1) != '*' && s.charAt(i + 1) != '/') {
                            result += lastNum / preNum;
                            preNum = 0;
                        } else {
                            preNum = lastNum / preNum;
                        }
                        waitToDivide = false;
                    }
            }
            i++;
        }
        if (preNum != 0) {
            result += preSign * preNum;
        }
        return result;
    }


}
