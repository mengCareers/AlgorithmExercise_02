package Google;

import java.util.HashSet;
import java.util.Set;

/**
 * bulls if both digit and position match
 * cows if digit match only
 * write the function to give hint
 * e.g. 1807
 * 7810
 * fisrt loop 8,
 * save into set, and compare, if equal B++
 */
public class BullsAndCows {
    public static void main(String[] args) {
        BullsAndCows inst = new BullsAndCows();
        String answer = inst.getHint("1807", "7810");
        System.out.println(answer);
    }

    public String getHint(String secret, String guess) {
        int cntA = 0, cntB = 0;
        StringBuilder result = new StringBuilder();
        int[] mapSecret = new int[10];
        int[] mapGuess = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            char chSecret = secret.charAt(i);
            char chGuess = guess.charAt(i);
            if (chSecret == chGuess) {
                cntA++;
            } else {
                mapSecret[chSecret - '0']++;
                mapGuess[chGuess - '0']++;
            }
        }
        for (int i = 0; i < 10; i++) {
            cntB += Math.min(mapSecret[i], mapGuess[i]);
        }
        result.append(cntA).append("A").append(cntB).append("B");
        return result.toString();
    }
}
