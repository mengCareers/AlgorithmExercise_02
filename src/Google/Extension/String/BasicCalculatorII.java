package Google.Extension.String;

public class BasicCalculatorII {
    public static void main(String[] args) {
        System.out.println(calculate("0-2147483647"));
    }

    public static int calculate(String s) {
        s = s.trim();
        char[] array = s.toCharArray();
        int n = array.length, result = 0, prenum = 0, presign = 1, lastnum = 0;
        boolean toMultiply = false, toDivide = false;
        for (int i = 0; i < n; i++) {
            char ch = array[i];
            switch (ch) {
                case ' ':
                    break;
                case '+':
                    result += prenum * presign;
                    prenum = 0;
                    presign = 1;
                    break;
                case '-':
                    result += prenum * presign;
                    prenum = 0;
                    presign = -1;
                    break;
                case '*':
                    lastnum = prenum * presign;
                    prenum = 0;
                    presign = 1;
                    toMultiply = true;
                    break;
                case '/':
                    lastnum = prenum * presign;
                    prenum = 0;
                    presign = 1;
                    toDivide = true;
                    break;
                default:
                    prenum = prenum * 10 + (ch - '0');
                    while (i + 1 < n && array[i + 1] >= '0' && array[i + 1] <= '9') {
                        ch = array[i + 1];
                        prenum = prenum * 10 + (ch - '0');
                        i++;
                    }
                    if (toMultiply) {
                        prenum = lastnum * prenum;
                        toMultiply = false;
                    }
                    if (toDivide) {
                        prenum = lastnum / prenum;
                        toDivide = false;
                    }
                    if (i == n - 1) {
                        result += presign * prenum;
                        break;
                    }
            }
        }
        return result;
    }
}
