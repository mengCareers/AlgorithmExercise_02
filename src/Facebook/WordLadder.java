package Facebook;

import java.sql.ResultSet;
import java.util.*;

/**
 * input: beginWord, endWord, wordList
 * output: length of shortest transformation from beginWord to endWord OR 0
 */
/*
shortest path, BFS with level order traversal
state: state[b][e] length of transformation from beginWord to endWord
aim state: min(state[b][e])

start state: beginWord
aim state: endWord
state transition:
    for curWord, try transforming to all its possible 'next step' in wordList, and set as curWord..
        until reach endWord or queue is empty
 */
public class WordLadder {
    public static void main(String[] args) {
        WordLadder inst = new WordLadder();
        String[] arr = {"a", "b", "c"};
        List<String> wordList = Arrays.asList(arr);
        int answer = inst.ladderLength("a", "c", wordList);
        System.out.println(answer);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        visited.add(beginWord);
        int levelSize = 0, levelDepth = 1;
        while (!queue.isEmpty()) {
            levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                String curWord = queue.poll();
                for (String neighbourWord : getNeighbourWords(curWord, wordSet)) {
                    if (visited.contains(neighbourWord)) {
                        continue;
                    }
                    if (neighbourWord.equals(endWord)) {
                        return levelDepth + 1;
                    }
                    queue.offer(neighbourWord);
                    visited.add(neighbourWord);
                }
            }
            levelDepth++;
        }
        return 0;
    }

    private List<String> getNeighbourWords(String curWord, Set<String> wordSet) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < curWord.length(); i++) {
            char[] curArr = curWord.toCharArray();
            char original = curArr[i];
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != original) {
                    curArr[i] = ch;
                    String tmpStr = new String(curArr);
                    if (wordSet.contains(tmpStr)) {
                        result.add(tmpStr);
                    }
                }
            }
        }
        return result;
    }
}
