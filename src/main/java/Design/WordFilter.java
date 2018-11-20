package Design;

public class WordFilter {

    public static void main(String[] args) {
        String[] words = {"abcd"};
        WordFilter inst = new WordFilter(words);

    }

    Node root;

    public WordFilter(String[] words) {

        root = new Node();
        Node ptr;
        String word;

        for (int weight = 0; weight < words.length; weight++) {

            word = words[weight] + '{'; // ASCII 27
            int wordLen = word.length();

            for (int i = 0; i < wordLen; i++) {
                ptr = root;
                for (int j = i; j < wordLen * 2 - 1; j++) {
                    char ch = word.charAt(j % wordLen);
                    if (ptr.children[ch - 'a'] == null) {
                        ptr.children[ch - 'a'] = new Node();
                    }
                    ptr.children[ch - 'a'].weight = weight;
                    ptr = ptr.children[ch - 'a'];
                }
            }

        }
    }

    public int f(String prefix, String suffix) {

        Node ptr = root;

        for (char ch : (suffix + '{' + prefix).toCharArray()) {
            if (ptr.children[ch - 'a'] == null) {
                return -1;
            }
            ptr = ptr.children[ch - 'a'];
        }

        return ptr.weight;
    }

    class Node {

        Node[] children;
        int weight;

        public Node() {
            children = new Node[27];
            weight = 0;
        }
    }
}
