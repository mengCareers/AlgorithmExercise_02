package CompanyOriented.Google.PhoneInterview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StreamContainsWords {

    public static List<String> containsWord_test(List<Character> stream, List<String> dict) {
        root = new Node();
        buildTrie(dict);
        List<Character> charList = new LinkedList<>();
        List<String> resultList = new ArrayList<>();

        for (char curCh : stream) {
            charList.add(curCh);
            List<String> wordsList = new ArrayList<>();
            searchPrefix(charList, charList.size() - 1, root, wordsList);
            resultList.addAll(wordsList);
        }

        return resultList;
    }


    static Node root;

    public List<String> containsWord(Stream stream, List<String> dict) {

        root = new Node();
        int wordMaxLength = buildTrie(dict);
        List<Character> charList = new LinkedList<>();
        List<String> resultList = new ArrayList<>();

        while (stream.hasNext()) {
            char curCh = stream.next();
            charList.add(curCh);
            if (wordMaxLength < charList.size())
                ((LinkedList<Character>) charList).removeFirst();
            List<String> wordsList = new ArrayList<>();
            searchPrefix(charList, charList.size() - 1, root, wordsList);
            resultList.addAll(wordsList);
        }

        return resultList;
    }

    private static void searchPrefix(List<Character> charList, int listIndex, Node parentNode, List<String> wordsList) {

        /* Rejection case. */
        if (listIndex < 0)
            return;

        /* Accepting case. */
        if (parentNode.content != null) {
            wordsList.add(parentNode.content);
            return;
        }

        int curOrder = charList.get(listIndex) - 'a';
        if (parentNode.children[curOrder] != null) {
            searchPrefix(charList, listIndex - 1, parentNode.children[curOrder], wordsList);
        }
    }

    /**
     * @param dict
     * @return Length of longest word.
     */
    private static int buildTrie(List<String> dict) {
        int maxLength = 0;
        for (String word : dict) {
            Node ptr = root;
            int n = word.length();
            maxLength = Math.max(n, maxLength);
            for (int i = n - 1; i >= 0; i--) {
                int order = word.charAt(i) - 'a';
                if (ptr.children[order] == null) {
                    ptr.children[order] = new Node();
                }
                ptr = ptr.children[order];
            }
            ptr.content = word;
        }

        return maxLength;
    }

    static class Node {
        Node[] children;
        String content;

        public Node() {
            children = new Node[26];
            content = null;
        }
    }
}
