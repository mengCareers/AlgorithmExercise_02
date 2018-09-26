package CompanyOriented.Amazon;


import java.util.*;

/*
wordsToExclude to set, and contains
 */
public class MostFrequentlyUsedWords {

    public static void main(String[] args) {
        MostFrequentlyUsedWords inst = new MostFrequentlyUsedWords();
        String[] toExclude = {"and", "he", "the", "to", "is", "Jack", "Jill"};
        List<String> wordsToExclude = Arrays.asList(toExclude);
        String text = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack's and Jill's" +
                " favorite food.";
        List<String> result = inst.retrieveMostFrequentlyUsedWords(text, wordsToExclude);
        System.out.println(result);
    }

    List<String> retrieveMostFrequentlyUsedWords(String literatureText, List<String> wordsToExclude) {

        List<String> result = new LinkedList<>();

        if (literatureText == null || literatureText.isEmpty()) {
            return result;
        }

        Set<String> excludeSet = new HashSet<>();
        for (String word : wordsToExclude) {
            excludeSet.add(word.toLowerCase());
        }

        // String[] words = literatureText.split("\\p{P}|\\s");
        String[] words = literatureText.split("\\W|\\d");
        Map<String, Integer> wordToFrequency = new HashMap<>();
        int maxFreq = 0, freq = 0;
        String tmp;

        for (String word : words) {
            word = word.toLowerCase();
            if (excludeSet.contains(word)) {
                continue;
            }
            freq = wordToFrequency.getOrDefault(word, 0) + 1;
            wordToFrequency.put(word, freq);
            maxFreq = Math.max(maxFreq, freq);
        }

        for (String word : wordToFrequency.keySet()) {
            if (wordToFrequency.get(word) == maxFreq) {
                result.add(word);
            }
        }

        return result;
    }
}
