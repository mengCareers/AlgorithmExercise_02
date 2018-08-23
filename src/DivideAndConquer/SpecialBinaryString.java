package DivideAndConquer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * If we regard 0 as ), 1 as ( in definition of Special String,
 * it is actually valid parenthesis.
 * <p>
 * to make it lexicographically largest, deeper prefer,
 * i.e. 110010, (())()
 * e.g. ()(()), 101100, whenever balanced, we surround .. with parenthesis, i.e., ( + .. + )
 */
public class SpecialBinaryString {

    public String makeLargestSpecial(String S) {

        int balance = 0, l = 0;
        List<String> subResults = new LinkedList<>();
        for (int r = 0; r < S.length(); r++) {
            if (S.charAt(r) == '0') {
                balance--;
            } else {
                balance++;
            }
            if (balance == 0) { // special
                subResults.add('1' + makeLargestSpecial(S.substring(l + 1, r)) + '0');
                l = r + 1;
            }
        }
        Collections.sort(subResults, Collections.reverseOrder());

        return String.join("", subResults);
    }

}
