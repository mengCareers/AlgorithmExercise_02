package CompanyOriented.Pinterest;

import java.util.*;

/*
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 */
public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime_SweepLine(List<List<Interval>> schedule) {

        List<Interval> result = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>(); // Key: time point, value: score.
        for (List<Interval> list : schedule) {
            for (Interval interval : list) {
                map.put(interval.start, map.getOrDefault(interval.start, 0) + 1);
                map.put(interval.end, map.getOrDefault(interval.end, 0) - 1);
            }
        }

        int start = -1, score = 0;
        for (int point : map.keySet()) {
            score += map.get(point);
            if (score == 0 && start == -1) {
                start = point;
            } else if (start != -1 && score != 0) {
                result.add(new Interval(start, point));
                start = -1;
            }
        }

        return result;
    }


    public List<Interval> employeeFreeTime_MergeInterval(List<List<Interval>> schedule) {

        List<Interval> result = new ArrayList<>();
        List<Interval> intervals = new ArrayList<>();

        for (List<Interval> list : schedule)
            intervals.addAll(list);
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        int prevEnd = intervals.get(0).end;
        for (Interval curr : intervals) {
            if (prevEnd < curr.start) {
                result.add(new Interval(prevEnd, curr.start));
            }
            prevEnd = Math.max(curr.end, prevEnd);
        }

        return result;
    }
}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}
