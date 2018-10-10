package Mock;

/*
 * If streaming, W/O additional memory is required
 * */
public class ParenthesisWithStar {
    public boolean checkValidString(String s) {
        char[] array = s.toCharArray();
        int lo = 0, hi = 0;
        for (char ch : array) {
            switch (ch) {
                case '(':
                    lo++;
                    hi++;
                    break;
                case ')':
                    if (lo > 0)
                        lo--;
                    hi--;
                    break;
                default:
                    if (lo > 0)
                        lo--;
                    hi++;
            }
            if (hi < 0)
                return false;
        }
        return lo == 0;
    }


    public boolean isValid(String str) {
        char[] array = str.toCharArray();
        int star = 0, extraRight = 0, extraLeft = 0, score = 0;
        for (char ch : array) {
            switch (ch) {
                case '*':
                    star++;
                    break;
                case '(':
                    score++;
                    break;
                default:
                    score--;
                    if (score < 0) {
                        score = 0;
                        extraRight++;
                    }
            }
        }
        if (star > extraRight)
            star -= extraRight;
        score = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            switch (array[i]) {
                case ')':
                    score++;
                    break;
                case '(':
                    score--;
                    break;
            }
        }
        return true;
    }
}
