package Contest;

/*
 * since we get MLE,
 * we don't want to manage the resultS from the beginning
 * The length rather the content matters for the front
 * */
public class DecodedStringAtIndex {
    public static void main(String[] args) {
        DecodedStringAtIndex inst = new DecodedStringAtIndex();
        System.out.println(inst.decodeAtIndex("leet2code3", 17));
    }

    public String decodeAtIndex(String S, int K) {

        long resultLength = 0;

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (Character.isDigit(c)) {
                resultLength *= c - '0';
            } else {
                resultLength++;
            }
        }

        for (int i = S.length() - 1; i >= 0; i--) {
            char c = S.charAt(i);
            if (Character.isLetter(c)) {
                if (K == 0 || K == resultLength) {
                    return "" + c;
                }
                resultLength--;
            } else {
                resultLength /= c - '0';
                K %= resultLength;
            }
        }

        throw null;
    }

    public String decodeAtIndexMLE(String S, int K) {
        StringBuilder resultS = new StringBuilder();

        for (int i = 0; i < S.length(); i++) {
            if (Character.isDigit(S.charAt(i))) {
                int times = S.charAt(i) - '0';
                String sub = resultS.substring(0);
                for (int t = 0; t < times - 1; t++) {
                    resultS.append(sub);
                    if (resultS.length() >= K) {
                        break;
                    }
                }
            } else {
                resultS.append(S.charAt(i));
            }
            if (resultS.length() >= K) {
                break;
            }
        }
        return "" + resultS.charAt(K - 1);
    }
}
