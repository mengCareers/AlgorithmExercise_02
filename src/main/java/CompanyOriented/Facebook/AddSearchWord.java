package CompanyOriented.Facebook;

/*
211. Add and Search Word - Data structure design
 */
public class AddSearchWord {

    Node root;

    /**
     * Initialize your data structure here.
     */
    public AddSearchWord() {
        root = new Node();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {

        Node ptr = root;
        int index;
        char[] array = word.toCharArray();

        for (int i = 0; i < array.length; i++) {
            index = array[i] - 'a';
            if (ptr.children[index] == null) {
                ptr.children[index] = new Node();
            }
            ptr = ptr.children[index];
        }

        ptr.isWordEnd = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return search(word, root, 0);
    }

    private boolean search(String word, Node startNode, int curIndex) {

        if (startNode == null) {
            return false;
        }

        if (curIndex == word.length()) {
            return startNode.isWordEnd;
        }

        char ch = word.charAt(curIndex);

        if (ch == '.') {
            boolean tmpAnswer = false;
            for (Node child : startNode.children) {
                tmpAnswer = tmpAnswer | search(word, child, curIndex + 1);
            }
            return tmpAnswer;
        }

        return search(word, startNode.children[ch - 'a'], curIndex + 1);
    }

    class Node {
        Node[] children;
        boolean isWordEnd;

        public Node() {
            children = new Node[26];
        }
    }
}
