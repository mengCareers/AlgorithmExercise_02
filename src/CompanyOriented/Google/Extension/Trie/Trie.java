package CompanyOriented.Google.Extension.Trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    public void insert(String word) {
        TrieNode ptr = root;
        Map<Character, TrieNode> curChildren = root.children;
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
            char wc = wordArray[i];
        }
    }
}

class TrieNode {
    char c;
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWordEnd;

}