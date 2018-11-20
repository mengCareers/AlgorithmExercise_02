package CompanyOriented.Karat;

import java.util.HashMap;
import java.util.Map;

public class Calculation {


    public static void main(String[] args) {
        Calculation inst = new Calculation();

        String expression = "a + b - c + 1 + d - a";
        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 1);
        map.put('b', 2);
        map.put('c', 3);
        // String answer = inst.calculate(expression, map);
        int result = inst.calculateParenthesis("2 + 3");
        System.out.println(result);
    }

    int i = 0;
    Map<Character, Integer> map;
    StringBuilder remained;

    public String calculate(String expression, Map<Character, Integer> map) {
        i = 0;
        this.map = map;
        remained = new StringBuilder();
        int value = calculateParenthesis("(" + expression + ")");
        return (value == 0) ? remained.deleteCharAt(0).toString() : remained.toString();
    }

    private int calculateWithoutParenthesis(String s) {
        int result = 0, preSign = 1, preNum = 0;
        int i = 0;
        while (i < s.length()) {
            switch (s.charAt(i)) {
                case ' ':
                    i++;
                    break;
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
                default:
                    preNum = preNum * 10 + (s.charAt(i) - '0');
                    i++;
            }
        }
        if (preNum != 0)
            result += preNum * preSign;
        return result;
    }

    private int calculateParenthesis(String s) {
        int result = 0, preSign = 1, preNum = 0;
        while (i < s.length()) {
            switch (s.charAt(i)) {
                case ' ':
                    i++;
                    break;
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
                    result += preSign * calculateParenthesis(s);
                    break;
                case ')':
                    result += preSign * preNum;
                    i++;
                    return result;
                default:
                    int tmp = 0;
                    char ch = s.charAt(i);
                    if (Character.isDigit(ch)) {
                        tmp = ch - '0';
                        preNum = preNum * 10 + tmp;
                    } else if (map.containsKey(ch)) {
                        tmp = map.get(ch);
                        preNum = preNum * 10 + tmp;
                    } else {
                        if (preSign == 1)
                            remained.append("+");
                        else
                            remained.append("-");
                        preSign = 1;
                        remained.append(ch);
                    }
                    i++;
            }
        }
        if (preNum != 0)
            result += preNum * preSign;
        return result;
    }
}
