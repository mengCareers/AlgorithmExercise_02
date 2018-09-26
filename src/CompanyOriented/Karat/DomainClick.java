package CompanyOriented.Karat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
count for each subdomain considering all cpdomains, i.e. 10 a.b.c
c 10
b.c 10
a.b.c 10
map<c, freq>
map<b.c, freq>
 */
public class DomainClick {

    public static void main(String[] args) {
        DomainClick inst = new DomainClick();
//        String[] cpdomains = {"9001 discuss.leetcode.com"};
//        List<String> result = inst.subdomainVisits(cpdomains);
        String[] user1 = {"3234.html", "xys.html", "7hsaa.html"};
        String[] user2 = {"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"};
        List<String> result = inst.getLongestContinuousCommonHistory(user1, user2);
        System.out.println(result);
    }

    public List<String> subdomainVisits(String[] cpdomains) {

        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>(); // key: subdomain, value: frequency
        StringBuilder resultStringBuilder = new StringBuilder();

        for (String cpdomain : cpdomains) {
            int indexSpace = cpdomain.indexOf(' ');
            int numClicks = Integer.parseInt(cpdomain.substring(0, indexSpace));
            String domain = cpdomain.substring(indexSpace + 1);
            resultStringBuilder.setLength(0);
            resultStringBuilder.append(domain);
            while (true) {
                map.put(resultStringBuilder.toString(), map.getOrDefault(resultStringBuilder.toString(), 0) + numClicks);
                int dotPosition = resultStringBuilder.indexOf(".");
                if (dotPosition == -1)
                    break;
                resultStringBuilder.delete(0, dotPosition + 1);
            }
        }

        for (String domain : map.keySet())
            result.add(map.get(domain) + " " + domain);

        return result;
    }

    /**
     * 第二题：给每个user访问历史记录，找出两个user之间longest continuous common history.
     * 输入： [
     * ["3234.html", "xys.html", "7hsaa.html"], // user1
     * ["3234.html", ''sdhsfjdsh.html", "xys.html", "7hsaa.html"] // user2
     * ], user1 and user2 （指定两个user求intersect）.
     * <p>
     * 输出：["xys.html", "7hsaa.html"]
     */
    /*
    substring starts from the beginning can uniquely identify a state
    if we define state[i][j] as LCCH between user1[0..i] and user2[0..j] ending at user1[i] and user2[j]
    our goal state is max(state[i][j])
    state[i][j] = state[i - 1][j - 1] + 1 if user1[i] = user2[j]
                = 0
                1 - based
     */
    public List<String> getLongestContinuousCommonHistory(String[] user1, String[] user2) {
        int len1 = user1.length, len2 = user2.length, longestHistory = 0;
        int[][] state = new int[len1 + 1][len2 + 1];
        int[] resultIndexes = new int[2];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (user1[i - 1].equals(user2[j - 1])) {
                    state[i][j] = state[i - 1][j - 1] + 1;
                } else {
                    state[i][j] = 0;
                }
                if (state[i][j] > longestHistory) {
                    longestHistory = state[i][j];
                    resultIndexes[0] = i - 1;
                    resultIndexes[1] = j - 1;
                }
            }
        }
        int resultLength = state[resultIndexes[0] + 1][resultIndexes[1] + 1], id = resultIndexes[0];
        List<String> result = new ArrayList<>();

        for (int i = resultLength - 1; i >= 0; i--)
            result.add(0, user1[id--]);

        return result;
    }


}
