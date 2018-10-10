package CompanyOriented.Google;

import java.util.ArrayList;
import java.util.List;

/**
 * input: n
 * output:all combinations of well-formed parentheses
 * If we define curRes as one possible combination of well-formed parentheses,
 * Start State: #( = 0, #) = 0
 * End State: #( = #) = n
 * For each step,
 * curRes can append ( OR )
 * if satisfy #( >= #)
 * and can make valid answer afterwards
 * We terminate the recursion if we meet base case, i.e., the End State
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        // corner cases
        List<String> result = new ArrayList<>();
        generateParenthesisFrom(0, 0, n, new StringBuilder(), result);
        return result;
    }

    private void generateParenthesisFrom(int leftBracket, int rightBracket, int n, StringBuilder curRes, List<String> result) {
        if (leftBracket == n && rightBracket == n) {
            result.add(curRes.toString());
            return;
        }

        if (leftBracket >= rightBracket + 1 && rightBracket + 1 <= n) {
            curRes.append(")");
            generateParenthesisFrom(leftBracket, rightBracket + 1, n, curRes, result);
            curRes.deleteCharAt(curRes.length() - 1);
        }
        if (leftBracket + 1 <= n) {
            curRes.append("(");
            generateParenthesisFrom(leftBracket + 1, rightBracket, n, curRes, result);
            curRes.deleteCharAt(curRes.length() - 1);
        }
    }
}
