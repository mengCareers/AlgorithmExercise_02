package DivideAndConquer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DifferentWaysToAddParentheses {

    public static void main(String[] args) {

        DifferentWaysToAddParentheses inst = new DifferentWaysToAddParentheses();
        List<Integer> result = inst.diffWaysToCompute("2-1-1");

        System.out.println(result);
    }

    public List<Integer> diffWaysToCompute(String input) {

        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            if (symbol == '+' || symbol == '-' || symbol == '*') {
                List<Integer> leftPart = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightPart = diffWaysToCompute(input.substring(i + 1));
                for (int leftNum : leftPart) {
                    for (int rightNum : rightPart) {
                        if (symbol == '+') {
                            result.add(leftNum + rightNum);
                        } else if (symbol == '-') {
                            result.add(leftNum - rightNum);
                        } else {
                            result.add(leftNum * rightNum);
                        }
                    }
                }
            }
        }
        if (result.size() == 0) {
            result.add(Integer.valueOf(input));
        }

        return result;
    }
}
