package CompanyOriented.Karat;

import java.util.*;

/*

 */
public class BadgeAccess {

    public static void main(String[] args) {
        BadgeAccess inst = new BadgeAccess();
        String[][] accesses = {{"Martha", "exit"}, {"Paul", "enter"}, {"Martha", "enter"}};
        List<List<String>> result = inst.findMismatchedEntries(accesses);
        System.out.println(result);
        String[][] badge_records = {{"Paul", "1355"}, {"Jen", "1910"},
                {"John", "830"}, {"Paul", "1315"}, {"John", "835"},
                {"Paul", "1405"}, {"John", "930"}, {"John", "855"},
                {"John", "915"}, {"Paul", "1630"}, {"John", "1630"}};
        Map<String, List<Integer>> resultMap = inst.findUnusuallyOften(badge_records);
        System.out.println(resultMap);
    }

    private final static String EXIT = "exit";

    public List<List<String>> findMismatchedEntries(String[][] accesses) {

        List<String> missExit = new ArrayList<>(); // List of employees who didn't use badge while exiting.
        List<String> missEnter = new ArrayList<>(); // List of employees who didn't use badge while entering
        List<List<String>> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>(); // key: name, value: score.
        Set<String> mismatchedEmployees = new HashSet<>();

        for (String[] access : accesses) {
            String name = access[0];
            if (mismatchedEmployees.contains(name))
                continue;
            map.put(name, access[1].equals(EXIT) ? map.getOrDefault(name, 0) - 1 : map.getOrDefault(name, 0) + 1);
            if (map.get(name) < 0 || map.get(name) > 1) {
                mismatchedEmployees.add(name);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() < 0)
                missEnter.add(entry.getKey());
            else if (entry.getValue() > 0)
                missExit.add(entry.getKey());
        }
        result.add(missEnter);
        result.add(missExit);
        return result;
    }

    public Map<String, List<Integer>> findUnusuallyOften(String[][] accesses) {
        Map<String, List<Integer>> result = new HashMap<>();
        Map<String, List<Integer>> map = new HashMap<>(); // Key: name, value: list of access time.
        String name;
        for (String[] access : accesses) {
            name = access[0];
            map.putIfAbsent(name, new ArrayList<>());
            map.get(name).add(Integer.parseInt(access[1]));
        }
        List<Integer> list, resultTime;
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            list = entry.getValue();
            Collections.sort(list);
            for (int i = 0; i < list.size() - 2; i++) {
                // The index of the first number which is >= list.get(i) + 100.
                int index = search(list, i, list.get(i) + 100);
                if (index - i >= 3) {
                    resultTime = new ArrayList<>();
                    for (int j = i; j < index; j++)
                        resultTime.add(list.get(j));
                    result.put(entry.getKey(), resultTime);
                    break;
                }
            }
        }
        return result;
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
