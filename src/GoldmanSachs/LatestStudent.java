package GoldmanSachs;

import java.util.*;

public class LatestStudent {

    private static void build_date_absoluteLateness(List<List<String>> attendanceData, Map<String, List<String>> date_absoluteLateness) {
        String date, individual;
        int absoluteLateness, avgAbsoluteLateness;

        for (List<String> data : attendanceData) {
            date = data.get(0);
            if (!date_absoluteLateness.containsKey(date)) {
                date_absoluteLateness.put(date, new ArrayList<>());
            }
            absoluteLateness = Integer.parseInt(data.get(3)) - Integer.parseInt(data.get(2));
            absoluteLateness = absoluteLateness < 0 ? 0 : absoluteLateness;
            date_absoluteLateness.get(date).add("" + absoluteLateness);
        }
    }

    private static void build_date_avgAbsoluteLateness(Map<String, List<String>> date_absoluteLateness, Map<String, Integer> date_avgAbsoluteLateness) {
        List<String> list;
        for (String key : date_absoluteLateness.keySet()) {
            list = date_absoluteLateness.get(key);
            int sum = 0;
            for (String val : list) {
                sum += Integer.parseInt(val);
            }
            date_avgAbsoluteLateness.put(key, sum / list.size());
        }
    }

    private static void build_individual_relativelateness(List<List<String>> attendanceData, Map<String, Integer> date_avgAbsoluteLateness, Map<String, Integer> individual_relativelateness, PriorityQueue<String> peopleWithMax) {

        int maxRelativeLateness = 0, absoluteLateness, avgAbsoluteLateness;
        String individual;

        for (List<String> data : attendanceData) {
            individual = data.get(1);
            absoluteLateness = Integer.parseInt(data.get(3)) - Integer.parseInt(data.get(2));
            absoluteLateness = absoluteLateness < 0 ? 0 : absoluteLateness;
            avgAbsoluteLateness = date_avgAbsoluteLateness.get(data.get(0));
            if (absoluteLateness >= avgAbsoluteLateness) {
                individual_relativelateness.put(individual, individual_relativelateness.getOrDefault(individual, 0) + absoluteLateness - avgAbsoluteLateness);
            }
            if (individual_relativelateness.get(individual) > maxRelativeLateness) {
                maxRelativeLateness = individual_relativelateness.get(individual);
                peopleWithMax.clear();
                peopleWithMax.add(individual);
            } else if (individual_relativelateness.get(individual) == maxRelativeLateness) {
                peopleWithMax.add(individual);
            }
        }
    }

    static String latestStudent(List<List<String>> attendanceData) {

        Map<String, List<String>> date_absoluteLateness = new HashMap<>();
        build_date_absoluteLateness(attendanceData, date_absoluteLateness);

        Map<String, Integer> date_avgAbsoluteLateness = new HashMap<>();
        build_date_avgAbsoluteLateness(date_absoluteLateness, date_avgAbsoluteLateness);


        PriorityQueue<String> peopleWithMax = new PriorityQueue<>();
        // The elements of the priority queue are ordered according to their natural ordering
        Map<String, Integer> individual_relativelateness = new HashMap<>();

        build_individual_relativelateness(attendanceData, date_avgAbsoluteLateness, individual_relativelateness, peopleWithMax);

        return peopleWithMax.isEmpty() ? "" : peopleWithMax.poll();
    }
}
