package CompanyOriented.Google;

import java.util.Stack;

/*
 * to evaluate a expression string containing (, ), +, -, non-negative integers, empty space
 * e.g. 1 + 1
 * number of empty spaces should not matter
 * so we escape all the spaces
 * 2+(1+1)
 * using stack | 2
 * if number, accumulate
 * if operator, record sign
 * if (, push the accumulated result and sign
 * if ), pop

 * 2 +
 *
 * Start State: curPos = 0
 * State Transfer:
 *   If number
 *   If calculator
 *   If ( or )
 *   If ' '
 * End State: curPos = s.length()
 * */
/*
eval --> Keep the current value we have got
multed --> the previous number before this current position(either negative or positive)
path --> the path to current
pos --> The current start position in this recursion.
* */
public class BasicCalculator {

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int i = 0, preNum = 0, preSign = 1, result = 0;
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
                default:
                    int tmp = c - '0';
                    preNum = preNum * 10 + tmp;
            }
            i++;
        }
        if (preNum != 0) {
            result += preNum * preSign;
        }
        return result;
    }

    public static void main(String[] args) {
        BasicCalculator inst = new BasicCalculator();
        inst.calculate("1 + 1");
    }

    int i = 0;

    public int calculateRecursion(String s) {
        i = 0;
        s = "(" + s + ")";
        return calculateParenthesis(s);
    }

    private int calculateParenthesis(String s) {
        int result = 0;
        int preSign = 1;
        int tmpNum = 0;
        while (i < s.length()) {
            switch (s.charAt(i)) {
                case ' ':
                    i++;
                    break;
                case '+':
                    result += preSign * tmpNum;
                    preSign = 1;
                    tmpNum = 0;
                    i++;
                    break;
                case '-':
                    result += preSign * tmpNum;
                    preSign = -1;
                    tmpNum = 0;
                    i++;
                    break;
                case '(':
                    i++;
                    result += preSign * calculateParenthesis(s);
                    break;
                case ')':
                    result += preSign * tmpNum;
                    i++;
                    return result;
                default:
                    int tmp = s.charAt(i) - '0';
                    tmpNum = tmpNum * 10 + tmp;
                    i++;
            }
        }
        return result;
    }
}
