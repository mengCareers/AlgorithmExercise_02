package CompanyOriented.Stripe;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/*
Given an array of hashmaps to represent a DB,
each hash map is a record.
Given a key,
returnthe minimum value of a given key in different records,
 */
public class ArrOfMaps {
    public int getMinRecord_0(String key, Map<String, Integer>[] database) {

        int minValue = Integer.MAX_VALUE;
        for (Map<String, Integer> records : database) {
            minValue = Math.min(minValue, records.get(key));
        }

        return minValue;
    }

    public int getMinRecord_1(String key, Map<String, Integer>[] database) {
        return getRecord_0(key, ASC, database);
    }

    private final static String ASC = "asc";

    public int getRecord_0(String key, String direction, Map<String, Integer>[] database) {

        boolean isGetMin = direction.equals(ASC);
        int targetValue = (isGetMin) ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        for (Map<String, Integer> records : database) {
            targetValue = (isGetMin) ? Math.min(targetValue, records.get(key)) : Math.max(targetValue, records.get(key));
        }

        return targetValue;
    }

    /*
    Converts map into a List<Map>, sorts the List<Map> with a custom Comparatpr and put it into a new insertion order map = LinkedHashMap.
    Map ---> List<Map> ---> Collections.sort() --> List<Map> (Sorted) ---> LinkedHashMap
     */
    public int getRecord_1(String key, String direction, Map<String, Integer>[] database) {

        boolean isGetMin = direction.equals(ASC);

        Arrays.sort(database, new Comparator<Map<String, Integer>>() {
            @Override
            public int compare(Map<String, Integer> o1, Map<String, Integer> o2) {
                return isGetMin ? Integer.compare(o1.get(key), o2.get(key)) : Integer.compare(o2.get(key), o1.get(key));
            }
        });

        return database[0].get(key);
    }


    private String[][] keyDirectionArray;
//    private static int index;

    public void sortMap(String[][] keyDirectionArray, Map<String, Integer>[] database) {

//        index = 0;
        this.keyDirectionArray = keyDirectionArray;
//        Arrays.sort(database, new Comparator<Map<String, Integer>>() {
//            @Override
//            public int compare(Map<String, Integer> o1, Map<String, Integer> o2) {
//                int compareResult = Integer.compare(o1.get(keyDirectionArray[index][0]), o2.get(keyDirectionArray[index][0]));
//                while (index < keyDirectionArray.length && compareResult == 0) { // Tie exists.
//                    index++;
//                    compareResult = Integer.compare(o1.get(keyDirectionArray[index][0]), o2.get(keyDirectionArray[index][0]));
//                }
//                return (index == keyDirectionArray.length) ? 0 :
//                        keyDirectionArray[index][1].equals(ASC) ? compareResult : -compareResult;
//
//            }
//        });
        Arrays.sort(database, new RecordComparator());
    }

    public class RecordComparator implements Comparator<Map<String, Integer>> {

        int index = 0;

        @Override
        public int compare(Map<String, Integer> o1, Map<String, Integer> o2) {
            int compareResult = Integer.compare(o1.get(keyDirectionArray[index][0]), o2.get(keyDirectionArray[index][0]));
            while (index < keyDirectionArray.length && compareResult == 0) { // Tie exists.
                index++;
                compareResult = Integer.compare(o1.get(keyDirectionArray[index][0]), o2.get(keyDirectionArray[index][0]));
            }
            return (index == keyDirectionArray.length) ? 0 :
                    keyDirectionArray[index][1].equals(ASC) ? compareResult : -compareResult;
        }
    }

    public int getRecord_2(String key, String direction, Map<String, Integer>[] database) {
        String[][] keyDirectionArray = {{direction, direction}};
        sortMap(keyDirectionArray, database);
        return database[0].get(key);
    }
}
