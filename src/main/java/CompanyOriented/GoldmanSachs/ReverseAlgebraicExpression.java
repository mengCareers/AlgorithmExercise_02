package CompanyOriented.GoldmanSachs;

import java.util.Scanner;
import java.util.Stack;

/*
input: string expression such as "a1 op a2 op a3 .. op an"
    where op represents an operator in {+, -, *, /}
    where ai is integer or real number
    where no spaces

output: reversed expression

e.g.
input: -1.4--2.5
output: -2.5--1.4

TP:
There are 3 types of section:

a. positive numbers
b. '-' before a postive number
c. operators exclude '-' before positive numbers

b always comes before a, is '-' and previous is operator
a is digit or ','
c always comes after a, is '+' '-' '*' '/'

since reverse, we utilize stack
 */
public class ReverseAlgebraicExpression {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReverseAlgebraicExpression inst = new ReverseAlgebraicExpression();
        while (true) {
            System.out.print("input: ");
            String expression = scanner.nextLine();
            if (expression.equals("N")) {
                break;
            }
            String result = inst.reverse(expression);
            System.out.println(result);
        }

    }

    public String reverse(String expression) {

        if (expression == null || expression.isEmpty())
            return expression;

        StringBuilder result = new StringBuilder();
        Stack<Character> operators = new Stack<>();
        Stack<String> numbers = new Stack<>();
        char[] expArray = expression.toCharArray();

        buildStacks(operators, numbers, expArray);

        result.append(numbers.pop());
        while (!operators.isEmpty()) {
            result.append(operators.pop());
            result.append(numbers.pop());
        }

        return result.toString();
    }

    private void buildStacks(Stack<Character> operators, Stack<String> numbers, char[] expArray) {
        int i = 0, length = expArray.length;
        StringBuilder curNumber;

        while (i < length) {
            curNumber = new StringBuilder();
            // for '-' before a postive number
            if (expArray[i] == '-' &&
                    (i == 0 || expArray[i - 1] == '-' || expArray[i - 1] == '+' || expArray[i - 1] == '*' || expArray[i - 1] == '/')) {
                curNumber.append('-');
                i++;
            }
            // for numbers
            while (i < length && (Character.isDigit(expArray[i]) || expArray[i] == '.')) {
                curNumber.append(expArray[i]);
                i++;
            }
            // for operators exclude '-' before positive numbers
            if (i < length) {
                operators.push(expArray[i]);
                i++;
            }
            numbers.push(curNumber.toString());
        }

    }
}