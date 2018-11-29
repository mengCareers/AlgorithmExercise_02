package CompanyOriented.Salesforce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * input: String sentence
 * length of word incrasing
 * if length equal, original sentenct
 * starts with a capital letter followed by lowercase letters and spaces, ends with a period
 * output: rearrange String
 */
public class ArrangeTheWords {


    public static String arrange(String ori) {
        List<String> words = new ArrayList<>();
        String[] splits = ori.split(" ");
        int numWords = splits.length;

        /* Substract words. */
        if (numWords == 1) {
            return ori;
        }
        for (int i = 0; i < splits.length; i++) {
            if (i == 0) {
                words.add(splits[i].toLowerCase());
            } else if (i == splits.length - 1) {
                words.add(splits[i].substring(0, splits[i].length() - 1));
            } else {
                words.add(splits[i]);
            }
        }

        /* Sort words. */
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        /* Build result string. */
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numWords; i++) {
            String str = words.get(i);
            if (i == 0) {
                char[] strArr = str.toCharArray();
                strArr[0] = Character.toUpperCase(strArr[0]);
                result.append(strArr);
            } else {
                result.append(str);
                if (i == splits.length - 1) {
                    result.append(".");
                    break;
                }
            }
            result.append(" ");
        }

        return result.toString();
    }


}

/**
 * Here i come.
 * here, i, come
 * i, here, come
 * I here come.
 */
