package CompanyOriented.Karat;


import java.util.*;
import java.util.Map;


/*

We want to allow students to use variables when entering expressions in the calculator. In addition to the formula string, we’ll add a new input to our function that holds variables and their values:
  {"e": 8, "y": 7, "pressure": 5}
and our string inputs now have a format like
      "(e+3)-(pressure+temperature)+2".

Evaluate the formula result as fully as possible using the input variables. It is possible that not all variables have known values, in which case you should preserve them in the output.


Sample input:
    variables = {"e": 8, "y": 7, "pressure": 5}
    expression = "(e+3)-(pressure+temperature)+2"

Sample output:
    "8-temperature"


a + b + ( .. () ..)
recursion
a + b
(a + b)

Q1:
  // time - O(n) n is length of expression
  // space - O(1) constant

Q2:
space complexity - O(n) recursion stack frame
time complexity - O(n) n is length of the expression

String expr
Integer

no space
assume valid

addtion, substraction
O(N)

a-b = +a + (-b)
preSign = 1 //positieve
preNumber
a + b - c
      i
  +
    b
// +
// -
// digit
 */

class CalculationReal {
    public static void main(String[] args) {

        String expression3 = "1+2+3+4-5-6-7";  // -8
        String expression4 = "255"; // 255
        String expression5 = "600+9-12"; //597
        String expression6 = "1-2-3-4"; // -8
//        System.out.println(calculate(expression3));
//        System.out.println(calculate(expression4));
//        System.out.println(calculate(expression5));
//        System.out.println(calculate(expression6));

        String expression1 = "5+16-((9-6)-(4-2))"; // 20
        String expression2 = "(5)-(8-2)"; // -1
//        System.out.println(calculateWithParenthesis(expression1));
//        System.out.println(calculateWithParenthesis(expression2));


        String expression = "(e+3)-(pressure+temperature)+2";
        expression = "-(1-c-(1-a+(1+a)+b))";
        Map<String, Integer> variables = new HashMap<>();
        variables.put("e", 8);
        variables.put("pressure", 7);


        String result = calculateWithParenthesis(expression, variables);
        System.out.println("result: " + result);
    }


    private static StringBuilder toAppend;
    private static int i;

    public static String calculateWithParenthesis(String expression, Map<String, Integer> dictionary) {
        i = 0;
        toAppend = new StringBuilder();
        int result = calculateRecur("(" + expression + ")", dictionary, 1);
        return result + toAppend.toString();
    }

    private static int calculateRecur(String expression, Map<String, Integer> dictionary, int savePreSign) {
        int preSign = 1, preNum = 0, result = 0;

        while (i < expression.length()) {
            char ch = expression.charAt(i);
            switch (ch) {
                case '+':
                    result += preNum * preSign;
                    preNum = 0;
                    preSign = 1;
                    i++;
                    break;
                case '-':
                    result += preNum * preSign;
                    preNum = 0;
                    preSign = -1;
                    i++;
                    break;
                case '(':
                    i++;
                    result += preSign * calculateRecur(expression, dictionary, savePreSign * preSign);
                    preSign = 1;
                    preNum = 0;
                    i++;
                    break;
                case ')':
                    return result += preNum * preSign;
                default:
                    if (Character.isDigit(ch)) {
                        preNum = preNum * 10 + (ch - '0');
                        i++;
                    } else {
                        StringBuilder preStr = new StringBuilder();
                        preStr.append(ch);
                        i++;
                        while (i < expression.length() && Character.isLetter(expression.charAt(i))) {
                            preStr.append(expression.charAt(i));
                            i++;
                        }
                        if (dictionary.containsKey(preStr.toString())) {
                            preNum = dictionary.get(preStr.toString());
                        } else {
                            toAppend.append((savePreSign * preSign == 1 ? "+" : "-")).append(preStr);
                            preSign = 1;
                        }
                    }

            }
        }
        return result;
    }


    public static int calculate(String expression) {
        int preSign = 1, preDigit = 0, result = 0;
        for (char ch : expression.toCharArray()) {
            switch (ch) {
                case '-':
                    result += preSign * preDigit;
                    preDigit = 0;
                    preSign = -1;
                    break;
                case '+':
                    result += preSign * preDigit;
                    preDigit = 0;
                    preSign = 1;
                    break;
                default: {
                    preDigit = preDigit * 10 + (ch - '0');
                }
            }
        }
        result += preDigit * preSign;
        return result;
    }

    public static int calculateWithParenthesis(String expression) {
        i = 0;
        return calculateRecur("(" + expression + ")");
    }

    private static int calculateRecur(String expression) {
        int preSign = 1, preNum = 0, result = 0;
        char[] arr = expression.toCharArray();
        while (i < arr.length) {
            switch (arr[i]) {
                case '+':
                    result += preSign * preNum;
                    preSign = 1;
                    preNum = 0;
                    i++;
                    break;
                case '-':
                    result += preSign * preNum;
                    preSign = -1;
                    preNum = 0;
                    i++;
                    break;
                case '(':
                    i++;
                    result += preSign * calculateRecur(expression);
                    preSign = 1;
                    preNum = 0;
                    i++;
                    break;
                case ')':
                    result += preSign * preNum;
                    return result;
                default:
                    preNum = preNum * 10 + (arr[i] - '0');
                    i++;
            }
        }
        return result;
    }


}

