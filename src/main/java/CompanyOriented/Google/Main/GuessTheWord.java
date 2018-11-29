package CompanyOriented.Google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
    int guess(String word);
}

public class GuessTheWord {

    private static final int WORD_LENGTH = 6;
    private static final Random random = new Random();

    public void findSecretWord(String[] wordlist, Master master) {
        List<String> list = new ArrayList<>(Arrays.asList(wordlist));

        while (true) {
            int n = list.size();
            int indexToGuess = random.nextInt(n);
            String wordToGuess = list.get(indexToGuess);
            int numMatches = master.guess(wordToGuess);
            if (numMatches == WORD_LENGTH)
                return;
            truncate(list, wordToGuess, numMatches);
        }
    }

    private void truncate(List<String> list, String target, int numMatches) {
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            if (countExactMatch(target, iterator.next()) != numMatches) {
                iterator.remove();
            }
        }
    }

    private int countExactMatch(String target, String word) {
        int numMatches = 0;

        for (int i = 0; i < WORD_LENGTH; i++) {
            if (target.charAt(i) == word.charAt(i)) {
                numMatches++;
            }
        }

        return numMatches;
    }
}
