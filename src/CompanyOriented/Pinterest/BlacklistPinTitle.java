package CompanyOriented.Pinterest;

/**
 * Given a Pin title blacklist which contains words and phrases, return false if the Pin title contains any one in blacklist.
 */

import java.util.Arrays;
import java.util.List;

/*
  starts at each word of Pin title, we check if it contains elements in blacklist
  words and phrases can be put in a trie

  pseudo-code
  for i in title
  if title[i] ' '
  start from i+1
       if (dfs(root, i+1)
           return false
  return true

  func boolean dfs(parentptr, index
  parentptr.isWordEnd
    if (title[index] = ' ')
        return true
  if parentptr.children[title[index] - 'a']) null
  return false;
  return dfs(parentptr.children[title[index-1] - 'a']), index + 1)
 */
public class BlacklistPinTitle {

    public static void main(String[] args) {
        String pinTitle = "I love fucking Kris go Wu die";
        List<String> blacklist = Arrays.asList("fuck", "go die");
        boolean answer = isValidPinTitle(pinTitle, blacklist);
        System.out.println(answer);
    }

    private static Node root;

    public static boolean isValidPinTitle(String pinTitle, List<String> blacklist) {

        root = new Node();

        buildTrie(blacklist);

        int n = pinTitle.length();
        char[] titleArr = pinTitle.toCharArray();
        for (int i = 0; i < n; i++) {
            if (Character.isUpperCase(titleArr[i])) {
                titleArr[i] = Character.toLowerCase(titleArr[i]);
            }
        }
        pinTitle = new String(titleArr);
        for (int i = 0; i < pinTitle.length(); i++) {
            if (pinTitle.charAt(i) == ' ') {
                // Check starting at i+1.
                if (dfs(i + 1, root, pinTitle)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean dfs(int index, Node parentPtr, String pinTitle) {
        if (parentPtr.isWordEnd) {
            if (index == pinTitle.length() || pinTitle.charAt(index) == ' ')
                return true;
        }
        if (index == pinTitle.length()) {
            return false;
        }

        char ch = pinTitle.charAt(index);
        int order = 0;
        if (ch == ' ')
            order = 26;
        else
            order = ch - 'a';
        if (parentPtr.children[order] == null) {
            return false;
        }

        return dfs(index + 1, parentPtr.children[order], pinTitle);
    }

    private static void buildTrie(List<String> blacklist) {
        Node ptr;
        for (String str : blacklist) {
            ptr = root;
            for (char ch : str.toCharArray()) {
                int order = 0;
                if (ch == ' ')
                    order = 26;
                else
                    order = ch - 'a';
                if (ptr.children[order] == null) {
                    ptr.children[order] = new Node();
                }
                ptr = ptr.children[order];
            }
            ptr.isWordEnd = true;
        }
    }

    static class Node {
        Node[] children;
        boolean isWordEnd;

        public Node() {
            children = new Node[27]; // children[26] represents ' '.
            isWordEnd = false;
        }
    }
}
