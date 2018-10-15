package CompanyOriented.Pinterest;

/*
Given list of intervals,
if two intervals overlap, the section overlapped will become new interval
with weight equals to sum of two intervals' weight.
------
    -----
     --
 */
public class SubstractOverlapped {


    class Interval {
        int start;
        int end;
        int weight;

        public Interval(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }


}
