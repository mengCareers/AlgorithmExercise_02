package CompanyOriented.Twitter;

/*
state[i][j]
j - i = 0 if str[i] can be decoded
j - i = 1 if str[i..j] valid can be decoded
j - i = 2 if str[i..j] valid can be decoded
goal state: state[n - 1];
state transition: state[i] = state[i - 1] + str[i]
                  state[i] = state[i - 2] + str[i..i-1]
                  state[i] = state[i - 3] + str[i..i-2]
 */
public class ASCIIDecoding {

    public static void main(String[] args) {
        ASCIIDecoding inst = new ASCIIDecoding();
        boolean ans = inst.decode("97329810");
        System.out.println(ans);
    }

    private boolean isValid(int num) {
        if (num >= 48 && num <= 57) {
            return true;
        }
        if (num >= 65 && num <= 90) {
            return true;
        }
        if (num >= 97 && num <= 122) {
            return true;
        }
        return num == 32;
    }

    private boolean decode(String str) {
        int n = str.length();
        boolean[] state = new boolean[n];
        String tmp;

        state[1] = isValid(Integer.valueOf(str.substring(0, 2)));
        state[2] = isValid(Integer.valueOf(str.substring(0, 3)));
        for (int i = 3; i < n; i++) {
            tmp = str.substring(i - 1, i + 1);
            if (isValid(Integer.valueOf(tmp))) {
                state[i] |= state[i - 2];
            }
            tmp = str.substring(i - 2, i + 1);
            if (isValid(Integer.valueOf(tmp))) {
                state[i] |= state[i - 3];
            }
        }
        return state[n - 1];
    }

}
