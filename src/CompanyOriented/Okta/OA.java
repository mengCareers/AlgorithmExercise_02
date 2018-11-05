package CompanyOriented.Okta;

import java.util.*;

public class OA {

    public static void main(String[] args) {
        String[] A = {
                "client1 0",
                "client1 15",
                "client1 30",
                "client1 45",

                "client1 60",
                "client1 75",
                "client1 90",

                "client1 120",

                "client1 180",

                "client1 240",

                "client2 0",

                "client2 60",

                "client2 120",

                "client2 180",

                "client2 240",

                "client2 320"
        };
        OA inst = new OA();
        String[] result = inst.solution(A, 3);
        for (String res : result)
            System.out.println(res);
    }

    TreeMap<Integer, Map<String, Integer>> map; // key: ts/60, value: <name, number of allowed requests>

    public String[] solution(String[] A, int Y) {

        map = new TreeMap<>();
        Map<String, Integer> name_requests = new TreeMap<>();
        String[] splits;
        String name;

        for (String record : A) {
            splits = record.split(" ");
            name = splits[0];
            int mapid = Integer.valueOf(splits[1]) / 60;
            map.putIfAbsent(mapid, new HashMap<>());
            map.get(mapid).put(name, Math.min(map.get(mapid).getOrDefault(name, 0) + 1, Y));
        }
        // Sliding window [i, j)
        Map<String, Integer> name_num = new HashMap<>();
        // Key: name, value: count of requests in current time window 300s.
        int j = 0, valuesSum = 0;
        for (int i = 0; i <= map.lastKey(); i++) {

            while (j < i + 5) {
                if (!map.containsKey(j)) {
                    j++;
                    continue;
                }
                for (String curname : map.get(j).keySet()) {
                    int value = name_num.getOrDefault(curname, 0) + map.get(j).get(curname);
                    name_num.put(curname, value);
                    valuesSum += map.get(j).get(curname);
                }
                j++;
            }

            if (valuesSum >= 10) {
                for (String curname : name_num.keySet()) {
                    if (name_num.get(curname) > valuesSum * 0.5) {
                        if (j == map.lastKey() + 1)
                            continue;
                        if (map.containsKey(j - 1))
                            map.get(j - 1).remove(curname);
                        if (map.containsKey(j - 2))
                            map.get(j - 2).remove(curname);
                    }
                }
            }
            for (String curname : map.get(i).keySet()) {
                valuesSum -= map.get(i).get(curname);
                name_num.remove(curname);
            }
            map.remove(i);
        }

        for (int key : map.keySet()) {
            for (String curname : map.get(key).keySet())
                name_requests.put(curname, name_requests.getOrDefault(curname, 0) + map.get(key).get(curname));
        }

        String[] result = new String[name_requests.size()];
        int i = 0;
        for (String curname : name_requests.keySet())
            result[i++] = curname + " " + name_requests.get(curname);

        return result;
    }
}
