package CompanyOriented.Karat;

import java.util.*;

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

        String[][] domainClicks = {{"google.com", "60"}, {"yahoo.com", "50"}, {"sports.yahoo.com", "80"}};
        System.out.println(inst.countDomainClicks(domainClicks));

        String[] user1 = {"3234.html", "xys.html", "7hsaa.html"};

        String[] user2 = {"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"};
        List<String> result = inst.getLongestCommonSubarray(new ArrayList<>(Arrays.asList(user1)), new ArrayList<>(Arrays.asList(user2)));
        System.out.println(result);
    }

    private List<String> getSubdomains(String domain) {

        int startIndex = 0, n = domain.length();

        List<String> result = new ArrayList<>();
        result.add(domain);
        while (startIndex < n) {
            int dotPosition = domain.indexOf(".", startIndex);
            if (dotPosition == -1)
                break;
            result.add(domain.substring(dotPosition + 1));
            startIndex = dotPosition + 1;
        }

        return result;
    }

    public List<List<String>> countDomainClicks(String[][] domainClicks) {

        List<List<String>> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>(); // Key: subdomain/domain, value: clicks.
        for (String[] domainClick : domainClicks) {
            List<String> subdomains = getSubdomains(domainClick[0]);
            for (String subdomain : subdomains) {
                map.put(subdomain, map.getOrDefault(subdomain, 0) + Integer.parseInt(domainClick[1]));
            }
        }
        List<String> curList;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            curList = new ArrayList<>();
            curList.add(entry.getKey());
            curList.add("" + entry.getValue());
            result.add(curList);
        }

        return result;
    }

    public List<String> getLongestCommonSubarray(List<String> history1, List<String> history2) {

        int m = history1.size(), n = history2.size(), maxLen = 0, endingIndex = -1;
        int[][] state = new int[m][n]; // state[i][j] is longest common subarray from history1[0..m] ending at m to history2[0..n] ending at n

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (history1.get(i).equals(history2.get(j))) {
                    if (i == 0 || j == 0)
                        state[i][j] = 1;
                    else
                        state[i][j] = state[i - 1][j - 1] + 1;
                    if (state[i][j] > maxLen) {
                        maxLen = state[i][j];
                        endingIndex = i;
                    }
                }
            }
        }

        if (endingIndex == -1)
            return new ArrayList<>();

        List<String> result = new ArrayList<>();
        for (int i = endingIndex - maxLen + 1; i <= endingIndex; i++) {
            result.add(history1.get(i));
        }

        return result;
    }


}
