package Google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
For Backtracking, we ought to figure out our start point, end point and the tranformation from each step.

        We start at the position 0,
        for each step,
        {
        we can either skip the word.charAt(pos) and increase cnt by 1;
        or add word.charAt(pos) to curRes and set cnt to 0;
        and we move forward to the next position
        }
        we end at the position word.length().
        Please note that we need to take care of the case that cnt equals to 0.
 */
public class GeneralizedAbbreviation {
    public static void main(String[] args) {
        GeneralizedAbbreviation inst = new GeneralizedAbbreviation();
        List<String> result = inst.generateAbbreviations("word");
        System.out.println("word" + 1);
    }

    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        generateAbbreviationsFrom(word, result, 0, 0, "");
        return result;
    }

    private void generateAbbreviationsFrom(String word, List<String> result, int pos, int cnt, String curRes) {
        if (pos == word.length()) {
            if (cnt != 0) {
                result.add(curRes + cnt);
            } else {
                result.add(curRes);
            }
            return;
        }
        generateAbbreviationsFrom(word, result, pos + 1, cnt + 1, curRes);
        if (cnt != 0) {
            generateAbbreviationsFrom(word, result, pos + 1, 0, curRes + cnt + word.charAt(pos));
        } else {
            generateAbbreviationsFrom(word, result, pos + 1, 0, curRes + word.charAt(pos));
        }
    }
}
