package CompanyOriented.Pinterest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import CompanyOriented.Pinterest.LongestCommonPrefix.Node;

/*
Given a dictionary, a phone number,
if words the phone number represented can construct words in dictionary
 */
public class PhoneNumberDictionary {
    private final static String[] digitsToChars = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    Node root;

    public boolean canCanConstruct(Set<String> dict, int[] phoneNumber) {
        List<String> candidates = new ArrayList<>();
        for (int num : phoneNumber) {
            candidates.add(digitsToChars[num]);
        }
//        buildTrie();
        return dfs(candidates, 0, root);
    }

    private boolean dfs(List<String> candidates, int curIndex, Node parentPtr) {

        if (curIndex == candidates.size()) {
            return true;
        }
        String candidate = candidates.get(curIndex);
        for (char ch : candidate.toCharArray()) {
            if (parentPtr.children[ch - 'a'] != null) {
                dfs(candidates, curIndex + 1, parentPtr.children[ch - 'a']);
            }
        }
        return false;
    }
}
