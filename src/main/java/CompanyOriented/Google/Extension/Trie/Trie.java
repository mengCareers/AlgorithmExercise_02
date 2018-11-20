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

        char[] wordArray = word.toCharArray();
        for (char ch : wordArray) {
            if (!ptr.children.containsKey(ch))
                ptr.children.put(ch, new TrieNode());
            ptr = ptr.children.get(ch);
        }
    }

}

class TrieNode {
    char c;
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWordEnd;

}