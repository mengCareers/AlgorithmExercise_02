package CompanyOriented.Google;

public class Trie {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }


    public void insert(String word) {
        TrieNode ptr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ptr.children[ch - 'a'] == null) {
                ptr.children[ch - 'a'] = new TrieNode();
            }
            ptr = ptr.children[ch - 'a'];
        }
        ptr.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode ptr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ptr.children[ch - 'a'] == null) {
                return false;
            }
            ptr = ptr.children[ch - 'a'];
        }
        return ptr.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode ptr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (ptr.children[ch - 'a'] == null) {
                return false;
            }
            ptr = ptr.children[ch - 'a'];
        }
        return true;
    }
}
