package DivideAndConquer;

import java.util.ArrayList;
import java.util.List;


public class DifferentWaysToAddParentheses {

    public static void main(String[] args) {

        DifferentWaysToAddParentheses inst = new DifferentWaysToAddParentheses();
        List<Integer> result = inst.diffWaysToCompute("2-1-1");

        System.out.println(result);
    }

    public List<Integer> diffWaysToCompute(String input) {

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                List<Integer> leftResults = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightResults = diffWaysToCompute(input.substring(i + 1));
                for (int leftResult : leftResults) {
                    for (int rightResult : rightResults) {
                        if (input.charAt(i) == '+') {
                            result.add(leftResult + rightResult);
                        } else if (input.charAt(i) == '-') {
                            result.add(leftResult - rightResult);
                        } else {
                            result.add(leftResult * rightResult);
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
