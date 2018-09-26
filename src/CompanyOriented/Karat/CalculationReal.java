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

        String expression1 = "5+16-((9-6)-(4-2))"; // 20
        String expression2 = "(5)-(8-2)"; // 20

        String expression = "(e+3)-(pressure+temperature)+2";
//        String expression = "e-ay+ey+cy";
        Map<String, Integer> variables = new HashMap<>();
        variables.put("e", 8);
        variables.put("pressure", 7);



        String result = calculate(expression, variables);
        System.out.println("result: " + result);
    }
    // may cause overflow


    public static int i;
    public static StringBuilder charRemained;

    public static String calculate(String expression, Map<String, Integer> map) {
        // assume input valid
        i = 0;
        charRemained = new StringBuilder();
        int result = calculateRecurUtil("(" + expression + ")", map);

        return (result == 0) ? charRemained.substring(1) : result + charRemained.toString(); // 1-1+c +c c
    }

    private static int calculateRecurUtil(String expression, Map<String, Integer> map) {
        int preSign = 1, preNumber = 0, result = 0;

        while (i < expression.length()) {
            switch (expression.charAt(i)) {
                case '+':
                    result += preSign * preNumber;
                    preSign = 1;
                    preNumber = 0;
                    i++;
                    break;
                case '-':
                    result += preSign * preNumber;
                    preSign = -1;
                    preNumber = 0;
                    i++;
                    break;
                case '(':
                    i++;
                    result += preSign * calculateRecurUtil(expression, map); // enter
                    break;
                case ')':
                    i++;
                    result += preSign * preNumber; // for the last section within ()
                    return result;
                default:  // lower-characters string, char, ch if it is
                    char ch = expression.charAt(i);

                    if (Character.isLetter(ch)) { // keys of map are single ()
                        if (i + 1 < expression.length() && !Character.isLetter(expression.charAt(i + 1))) {
                            if (map.containsKey(ch + "")) {
                                preNumber = map.get(ch + "");
                            } else { // char
                                charRemained.append(preSign == 1 ? '+' : '-').append(ch);
                            }
                            i++;
                        } else {
                            int j = i;
                            while (j + 1 < expression.length() && Character.isLetter(expression.charAt(j + 1))) {
                                j++;
                            }
                            String str = expression.substring(i, j + 1);
                            if (map.containsKey(str)) {
                                preNumber = map.get(str);
                            } else {
                                charRemained.append(preSign == 1 ? '+' : '-').append(str);
                            }
                            i = j + 1;
                        }
                    } else {
                        preNumber = preNumber * 10 + (expression.charAt(i) - '0');
                        i++;
                    }
            }
        }

        if (preNumber != 0) {
            result += preSign * preNumber;
        }

        return result;

    }


}

