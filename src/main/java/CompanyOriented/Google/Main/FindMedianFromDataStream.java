package CompanyOriented.Google;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 */
public class FindMedianFromDataStream {

    PriorityQueue<Integer> minPQ; // bigger half, always poll min
    PriorityQueue<Integer> maxPQ; // smaller half, always poll max

    public FindMedianFromDataStream() {
        minPQ = new PriorityQueue<>();
        maxPQ = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        minPQ.add(num);
        maxPQ.add(minPQ.poll());
        if (minPQ.size() < maxPQ.size()) {
            minPQ.add(maxPQ.poll());
        }
    }

    public double findMedian() {
        if (minPQ.size() > maxPQ.size()) {
            return minPQ.peek();
        }
        return (minPQ.peek() + maxPQ.peek()) / 2.0;
    }
}
