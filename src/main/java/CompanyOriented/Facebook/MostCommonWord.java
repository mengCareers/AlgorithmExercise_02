package CompanyOriented.Facebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * input: paragraph, list of banned words
 * output: most frequent word that is not int he list of banned words
 */
/*
all to lower case, split by ' '
a word, if not contains, put in map <word, freq>
get the maximum one
 */

/**
 * ([.,!?:;'\"-]|\\s)+ should do the trick. It is read as "for one or more occurrences of either a whitespace or punctuation". The important part here is to make sure that the '+' is kept outside the alternation, since we can have a mix of white-spaces and punctuations.
 */
public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>();
        for (String word : banned) {
            bannedSet.add(word);
        }
        //   \p{Punct}	Punctuation: One of !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
        //   \s	        A whitespace character: [ \t\n\x0B\f\r]
        //   X+	X, one or more times
        String[] words = paragraph.split("[\\p{Punct}\\s]+");
        Map<String, Integer> wordToFreq = new HashMap<>();
        for (String word : words) {
            String lowerCaseWord = word.toLowerCase();
            if (!bannedSet.contains(lowerCaseWord)) {
                wordToFreq.put(lowerCaseWord, wordToFreq.getOrDefault(lowerCaseWord, 0) + 1);
            }
        }
        int maxFreq = 0;
        String wordMostCommon = "";
        for (String word : wordToFreq.keySet()) {
            if (wordToFreq.get(word) > maxFreq) {
                maxFreq = wordToFreq.get(word);
                wordMostCommon = word;
            }
        }
        return wordMostCommon;
    }
}
