package CompanyOriented.Pinterest;

import java.util.*;

/**
 * Given a list of words and an Integer N, return the matrix constructed such that each row and each column are valid words in list.
 * For example, wordlist = [cat, oah, aee, coa, aae, the, abh, csw]
 */
/*
 * Thought:
 * Brute-force, we build matrix row by row
 * we try each word as the first
 *   we try each word as the second
 *       if (valid)
 *           ...
 *
 * How do we define valid?
 *
 *   e.g. a b c
 *        x y z  - (there must be words start with ax.., by.., ca..
 *        axc
 *
 *        aaa
 *        xxx
 *        ccc
 *   start with is natural to trie.
 * */
public class WordMatrix {

    public static void main(String[] args) {
        WordMatrix inst = new WordMatrix();
        List<String> words = Arrays.asList("cat", "oah", "aee", "coa", "aae", "the", "abh", "csw");
        int N = 3;
        List<List<String>> result = inst.buildWordMatrix(words, N);
        System.out.println(result);
    }

    private static Node root;

    public List<List<String>> buildWordMatrix(List<String> words, int N) {

        if (words == null || words.isEmpty() || N == 0)
            return new ArrayList<>();

        root = new Node();
        buildTrie(words);
        List<List<String>> finalResult = new ArrayList<>();
        buildMatrixRecur(words, N, new HashSet<>(), new ArrayList<>(), finalResult);
        return finalResult;
    }

    private void buildMatrixRecur(List<String> words, int N, Set<String> visited, List<String> matrix, List<List<String>> finalResult) {
        if (matrix.size() == N) {
            finalResult.add(new ArrayList<>(matrix));
            return;
        }
        for (String word : words) {
            if (!visited.contains(word)) {
                matrix.add(word);
                visited.add(word);
                if (isValid(matrix, N)) {
                    buildMatrixRecur(words, N, visited, matrix, finalResult);
                }
                matrix.remove(matrix.size() - 1);
                visited.remove(word);
            }
        }
    }

    private boolean isValid(List<String> matrix, int N) {
        StringBuilder prefix;
        for (int j = 0; j < N; j++) {
            prefix = new StringBuilder();
            for (int i = 0; i < matrix.size(); i++) {
                prefix.append(matrix.get(i).charAt(j));
            }
            if (!searchPrefix(prefix.toString())) {
                return false;
            }
        }
        return true;
    }

    private boolean searchPrefix(String prefix) {
        Node ptr = root;
        for (char ch : prefix.toCharArray()) {
            int order = ch - 'a';
            if (ptr.children[order] == null)
                return false;
            ptr = ptr.children[order];
        }
        return true;
    }

    private void buildTrie(List<String> words) {
        Node ptr;
        for (String word : words) {
            ptr = root;
            for (char ch : word.toCharArray()) {
                int order = ch - 'a';
                if (ptr.children[order] == null)
                    ptr.children[order] = new Node();
                ptr = ptr.children[order];
            }
        }
    }

    class Node {
        Node[] children;

        public Node() {
            children = new Node[26];
        }
    }

}
