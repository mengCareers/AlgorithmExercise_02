package CompanyOriented.Pinterest;

import java.util.*;

/*
Given k columns and a list of pins,
we always put the pin with the highest score to the lowest column,
return List<List<Pin>> to represent pins in each column.
*/
/*
How do we get the pin with the highest score?
Using PQ maxheap
How do we get the lowest column?
Using PQ minheap.
 */
/*
Follow-up:
用户的手机屏幕只有M长，如果屏幕的顶点距离主页顶点距离为K
|-------|
|_K_____|
|x	    |
|x	    |
|x	    |
|-------|
pins height = M-K = 10 - 3 = 7
for col, 2, 4, 3,
2 + 4 < 7 < 2 + 4 + 3
so 2, 4 viewable
 */
public class PinArrangement {


    public List<List<Pin>> getColumnPins(int k, List<Pin> pins) {

        if (k == 0 || pins == null || pins.isEmpty())
            return new ArrayList<>();

        List<List<Pin>> result = new ArrayList<>();
        PriorityQueue<Pin> maxHeap = new PriorityQueue<Pin>((a, b) -> (Double.compare(b.score, a.score))); // Max-heap stores Pins.
        Map<Integer, Double> columnToHeight = new HashMap<>(); // key: columnId; values: height of column.

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int compareResult = Double.compare(columnToHeight.getOrDefault(a, 0.0), columnToHeight.getOrDefault(b, 0.0));
                return (compareResult == 0) ? Integer.compare(a, b) : compareResult;
            }
        }); // Min-heap stores column heights.

        for (int i = 0; i < k; i++) {
            result.add(new ArrayList<>());
            minHeap.offer(i);
        }

        for (Pin pin : pins) {
            maxHeap.offer(pin);
        }

        while (!maxHeap.isEmpty()) {
            Pin curPin = maxHeap.poll();
            int columnId = minHeap.poll();

            columnToHeight.put(columnId, columnToHeight.getOrDefault(columnId, 0.0) + curPin.height);

            result.get(columnId).add(curPin);
            minHeap.offer(columnId);
        }

        return result;
    }


    public List<List<Pin>> getVisiblePins(List<List<Pin>> pins, double M, double K) {

        // Corner cases to add.
        if (pins == null || pins.size() == 0 || M - K <= 0)
            return new ArrayList<>();

        List<List<Pin>> result = new ArrayList<>(); // The final result.

        double[] distArray; // Each pin's bottom to the top of homepage.
        List<Pin> colOfPins; // Current column of pins assigned.

        for (int i = 0; i < pins.size(); i++) {
            result.add(new ArrayList<>());
            colOfPins = pins.get(i);
            if (colOfPins.isEmpty())
                continue;
            int numPins = colOfPins.size();
            distArray = new double[numPins];
            distArray[0] = colOfPins.get(0).height;
            for (int j = 1; j < numPins; j++) {
                distArray[j] = distArray[j - 1] + colOfPins.get(j).height;
            }
            int numVisiblePins = binarySearch(M - K, distArray);
            for (int j = 0; j < numVisiblePins; j++)
                result.get(i).add(colOfPins.get(j));
        }

        return result;
    }

    /*
    Find the first element in distArray that's bigger than homepageHeight.
     */
    private int binarySearch(double homepageHeight, double[] distArray) {
        int lo = 0, hi = distArray.length - 1;
        while (lo <= hi) {
            int mi = lo + ((hi - lo) >> 1);
            if (distArray[mi] <= homepageHeight) {
                lo++;
            } else {
                hi--;
            }
        }
        return lo;
    }


    static class Pin {
        int pinId;
        double height;
        double score;

        public Pin(int pinId, double height, double score) {
            this.pinId = pinId;
            this.height = height;
            this.score = score;
        }
    }

    public static void main(String[] args) {
        PinArrangement inst = new PinArrangement();

        Pin[] pinArray = {
                new Pin(0, 10, 2),
                new Pin(1, 2, 1),
                new Pin(2, 4, 3),
                new Pin(3, 4, 4)};
        List<Pin> pins = Arrays.asList(pinArray);
        List<List<Pin>> columnsOfPins = inst.getColumnPins(2, pins);

        int columnId = 0;
        for (List<Pin> list : columnsOfPins) {
            System.out.println("Column ID: " + columnId++ + ", Pin IDs are as below: ");
            for (Pin pin : list)
                System.out.print(pin.pinId + ", ");
            System.out.println();
        }

        double M = 10.0, K = 5.0;
        List<List<Pin>> columnsOfVisiblePins = inst.getVisiblePins(columnsOfPins, M, K);
        for (List<Pin> list : columnsOfVisiblePins) {
            System.out.print("Column ID: " + columnId++ + ", Pin IDs visible: ");
            for (Pin pin : list)
                System.out.print(pin.pinId + ", ");
            System.out.println();
        }
    }
}
