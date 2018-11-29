package CompanyOriented.Google.Interview;

import java.util.*;

public class SmallestTopTermsSet {
    public Set<String> getSmallestTopTermsSet(List<String> terms) {
        Set<String> result = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();// key: terms, value: frequency of a term.
        for (String term : terms) {
            map.put(term, map.getOrDefault(term, 0) + 1);
        }
        int n = terms.size();
        double minFrequency = n * 0.01;
        List<String>[] bucket = new List[n];
        for (String term : map.keySet()) {
            if (bucket[map.get(term)] == null)
                bucket[map.get(term)] = new ArrayList<>();
            bucket[map.get(term)].add(term);
        }
        for (int i = n - 1; n >= 0; i--) {
            if (bucket[i] != null) {
                for (int j = 0; j < bucket[i].size() && minFrequency > 0; j++) {
                    minFrequency -= i;
                    result.add(bucket[i].get(j));
                }
                if (minFrequency <= 0) {
                    return result;
                }
            }
        }
        return result;
    }
}
