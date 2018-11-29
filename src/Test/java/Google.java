import CompanyOriented.Google.GuessTheWord;
import CompanyOriented.Google.Interview.CharReplacement;
import CompanyOriented.Google.Interview.CoverRangeCombinationSum;
import CompanyOriented.Google.Interview.FamilyTree;
import CompanyOriented.Google.Interview.StreamContainsWords;
import CompanyOriented.Google.Interview.WildCardMatching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// alien diction
// swapping puzzle

public class Google {

    @Test
    public void testGuessTheWord() {
        GuessTheWord guessTheWord = new GuessTheWord();
        guessTheWord.findSecretWord(new String[]{"a", "b"}, null);
    }

    @Test
    @DisplayName("WildcardMatching")
    public void testWildcardMatching() {
        assertTrue(WildCardMatching.isMatch("aa", "*"));
    }

    @Test
    @DisplayName("CharReplacement")
    public void testCharReplacement() {

        assertTrue(CharReplacement.canTransferByCharReplacement("aba", "cac"));
        /*
        a - c
        b - a
        a - c
         */
        assertTrue(CharReplacement.canTransferByCharReplacement("abc", "aaa"));
        /*
        a - a
        b - a
        c - a
         */
        assertTrue(CharReplacement.canTransferByCharReplacement("aba", "aaa"));
        /*
        a - a
        b - a
        a - a
         */

        assertFalse(CharReplacement.canTransferByCharReplacement("abc", "bca"));
        /*
        a - b
        b - c
        c - a
         */
        assertFalse(CharReplacement.canTransferByCharReplacement("baa", "acc"));
        /* ??
        b - a
        a - c
        a - c
         */
        assertFalse(CharReplacement.canTransferByCharReplacement("abc", "cba"));
        /*
        a - c
        b - b
        c - a
         */
    }


    @Test
    @DisplayName("CoverRangeCombinationSum")
    public void testCoverRangeCombinationSum() {
        int[][] ranges = {{100, 120}, {200, 240}, {400, 410}};
        assertFalse(CoverRangeCombinationSum.canCoverRange(new int[]{100, 110}, ranges));
        assertTrue(CoverRangeCombinationSum.canCoverRange(new int[]{90, 120}, ranges));
        assertTrue(CoverRangeCombinationSum.canCoverRange(new int[]{300, 360}, ranges));
        assertFalse(CoverRangeCombinationSum.canCoverRange(new int[]{310, 360}, ranges));
        assertTrue(CoverRangeCombinationSum.canCoverRange(new int[]{1, 999999999}, ranges));
    }

    @Test
    @DisplayName("StreamContainsWords")
    public void testStreamContainsWords() {
        List<String> result = StreamContainsWords.containsWord_test(new ArrayList<>(Arrays.asList('e', 'd', 'c', 'a', 'b', 'c', 's', 'd', 'e', 'f')),
                new ArrayList<>(Arrays.asList("abc", "def")));
        assertEquals(result, new ArrayList<>(Arrays.asList("abc", "def")));
    }

    /*
    Meng
    /\ \
    a b c
   /|\
  d e f
     */
    @Test
    @DisplayName("Monocracy")
    public void testMonocracy() {
        FamilyTree familyTree = new FamilyTree("Meng");
        familyTree.birth("a", "Meng");
        familyTree.birth("d", "a");
        familyTree.birth("e", "a");
        familyTree.birth("f", "a");

        familyTree.birth("b", "Meng");
        familyTree.birth("c", "Meng");

        familyTree.death("a");
        assertEquals(familyTree.getOrderOfSuccession()
                , new ArrayList<>(Arrays.asList("Meng", "d", "e", "f", "b", "c")));
    }
}
