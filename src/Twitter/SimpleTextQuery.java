package Twitter;

import java.util.*;

/*
input: array of sentences
       array of phrases
output: index of sentence contains all of words of a phrase
 */
public class SimpleTextQuery {
    public Set<Integer> simpleTextQuery(String[] sentences, String phrase) {

        Map<String, Set<Integer>> wordToSentenceId = new HashMap<>();
        String[] words;

        for (int i = 0; i < sentences.length; i++) {
            words = sentences[i].split(" ");
            for (String word : words) {
                if (!wordToSentenceId.containsKey(word)) {
                    wordToSentenceId.put(word, new HashSet<>());
                }
                wordToSentenceId.get(word).add(i);
            }
        }

        words = phrase.split(" ");
        Set<Integer> tmpSet = wordToSentenceId.get(words[0]);;

        for (int i = 1; i < words.length; i++) {
            tmpSet.retainAll(wordToSentenceId.get(words[i]));
        }

        return tmpSet;
    }
}
