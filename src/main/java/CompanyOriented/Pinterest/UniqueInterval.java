package CompanyOriented.Pinterest;

import java.util.*;

/*
Given a list of intervals, return non-overlapping intervals.

 */
/*
Sweep-line works when we need to get number of intersections.
Or else,
since there are two influencing factors of an interval, we fix order of ones by sorting intervals by starts increasingly.
It is always easier to get the space without intervals (i.e free time).
As for getting a list of intervals
    - non-overlapping intervals
    - merged intervals
  we init a result list,
    add interval to list if its start is done,
    in next round, remove last one from list called prev, compare with curr, if no overlap,
        prev end is done, add to result
        curr start is done, add to resulat
 */
public class UniqueInterval {

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>(Arrays.asList(
                new Interval(0, 2),
                new Interval(3, 4),
                new Interval(1, 3),
                new Interval(6, 8),
                new Interval(9, 10),
                new Interval(10, 12)));
//        List<Interval> resultList = getNonOverlapped(intervals);

        List<Interval> resultList = getMaxNonOverlappedSet(intervals);
        for (Interval interval : resultList) {
            System.out.println(interval.start + ", " + interval.end);
        }
    }

    public static List<Interval> getNonOverlapped(List<Interval> intervals) {
        List<Interval> resultList = new ArrayList<>();
        Collections.sort(intervals, (a, b) -> (a.start - b.start));

        resultList.add(intervals.get(0)); // As long as it doesn't overlap with its previous.

        Interval prev = null, prevMerged = null;

        for (Interval cur : intervals) { // the first
            if (prevMerged != null) {
                if (prevMerged.end > cur.start) { // Overlapped case.
                    prevMerged = new Interval(prevMerged.start, Math.max(prevMerged.end, cur.end));
                } else {
                    prevMerged = null;
                    resultList.add(cur);
                }
            } else {
                prev = resultList.remove(resultList.size() - 1);
                if (prev.end > cur.start) { // Overlapped case.
                    prevMerged = new Interval(prev.start, Math.max(prev.end, cur.end));
                } else {
                    resultList.add(prev);
                    resultList.add(cur);
                }
            }
        }
        return resultList;
    }

    /*
    Sort by end.
    earliest end can have more non overlapped following.
    so we remove impossible by comparing with earliest end, put end to result list
    and pick next earliest end
     */
    public static List<Interval> getMaxNonOverlappedSet(List<Interval> intervals) {

        List<Interval> resultList = new ArrayList<>();

        Collections.sort(intervals, (a, b) -> (a.end - b.end));
        Interval endEarliest;
        Iterator iterator = intervals.iterator();

        while (iterator.hasNext()) {
            endEarliest = (Interval) iterator.next();
            iterator.remove();

            while (iterator.hasNext()) {
                if (endEarliest.end >= ((Interval) iterator.next()).start) {
                    iterator.remove();
                }
            }
            resultList.add(endEarliest);
            iterator = intervals.iterator();
        }
        return resultList;
    }
}
