package CompanyOriented.Karat;

import java.util.*;

/*

 */
public class BadgeAccess {

    public static void main(String[] args) {
        BadgeAccess inst = new BadgeAccess();
        String[][] accesses = {{"Martha", "exit"}, {"Paul", "enter"}, {"Martha", "enter"}};
        List<List<String>> result = inst.find(accesses);
        String[][] badge_records = {{"Paul", "1355"}, {"Jen", "1910"}, {"John", "830"}, {"Paul", "1315"}, {"John", "835"},
                {"Paul", "1405"}, {"John", "930"}, {"John", "855"}, {"John", "915"}, {"Paul", "1630"}, {"John", "1630"}};
        Map<String, List<Integer>> resultMap = inst.findUnusuallyOften(badge_records);
        System.out.println(resultMap);
    }

    /**
     * find a list of people missing "enter", and a list of people missing "end"
     *
     * @param accesses
     * @return
     */
    public List<List<String>> find(String[][] accesses) {

        List<List<String>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());
        Map<String, Integer> map = new HashMap<>(); // key: name, value: enter ++, exit --
        Set<String> invalid = new HashSet<>();
        for (String[] access : accesses) {
            String name = access[0];
            if (invalid.contains(name)) {
                continue;
            }
            String verb = access[1];
            map.put(name, (verb.equals("exit")) ? (map.getOrDefault(name, 0) - 1) : (map.getOrDefault(name, 0) + 1)); // assume only enter or exit
            if (map.get(name) > 1 || map.get(name) < 0) // invalid
                invalid.add(name);
        }

        for (String name : map.keySet()) {
            if (map.get(name) > 0) {
                result.get(0).add(name);
                continue;
            }
            if (map.get(name) < 0) {
                result.get(1).add(name);
            }
        }

        return result;
    }

    Map<String, List<Integer>> map;
    Map<String, List<Integer>> result;

    public Map<String, List<Integer>> findUnusuallyOften(String[][] badge_records) {

        result = new HashMap<>(); // key: name of unusual
        map = new HashMap<>(); // key: name, value: list of times
        for (String[] record : badge_records) {
            String name = record[0];
            int time = Integer.parseInt(record[1]);
            map.putIfAbsent(name, new ArrayList<>());
            map.get(name).add(time);
        }

        for (String name : map.keySet()) {
            checkUnusual(name);
        }

        return result;
    }

    private void checkUnusual(String name) {
        List<Integer> times = map.get(name);
        Collections.sort(times);
        Set<Integer> accessTimeSet = new HashSet<>();
        for (int i = 0; i < times.size() - 2; i++) {
            int index = search(times, i, times.get(i) + 100);
            if (index < times.size() && index - i >= 3) {
                for (int j = i; j < index; j++) {
                    accessTimeSet.add(times.get(j));
                }
            }
        }
        if (!accessTimeSet.isEmpty())
            result.put(name, new ArrayList<>(accessTimeSet));
    }

    /**
     * The first index (beginning at start) that with value >=  target
     *
     * @param times
     * @param start
     * @param target
     * @return
     */
    private int search(List<Integer> times, int start, int target) {

        int lo = start, hi = times.size() - 1;
        while (lo <= hi) {
            int mi = lo + ((hi - lo) >> 1);
            if (times.get(mi) < target) {
                lo = mi + 1;
            } else {
                hi = mi - 1;
            }
        }

        return lo;
    }
}
