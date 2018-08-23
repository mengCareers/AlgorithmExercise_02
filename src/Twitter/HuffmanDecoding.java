package Twitter;

import java.util.*;

/*

 */
public class HuffmanDecoding {

    public static void main(String[] args) {
        HuffmanDecoding inst = new HuffmanDecoding();
        Map<String, String> mappings = new HashMap<>();
        mappings.put("a", "01");
        mappings.put("b", "02");
        mappings.put("c", "102");
        String ans = inst.huffmanDecoding(mappings, "102102");
        System.out.println(ans);
    }

    public String huffmanDecoding(Map<String, String> mappings, String encoded) {

        int n = encoded.length();
        Set<Integer> lengthSet = new HashSet<>();
        Map<String, String> mappingsReverse = new HashMap<>();

        for (String code : mappings.keySet()) {
            mappingsReverse.put(mappings.get(code), code);
            lengthSet.add(mappings.get(code).length());
        }

        String[] state = new String[n];
        Arrays.fill(state, "");

        for (int i = 0; i < n; i++) {
            for (int length : lengthSet) {
                if (i - length + 1 >= 0) {
                    if (mappingsReverse.containsKey(encoded.substring(i - length + 1, i + 1))) {
                        if (i - length >= 0) {
                            if (!state[i - length].isEmpty()) {
                                state[i] = state[i - length] + mappingsReverse.get(encoded.substring(i - length + 1, i + 1));
                            }
                        } else {
                            state[i] = mappingsReverse.get(encoded.substring(i - length + 1, i + 1));
                        }
                    }
                }
            }
        }

        return state[n - 1];
    }
}
