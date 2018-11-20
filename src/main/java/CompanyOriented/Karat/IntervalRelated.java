package CompanyOriented.Karat;

import java.util.*;

/**

 */
public class IntervalRelated {

    public static void main(String[] args) {
        IntervalRelated intervalRelated = new IntervalRelated();
        int[][] meetings = {{1300, 1500}, {930, 1200}, {830, 845}};
        // System.out.println(intervalRelated.hasNoOverlappings(meetings, 1340, 1600));
        List<Interval> idleIntervals = intervalRelated.getIdleIntervals(meetings);
        for (Interval interval : idleIntervals)
            System.out.print("[" + interval.start + ", " + interval.end + "], ");
    }

    public boolean hasNoOverlappings(int[][] meetings, int start, int end) {

        for (int[] meeting : meetings) {
            if (meeting[0] < end && meeting[1] > start) {
                return false;
            }
        }

        return true;

//        Arrays.sort(meetings, (a, b) -> (a[0] - b[0]));
//        int i = 0, n = meetings.length;
//        while (i < n - 1) {
//            if (meetings[i][1] <= start && end <= meetings[i + 1][0])
//                return true;
//            i++;
//        }
//        return end <= meetings[0][0] || meetings[i][1] <= start;
    }

    class Interval {

        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public List<Interval> getIdleIntervals(int[][] meetings) {
        List<Interval> meetingList = new ArrayList<>();
        List<Interval> idleIntervals = new ArrayList<>();
        for (int[] meeting : meetings) {
            meetingList.add(new Interval(meeting[0], meeting[1]));
        }
        Collections.sort(meetingList, (a, b) -> Integer.compare(a.start, b.start));
        int i = 0, n = meetingList.size();
        Interval curr = meetingList.get(0), next = null;
        if (curr.start > 0)
            idleIntervals.add(new Interval(0, curr.start - 1));
        while (i < n) {
            next = meetingList.get(i);
            if (next.start <= curr.end) // merge
                next.end = Math.max(curr.end, next.end);
            else
                idleIntervals.add(new Interval(curr.end + 1, next.start - 1));
            curr = next;
            i++;
        }
        return idleIntervals;
    }

    /**
     * @param intervals
     * @return Empty intervals between two intervals
     */
    public List<Interval> getRestInterval(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        Collections.sort(intervals, (a, b) -> (a.start - b.start));
        int curStart = intervals.get(0).start, curEnd = intervals.get(0).end;
        if (curStart > 0)
            result.add(new Interval(0, curStart - 1));
        for (Interval nextInterval : intervals) {
            if (nextInterval.start <= curEnd) {
                curEnd = Math.max(curEnd, nextInterval.end);
            } else {
                result.add(new Interval(curEnd + 1, nextInterval.start - 1));
                curEnd = nextInterval.end;
            }
        }
        return result;
    }


}
