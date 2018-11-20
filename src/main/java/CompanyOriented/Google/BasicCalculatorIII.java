package CompanyOriented.Google;

import java.util.Stack;

public class BasicCalculatorIII {
    public static void main(String[] args) {
        BasicCalculatorIII inst = new BasicCalculatorIII();
        int result = inst.calculate("2*(5+5*2) / 3");
        System.out.println(result);
    }

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int i = 0, preNum = 0, preSign = 1, result = 0, lastNum = 0;
        boolean waitToMultiply = false, waitToDivide = false;
        while (i < s.length()) {
            char c = s.charAt(i);
            switch (c) {
                case ' ':
                    break;
                case '+':
                    result += preNum * preSign;
                    preNum = 0;
                    preSign = 1;
                    break;
                case '-':
                    result += preNum * preSign;
                    preNum = 0;
                    preSign = -1;
                    break;
                case '(':
                    int l = 1, j = i + 1;
                    while (j < s.length() && l > 0) {
                        if (s.charAt(j) == '(') {
                            l++;
                        } else if (s.charAt(j) == ')') {
                            l--;
                        }
                        j++;
                    }
                    int blockValue = calculate(s.substring(i + 1, j - 1));
                    stack.push(result);
                    result = 0;
                    stack.push(preSign);
                    preSign = 1;
                    break;
                case ')':
                    result += preNum * preSign;
                    preNum = 0;
                    preSign = 1;
                    result *= stack.pop();
                    result += stack.pop();
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
            result += preNum * preSign;
        }
        return result;
    }
}
