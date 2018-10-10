package CompanyOriented.Google;

/**
 According to the problem description, we have to decide how many bytes count towards a single character.
 For data[i],
 numOnes = cntBytes(data[i]); indicates that the first numOnes bits are all 1's, i.e., numOnes bytes count towards a single character.
 There are several possibilities:

 If numOnes = 0, data[i] is a valid 1-byte character, so we go further check for the next character.
 If numOnes = 1, data[i] is invalid.
 If 1 < numOnes <= 4,
 if all data[j], for i + 1 <= j <= i + numOnes - 1, are with most significant 2 bits being 10, data[i] is valid.
 And we start from j + 1 to go further check for the next character.
 If numOnes > 4, data[i] is invalid.
 */
public class UTF8Validation {

    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) {
            return true;
        }
        int i = 0;
        int numOnes = 0;
        while (i < data.length) {
            numOnes = cntBytes(data[i]);
            if (numOnes == 0) {
                i++;
                continue;
            }
            if (numOnes == 1 || numOnes > 4) {
                return false;
            }
            if (i + numOnes - 1 >= data.length) {
                return false;
            }
            int j = i + 1;
            for (; j < i + numOnes; j++) {
                if (!isFirstTwoDigitValid(data[j])) {
                    return false;
                }
            }
            i = j;
        }
        return true;
    }

    private static int cntBytes(int num) {
        String binary = Integer.toBinaryString(num);
        if (binary.length() < 8) {
            return 0;
        }
        int numOnes = 0;
        for (char c : binary.toCharArray()) {
            if (c - '0' != 1) {
                break;
            }
            numOnes++;
        }
        return numOnes;
    }

    private static boolean isFirstTwoDigitValid(int num) {
        String binary = Integer.toBinaryString(num);
        if (binary.length() < 8) {
            return false;
        }
        return binary.startsWith("10");
    }
}
