package CollectiveHealth;

import java.util.Stack;

/*
Verify if the string given is well-formatted in terms of open or close parenthesis.
 */
/*
Pseudo:
Using stack saving (,
  if )
    pop from stack
  if (
    push to stack
  else
    ignore
 */
public class WellFormattedVerification {

    public static boolean isWellFormatted_0(String str) {

        Stack<Character> stack = new Stack<>(); // Save left bracket.

        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                stack.push('(');
            } else if (ch == ')') {
                if (stack.isEmpty())
                    return false;
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    public static boolean isWellFormatted_1(String str) {

        int score = 0; // Indicate balance.

        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                score++;
            } else if (ch == ')') {
                if (score <= 0)
                    return false;
                score--;
            }
        }

        return score == 0;
    }

    public static void main(String[] args) {
        String str = "()";
        System.out.println(isWellFormatted(str));
    }
}
