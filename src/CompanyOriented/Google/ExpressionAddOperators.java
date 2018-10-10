package CompanyOriented.Google;

import java.util.ArrayList;
import java.util.List;

/*
Logical Thinking
State: from curPos with curVal, we try all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
Start State: curPos = 0, curVal = 0
End State: curPos = num.length()
Aim State: curPos = num.length() && curVal = target
Transition Function:
curPos increase by one for each state.
For every state,
we start at curPos, and we can add '+' or '-' or '*' to curRes, and move on to the digits afterwards.
*/

public class ExpressionAddOperators {
    public static void main(String[] args) {
        ExpressionAddOperators inst = new ExpressionAddOperators();
        List<String> result = inst.addOperators("232", 12);
        System.out.println(result);
    }

    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        addOperatorsFrom(num, target, 0, 0, 0, result, "");
        return result;
    }

    private void addOperatorsFrom(String num, int target, int curPos, long curVal, long toRestore, List<String> result, String curExpression) {
        if (curPos == num.length()) {
            if (curVal == target) {
                result.add(curExpression);
            }
            return;
        }
        for (int i = curPos; i < num.length(); i++) {
            if (num.charAt(curPos) == '0' && i != curPos) {
                break;
            }
            long tmp = Long.parseLong(num.substring(curPos, i + 1));
            if (curPos == 0) {
                addOperatorsFrom(num, target, i + 1, tmp, tmp, result, String.valueOf(tmp));
            }
            else {
                addOperatorsFrom(num, target, i + 1, curVal + tmp, tmp, result, curExpression + "+" + tmp);
                addOperatorsFrom(num, target, i + 1, curVal - tmp, -tmp, result, curExpression + "-" + tmp);
                addOperatorsFrom(num, target, i + 1, curVal - toRestore + toRestore * tmp, toRestore * tmp, result, curExpression + "*" + tmp);
            }
        }
    }
}
