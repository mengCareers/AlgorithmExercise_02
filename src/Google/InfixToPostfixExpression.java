package Google;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InfixToPostfixExpression {
    public String toPostfixExpression(String infixExp) {

        // corner cases to add

        Stack<Character> cacheOperators = new Stack<>();
        Stack<Character> preparePostfixExp = new Stack<>();
        Map<Character, Integer> operatorToPriority = new HashMap<>();
        operatorToPriority.put('+', 0);
        operatorToPriority.put('-', 0);
        operatorToPriority.put('*', 1);
        operatorToPriority.put('/', 1);
        infixExp += "#";
        char[] arrInfixExp = infixExp.toCharArray();

        for (char ch : arrInfixExp) {
            if (ch >= 'a' && ch <= 'z') {
                preparePostfixExp.push(ch);
            } else {
                if (ch == '(') {
                    cacheOperators.push(ch);
                } else if (ch == ')') {
                    while (!cacheOperators.isEmpty() && cacheOperators.peek() != '(') {
                        preparePostfixExp.push(cacheOperators.pop());
                    }
                    if (!cacheOperators.isEmpty() && cacheOperators.peek() == '(') {
                        cacheOperators.pop();
                    }
                } else if (ch == '#') {
                    while (!cacheOperators.isEmpty()) {
                        preparePostfixExp.push(cacheOperators.pop());
                    }
                    return buildPostfix(preparePostfixExp);
                } else { // + - * /
                    if (cacheOperators.isEmpty()
                            || cacheOperators.peek() == '('
                            || operatorToPriority.get(ch) > operatorToPriority.get(cacheOperators.peek())) {
                        cacheOperators.push(ch);
                    } else {
                        while (!cacheOperators.isEmpty()
                                && cacheOperators.peek() != '('
                                && operatorToPriority.get(ch) <= operatorToPriority.get(cacheOperators.peek())) {
                            preparePostfixExp.push(cacheOperators.pop());
                        }
                        cacheOperators.push(ch);
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        InfixToPostfixExpression inst = new InfixToPostfixExpression();
        String answer = inst.toPostfixExpression("a*(b+c)/d+e");
        System.out.println(answer);
    }

    private String buildPostfix(Stack<Character> preparePostfix) {
        StringBuilder result = new StringBuilder();
        while (!preparePostfix.isEmpty()) {
            result.append(preparePostfix.pop());
        }
        return result.reverse().toString();
    }
}
