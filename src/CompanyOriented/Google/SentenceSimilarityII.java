package CompanyOriented.Google;

import java.util.HashMap;
import java.util.Map;

/**
 * input : words1, words2, similar word pairs
 * output: true if two sentences are similar
 * similarity relation is transitive (connected component)
 * similarity relation is symmetric  (undirected)
 * we can't use dp
 * If we apply Union Find,
 * What will be the root, a word
 * if each word as a an element
 */
public class SentenceSimilarityII {

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }

        Map<String, Integer> map = new HashMap<>();
        constructMap(pairs, map);

        UF uf = new UF(map.keySet().size());
        constructUF(pairs, map, uf);

        return compareSentences(words1, words2, map, uf);
    }

    private void constructMap(String[][] pairs, Map<String, Integer> map) {
        int mi = 0;
        for (String[] pair : pairs) {
            if (!map.containsKey(pair[0])) {
                map.put(pair[0], mi++);
            }
            if (!map.containsKey(pair[1])) {
                map.put(pair[1], mi++);
            }
        }
    }

    private void constructUF(String[][] pairs, Map<String, Integer> map, UF uf) {
        for (String[] pair : pairs) {
            int a = map.get(pair[0]);
            int b = map.get(pair[1]);
            uf.union(a, b);
        }
    }

    private boolean compareSentences(String[] words1, String[] words2, Map<String, Integer> map, UF uf) {
        for (int i = 0; i < words1.length; i++) {
            String w1 = words1[i];
            String w2 = words2[i];
            if (!map.containsKey(w1) || !map.containsKey(w2)) {
                if (!w1.equals(w2)) {
                    return false;
                }
                continue;
            }
            int i1 = map.get(w1);
            int i2 = map.get(w2);
            if (uf.find(i1) != uf.find(i2)) {
                return false;
            }
        }
        return true;
    }

}

class UF {
    int[] parent;

    public UF(int cnt) {
        parent = new int[cnt];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    public int find(int a) {
        while (a != parent[a]) {
            a = parent[a];
        }
        return a;
    }
}