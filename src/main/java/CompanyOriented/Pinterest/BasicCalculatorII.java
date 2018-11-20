package CompanyOriented.Pinterest;

public class BasicCalculatorII {
    public int calculate(String s) {

        int preNum = 0, preSign = 1, result = 0, i = 0, n = s.length();
        boolean toMultiply = false, toDivide = false;

        while (i < n) {
            switch (s.charAt(i)) {
                case ' ':
                    break;
                case '+':
                    result += preNum * preSign;
                    preNum = 0;
                    preSign = 1;
                    break;
                case '-':
                    result += preNum * preSign;
                    preNum = 0;
                    preSign = -1;
                    break;
                case '*':
                    toMultiply = true;
                    break;
                case '/':
                    toDivide = true;
                    break;
                default:
                    int curNum = s.charAt(i) - '0';
                    while (i < n - 1 && Character.isDigit(s.charAt(i + 1))) {
                        curNum = curNum * 10 + s.charAt(i + 1) - '0';
                        i++;
                    }
                    if (toMultiply) {
                        preNum *= curNum;
                        toMultiply = false;
                    } else if (toDivide) {
                        preNum /= curNum;
                        toDivide = false;
                    } else {
                        preNum = curNum;
                    }
            }
            i++;
        }
        return result += preNum * preSign;
    }
}
