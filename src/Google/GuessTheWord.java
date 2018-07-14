package Google;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * There is no solution that can guarantee to find a secret word in 10 tries.
 * If I make up a test case with wordlist like ["aaaaaa", "bbbbbb" ...., "zzzzzz"], it need 26 tries to find the secret.
 * So 10 tries is just a limit to test reasonable solution.
 * And this problem is more than finding right output for given input,
 * it's more about a strategy.
 */
interface Master {
    public int guess(String word);
}

public class GuessTheWord {
    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0, matches = 0; i < 10 && matches < 6; i++) {
            String guessWord = wordlist[new Random().nextInt(wordlist.length)];
            matches = master.guess(guessWord);
            List<String> candidateWordlist = new ArrayList<>();
            for (String word : wordlist) {
                if (countMatches(guessWord, word) == matches) {
                    candidateWordlist.add(word);
                }
            }
            wordlist = candidateWordlist.toArray(new String[candidateWordlist.size()]);
        }
    }

    private int countMatches(String word1, String word2) {
        int matches = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                matches++;
            }
        }
        return matches;
    }
}
