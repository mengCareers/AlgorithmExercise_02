package CompanyOriented.Pinterest;

import java.util.*;

public class PinArrangement {
    public List<List<Pin>> getColumnPins(int k, List<Pin> pins) {
        List<List<Pin>> result = new LinkedList<>();
        Map<Integer, List<Pin>> resultMap = new TreeMap<>(); // key: columnId; values: list of pins in the column.
        Map<Integer, Double> columnToHeight = new HashMap<>(); // key: columnId; values: height of column.
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((a, b) -> (Double.compare(columnToHeight.getOrDefault(a, 0.0), columnToHeight.getOrDefault(b, 0.0)))); // Min-heap stores column heights.
        for (int i = 0; i < k; i++)
            minHeap.offer(i);
        for (Pin pin : pins) {
            int columnId = minHeap.poll();
            columnToHeight.put(columnId, columnToHeight.getOrDefault(columnId, 0.0) + pin.height);
            resultMap.putIfAbsent(columnId, new LinkedList<>());
            resultMap.get(columnId).add(pin);
            minHeap.offer(columnId);
        }
        for (List<Pin> list : resultMap.values())
            result.add(list);
        return result;
    }

    static class Pin {
        int pinId;
        double height;

        public Pin(int pinId, double height) {
            this.pinId = pinId;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        PinArrangement inst = new PinArrangement();
        Pin[] pinArray = {new Pin(0, 10), new Pin(1, 2), new Pin(2, 4), new Pin(3, 4)};
        List<Pin> pins = Arrays.asList(pinArray);
        List<List<Pin>> answer = inst.getColumnPins(3, pins); // 0  1,3  2
        int columnId = 0;
        for (List<Pin> list : answer) {
            System.out.println("Column ID: " + columnId++ + ", Pin IDs are as below: ");
            for (Pin pin : list)
                System.out.print(pin.pinId + ", ");
            System.out.println();
        }
    }
}
