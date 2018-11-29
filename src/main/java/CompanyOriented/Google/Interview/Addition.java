package CompanyOriented.Google.Interview;


import java.util.LinkedList;
import java.util.List;

/*
list of chars
carry = 0

for (i = n-1; i>=0; i--) {
    char ch = digits[i] + carry
    ch++;
    if (ch == 10)
        ch = 0
    carry = 1;
    list.insert(0, ch)
}
 */
public class Addition {

    public static void main(String[] args) {
        Addition inst = new Addition();
        System.out.println(inst.addOne(new char[]{'9', '9'}));
    }

    public List<Character> addOne(char[] digits) {
        List<Character> result = new LinkedList<>();
        int carry = 0, resultDigit = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            resultDigit += digits[i] - '0' + carry;
            carry = resultDigit / 10;
            resultDigit %= 10;
            result.add(0, (char) ('0' + resultDigit));
            resultDigit = 0;
        }
        if (carry != 0) {
            result.add(0, '1');
        }
        return result;
    }
}
/*
char[] 1, 2
+ 1
9 9
  1
0 0
 */