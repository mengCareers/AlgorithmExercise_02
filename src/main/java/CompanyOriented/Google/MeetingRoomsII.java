package CompanyOriented.Google;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 It is inductive that for any interval, we ought to compare the one with the earliest end and the one with the earliest start after that.
 The earliest end makes it most likely to hold the two meetings in the same meeting room, and the earliest starting after the earliest end ensures that we waste the least meeting rooms.
 PriorityQueue<Interval> meetingRooms stores the meeting rooms needed. And it should be sorted by the ends of intervals. We also sort the Interval[] intervals by the starts of intervals.
 We compare meetingRooms.poll().end and intervals[i].start, and update the meetingRooms on the fly.
 */
public class MeetingRoomsII {

    public int minMeetingRooms(Interval[] intervals) {

        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        PriorityQueue<Interval> meetingRooms = new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.end == o2.end) {
                    return o1.start - o2.start;
                }
                return o1.end - o2.end;
            }
        });
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start == o2.start) {
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });

        meetingRooms.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval cur = meetingRooms.poll();
            if (cur.end <= intervals[i].start) {
                cur.end = intervals[i].end;
                meetingRooms.add(cur);
            } else {
                meetingRooms.add(intervals[i]);
                meetingRooms.add(cur);
            }
        }
        return meetingRooms.size();
    }

    class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}



