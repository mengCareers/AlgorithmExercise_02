package CompanyOriented.Facebook;

import java.util.HashSet;
import java.util.Set;

/**
 * input: S
 * if word begins with a/e/i/o/u, append "ma" to word end
 * if word begins with not aeiou, remove the first letter of word and append it to word end, append "ma" to word end
 * add n x wi to end of each word (the first word n = 1, second n = 2)
 */
public class GoatLatin {
    public String toGoatLatin(String S) {

        StringBuilder result = new StringBuilder();
        StringBuilder lastAppendStr = new StringBuilder();
        String[] words = S.split(" ");
        Set<Character> lowerVowels = new HashSet<>();
        lowerVowels.add('a');
        lowerVowels.add('e');
        lowerVowels.add('i');
        lowerVowels.add('o');
        lowerVowels.add('u');

        for (String word : words) {
            char firstChar = word.charAt(0);
            if (lowerVowels.contains(Character.toLowerCase(firstChar))) {
                result.append(word);
            } else {
                result.append(word.substring(1)).append(firstChar);
            }
            result.append("ma").append(lastAppendStr).append("a ");
            lastAppendStr.append("a");
        }

        return result.toString().trim();
    }
}
