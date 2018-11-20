package CompanyOriented.Facebook;

public class ImplementTrie {
}

class Trie {
    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {

        Node ptr = root;
        char[] array = word.toCharArray();
        int index;

        for (char ch : array) {
            index = ch - 'a';
            if (ptr.children[index] == null) {
                ptr.children[index] = new Node();
            }
            ptr = ptr.children[index];
        }

        ptr.isWordEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        return searchUtil(word, root, 0);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return startsWithUtil(prefix, root, 0);
    }

    private boolean searchUtil(String word, Node startNode, int curIndex) {

        if (startNode == null) {
            return false;
        }

        if (curIndex == word.length()) {
            return startNode.isWordEnd;
        }

        char ch = word.charAt(curIndex);
        int index = ch - 'a';

        return searchUtil(word, startNode.children[index], curIndex + 1);
    }

    private boolean startsWithUtil(String word, Node startNode, int curIndex) {

        if (startNode == null) {
            return false;
        }

        if (curIndex == word.length()) {
            return true; // different
        }

        char ch = word.charAt(curIndex);
        int index = ch - 'a';

        return startsWithUtil(word, startNode.children[index], curIndex + 1);
    }

    class Node {
        Node[] children;
        boolean isWordEnd;

        public Node() {
            children = new Node[26];
        }
    }
}