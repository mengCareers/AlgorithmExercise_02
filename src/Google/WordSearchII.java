package Google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchII {
    TrieNode root;

    public List<String> findWords(char[][] board, String[] words) {

        root = new TrieNode();

        Set<String> distinctWords = new HashSet<>();
        for (String word : words) {
            distinctWords.add(word);
        }
        List<String> result = new ArrayList<>();
        for (String word : distinctWords) {
            insert(word);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                canFindWordFrom(board, i, j, result, root);
            }
        }

        return result;
    }

    private void canFindWordFrom(char[][] board, int x, int y, List<String> result, TrieNode root) {

        if (!root.word.isEmpty()) {
            result.add(root.word);
            root.word = "";
            return;
        }
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] == '-') {
            return;
        }
        char ch = board[x][y];

        if (root.children[ch - 'a'] == null) {
            return;
        }

        board[x][y] = '-';
        canFindWordFrom(board, x + 1, y, result, root.children[ch - 'a']);
        canFindWordFrom(board, x - 1, y, result, root.children[ch - 'a']);
        canFindWordFrom(board, x, y + 1, result, root.children[ch - 'a']);
        canFindWordFrom(board, x, y - 1, result, root.children[ch - 'a']);
        board[x][y] = ch;
    }

    class TrieNode {

        TrieNode[] children;
        String word;

        public TrieNode() {
            children = new TrieNode[26];
            word = "";
        }
    }

    private void insert(String word) {

        char[] array = word.toCharArray();
        TrieNode ptr = root;
        for (int i = 0; i < array.length; i++) {
            if (ptr.children[array[i] - 'a'] == null) {
                ptr.children[array[i] - 'a'] = new TrieNode();
            }
            ptr = ptr.children[array[i] - 'a'];
        }

        ptr.word = word;
    }
}
