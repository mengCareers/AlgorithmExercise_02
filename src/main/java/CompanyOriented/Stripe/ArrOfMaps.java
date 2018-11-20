package CompanyOriented.Stripe;

import java.util.*;

/*
Given an array of hashmaps to represent a DB,
each hash map is a record.
Given a key,
returnthe minimum value of a given key in different records,
 */
public class ArrOfMaps {

    private static String[][] sortOrderArr;

    public static Map<String, Integer> firstBySortOrder(LinkedHashMap<String, String> sortOrder, List<Map<String, Integer>> records) {
        // records to
        String[][] sortOrderArray = new String[sortOrder.size()][2];
        int index = 0;
        for (String key : sortOrder.keySet()) {

            String value = sortOrder.get(key);
            sortOrderArray[index][0] = key;
            sortOrderArray[index][1] = value;
            index++;
        }
        sortOrderArr = sortOrderArray;
        Collections.sort(records, new RecordComparator2());
        return records.get(0);
    }


    static class RecordComparator2 implements Comparator<Map<String, Integer>> {

        @Override
        public int compare(Map<String, Integer> left, Map<String, Integer> right) {
            int index = 0;
            int compareResult = 0;

            while (index < sortOrderArr.length && compareResult == 0) {
                String key = sortOrderArr[index][0];
                compareResult = Integer.compare(left.getOrDefault(key, 0), right.getOrDefault(key, 0));
                index++;
            }
            return sortOrderArr[index][1].equals(ASC) ? compareResult : -compareResult;
        }
    }


    public int getMinRecord_0(String key, Map<String, Integer>[] database) {

        int minValue = Integer.MAX_VALUE;
        for (Map<String, Integer> records : database) {
            minValue = Math.min(minValue, records.getOrDefault(key, Integer.MAX_VALUE));
        }

        return minValue;
    }

    public int getMinRecord_1(String key, Map<String, Integer>[] database) {
        return getRecord_0(key, ASC, database);
    }

    private final static String ASC = "asc";

    public int getRecord_0(String key, String direction, Map<String, Integer>[] database) {

        boolean isGetMin = direction.equals(ASC);
        int targetValue = isGetMin ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        int defaultValue = targetValue;

        for (Map<String, Integer> records : database) {
            int value = records.getOrDefault(key, defaultValue);
            targetValue = isGetMin ? Math.min(targetValue, value) : Math.max(targetValue, value);
        }

        return targetValue;
    }

    /*
    Converts map into a List<Map>, sorts the List<Map> with a custom Comparatpr and put it into a new insertion order map = LinkedHashMap.
    Map ---> List<Map> ---> Collections.sort() --> List<Map> (Sorted) ---> LinkedHashMap
     */
    public int getRecord_1(String key, String direction, Map<String, Integer>[] database) {

        boolean isGetMin = direction.equals(ASC);
        int defaultValue = isGetMin ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        Arrays.sort(database, new Comparator<Map<String, Integer>>() {
            @Override
            public int compare(Map<String, Integer> o1, Map<String, Integer> o2) {
                int v1 = o1.getOrDefault(key, defaultValue), v2 = o2.getOrDefault(key, defaultValue);
                return isGetMin ? Integer.compare(v1, v2) : Integer.compare(v2, v1);
            }
        });

        return database[0].get(key);
    }
//
//    {
//    {
//        String[] arr = new String[]{}; //char 3, char 2, char 5, char 4, char 1
//        //arr[0] -> human
//        int[][] order = new int[][]{{3, 0}, {2, 1}, {5, 0}, {4, 1}, {1, 1}};
//        Arrays.sort(arr, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                int charIndex = order[0][0];
//                int result = Character.compare(o1.charAt(charIndex), o2.charAt(charIndex));
//                while (result != 0 && charIndex + 1 < o1.length()) {
//                    charIndex++;
//                    result = Character.compare(o1.charAt(order[charIndex][0]), o2.charAt(order[charIndex][0]));
//                }
//                return order[charIndex][1] == 0 ? result : -result;
//            }
//        });
//    }
//
//    {
//        Map<String, Integer>[] database;
//        sortMap(new String[][]{{"key"}, {"ASC"}}, database);
//    }


    public void sortMap(String[][] keyDirectionArray, Map<String, Integer>[] database) {
        Arrays.sort(database, new Comparator<Map<String, Integer>>() {

            @Override
            public int compare(Map<String, Integer> o1, Map<String, Integer> o2) {
                int index = 0;
                int compareResult = Integer.compare(o1.get(keyDirectionArray[index][0]), o2.get(keyDirectionArray[index][0]));
                while (index < keyDirectionArray.length && compareResult == 0) { // Tie exists.
                    index++;
                    compareResult = Integer.compare(o1.get(keyDirectionArray[index][0]), o2.get(keyDirectionArray[index][0]));
                }
                return (index == keyDirectionArray.length) ? 0 :
                        keyDirectionArray[index][1].equals(ASC) ? compareResult : -compareResult;

            }
        });
    }


    public int getRecord_2(String key, String direction, Map<String, Integer>[] database) {
        String[][] keyDirectionArray = {{direction, direction}};
        sortMap(keyDirectionArray, database);
        return database[0].get(key);
    }
}
