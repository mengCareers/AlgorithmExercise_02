package CompanyOriented.Visa;

import java.util.*;

/*
m items to delete
id of all items
delete fewer number first, i.e.we need map id to frequencies, and frequency to list of ids

 * */
public class SmartSale {

    public static void main(String[] args) {
        SmartSale inst = new SmartSale();
        int[] ids = {1, 1, 1, 2, 2, 3};
        int type = inst.deleteProducts(ids, 1);
        System.out.println(type);
    }

    public int deleteProducts(int[] ids, int m) {

        Map<Integer, Integer> map = new HashMap<>(); // key is id, value is frequency
        for (int id : ids) {
            map.put(id, map.getOrDefault(id, 0) + 1);
        }
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>(); // key is frequency, value is list of ids
        for (int id : map.keySet()) {
            int freq = map.get(id);
            treeMap.putIfAbsent(freq, new LinkedList<>());
            treeMap.get(freq).add(id);
        }
        // remove m products
        /*
         *
         * */
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = treeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> entry = iterator.next();
            int freq = entry.getKey();
            List<Integer> list = entry.getValue();
            while (freq <= m && list.size() > 0) {
                list.remove(0);
                m -= freq;
            }
            if (list.size() == 0)
                treeMap.pollFirstEntry();
            if (m == 0) {
                break;
            }
        }

        if (m != 0)
            return 0;
        int remained = 0;
        for (List<Integer> list : treeMap.values()) {
            remained += list.size();
        }
        return remained;
    }
}
