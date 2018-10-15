package CompanyOriented.Pinterest;

import java.util.List;

/*
Given WeightedInterval, return a list of overlaps.
An overlap is the intersection of two overlapping intervals, and its weight equal to sum of two overlapping intervals.
 */
/*
 -
 -
  --

 */
public class WeighedIntervalOverlapping {
    public List<Interval> buildWeightedOverlaps(List<Interval> intervals) {
        return null;
    }
}

class WeightedInterval {
    int start;
    int end;
    int weight;

    public WeightedInterval(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}