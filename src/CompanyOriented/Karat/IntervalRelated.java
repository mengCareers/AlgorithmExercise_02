package CompanyOriented.Karat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**

 */
public class IntervalRelated {


    /**
     * @param meetings
     * @param start
     * @param end
     * @return true if [start, end] has no overlap with meetings[i]
     */
    public boolean canArrange(int[][] meetings, int start, int end) {
        for (int[] meeting : meetings)
            if (meeting[0] < end && meeting[1] > start)
                return false;
        return true;
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

    class Interval {

        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
