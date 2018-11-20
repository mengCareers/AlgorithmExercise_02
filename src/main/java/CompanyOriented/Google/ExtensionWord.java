package CompanyOriented.Google;

import java.util.ArrayList;
import java.util.List;

/*
Extension word:
when a letter repeated >= three times the word is considered as a word extension.
eg:lll and oooo


List<String> validWords -
check if extension word remove duplicated is in  validWords
 */

/*
for a extension word,
we remove duplicates, only keep unique char
see if in the extension word

 */
public class ExtensionWord {
    public static void main(String[] args) {
        ExtensionWord inst = new ExtensionWord();
        printResult(inst.getExtensionPortion("aaaaa"));
    }

    public static void printResult(List<int[]> res) {
        for (int[] arr : res)
            System.out.print(arr[0] + " , " + arr[1] + " | ");
    }

    public List<int[]> getExtensionPortion(String word) {
        int i = 0;
        List<int[]> result = new ArrayList<>();
        while (i < word.length()) {
            int j = i + 1;
            while (j < word.length() && word.charAt(j) == word.charAt(i)) {
                j++;
            }

            if (j - i >= 3)
                result.add(new int[]{i, j - 1});
            i = j;
        }
        return result;
    }
}
