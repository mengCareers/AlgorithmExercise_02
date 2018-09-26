package CompanyOriented.Karat;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class WordCount {
    /*
    If define a step - adding one char, order doesn't matter
abc
bcda
word, list of words
     */
    public static void main(String[] args) {
        LinkedHashMap<String, String> mapWordCount = new LinkedHashMap<>();
        mapWordCount.put("ABC", "1");
        mapWordCount.put("ADC", "1");
        mapWordCount.put("DABC", "1");
        mapWordCount.put("ABCE", "1");
        mapWordCount.put("ABCEF", "1");
        WordCount inst = new WordCount();
        Map<String, List<String>> result = inst.oneStepAway(mapWordCount);
        System.out.println(result);
    }

    /*
    in mapWordCount,
        for word in words:
            for onestep in words:
                if (length(onestep) - length(oneword) == 1)
                    checkIfOneStep();

     */
    public Map<String, List<String>> oneStepAway(LinkedHashMap<String, String> mapWordCount) {

        Map<String, List<String>> wordToOneStepWords = new HashMap<>();
        Map<String, int[]> wordToDict = new HashMap<>();
        Set<String> words = mapWordCount.keySet();

        for (String word : words) {
            wordToDict.put(word, buildWordHash(word));
        }

        for (String wordOri : words) {
            for (String wordOneStep : words) {
                if (wordOneStep.length() - wordOri.length() != 1)
                    continue;
                int[] dictOri = wordToDict.get(wordOri);
                int[] dictOneStep = wordToDict.get(wordOneStep);
                if (isOneStep(dictOri, dictOneStep)) {
                    wordToOneStepWords.putIfAbsent(wordOri, new ArrayList<>());
                    wordToOneStepWords.get(wordOri).add(wordOneStep);
                }
            }
        }

        return wordToOneStepWords;
    }


    private boolean isOneStep(int[] arrA, int[] arrB) {
        int numOfDiff = 0;
        for (int i = 0; i < 26; i++) {
            if (arrA[i] != arrB[i]) {
                numOfDiff++;
                if (numOfDiff > 1) {
                    return false;
                }
            }
        }
        return numOfDiff == 1;
    }


    private int[] buildWordHash(String word) {
        char[] wordArray = word.toCharArray();
        int[] dict = new int[26];
        for (char ch : wordArray) {
            dict[ch - 'A']++;
        }
        return dict;
    }

    private static void process(LinkedHashMap<String, String> linkedHashMap, String s, int N, int K) {
        String[] array = s.split("\\t");
        String word = array[0], count = array[1];
        if (word.length() >= 2 && word.length() <= K) {
            linkedHashMap.put(word, count);
        }
    }

    public LinkedHashMap<String, String> retrieveNWordsLengthNoExceedK(int N, int K) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new URL("").openStream(), "UTF-8").useDelimiter("\\n");
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        String line = "";
        while ((line = scanner.nextLine()) != null) {
            process(linkedHashMap, line, N, K);
            if (linkedHashMap.size() >= N)
                break;
        }
        scanner.close();

        return linkedHashMap;
    }
}
