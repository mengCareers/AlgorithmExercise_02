package CompanyOriented.Pinterest;

/*
Write a function to find the longest common prefix string amongst an array of strings.
 */
/*
must be prefix of strs[0]
we save all prefixes of strs[0] in trie
e.g. apple
a
ap
app x
appl  x
apple xx
if all strs can be found in trie, we return the longest
boolean
the first set true
save the shortest

 */
public class LongestCommonPrefix {


    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];

        int minLen = Integer.MAX_VALUE, minId = 0, n = strs.length;

        for (int i = 0; i < n; i++) {
            if (strs[i].length() < minLen) {
                minLen = strs[i].length();
                minId = i;
            }
        }

        String prefix = strs[minId];
        for (int i = 0; i < n; i++) {
            if (i != minId) {
                while (strs[i].indexOf(prefix) != 0) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                }
            }
        }

        return prefix;
    }

    /*
    TRIE Solution
     */

    private static Node root;

    public String longestCommonPrefix_Trie(String[] strs) {

        if (strs == null || strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];

        root = new Node();

        int minLen = Integer.MAX_VALUE, minId = 0;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minLen) {
                minLen = strs[i].length();
                minId = i;
            }
        }

        buildTrie(strs[minId]);

        for (int i = 0; i < strs.length; i++) {
            if (i != minId && !search(strs[i])) {
                return "";
            }
        }

        return findFirstPrefixEnd(strs[minId]);
    }

    private String findFirstPrefixEnd(String str) {

        Node ptr = root;

        for (char ch : str.toCharArray()) {
            ptr = ptr.children[ch - 'a'];
            if (ptr.isPrefixEnd) {
                return ptr.content;
            }
        }

        return "";
    }

    private boolean search(String str) {

        Node ptr = root;

        for (char ch : str.toCharArray()) {
            int order = ch - 'a';
            if (ptr.children[order] == null) {
                if (ptr == root)
                    return false;
                ptr.isPrefixEnd = true;
                return true;
            }
            ptr = ptr.children[order];
        }

        return (ptr.isPrefixEnd = true);
    }

    private void buildTrie(String str) {

        Node ptr = root;

        for (char ch : str.toCharArray()) {
            int order = ch - 'a';
            if (ptr.children[order] == null) {
                ptr.children[order] = new Node();
            }
            ptr.children[order].content = ptr.content == null ? "" + ch : ptr.content + ch;
            ptr = ptr.children[order];
        }
    }

    class Node {

        Node[] children;
        String content;
        boolean isPrefixEnd;

        public Node() {
            children = new Node[26];
            content = null;
            isPrefixEnd = false;
        }
    }
}

