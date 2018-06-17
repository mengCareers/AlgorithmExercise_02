package Google;

import java.util.ArrayList;
import java.util.List;

/**
 * input: 2 - 9 inclusive
 * output: all possible letter combinations represented
 * <p>
 * mapping -> corresponding string
 * e.g. abc def ghi
 * and take one from each string, to combine
 * start state : curRes.length() = 0
 * end state : curRes.length() = list.size()
 * for each step, try chars at curIdx in curRes.length(), and add to list
 */
public class LetterCombinationsOfPhoneNumbers {
    private final static String[] digitsToChars = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList();
        }
        List<String> candidates = new ArrayList<>();
        for (char c : digits.toCharArray()) {
            candidates.add(digitsToChars[c - '0']);
        }
        List<String> result = new ArrayList<>();
        letterCombinationsFrom(candidates, new StringBuilder(), result);
        return result;
    }

    private void letterCombinationsFrom(List<String> candidates, StringBuilder curRes, List<String> result) {
        if (curRes.length() == candidates.size()) {
            result.add(curRes.toString());
            return;
        }
        String candidate = candidates.get(curRes.length());
        for (int i = 0; i < candidate.length(); i++) {
            curRes.append(candidate.charAt(i));
            letterCombinationsFrom(candidates, curRes, result);
            curRes.deleteCharAt(curRes.length() - 1);
        }
    }
}
