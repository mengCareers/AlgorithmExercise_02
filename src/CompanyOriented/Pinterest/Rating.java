package CompanyOriented.Pinterest;

/*
Given N ratings in total,
implement updateRating() and getTopKRating().
getTopKRating() should take O(1) time complexity.
 */
/*
getTopKRating use MaxHeap
heap getMax O(1)
O(K)
List k max until now
heap n - k
 */
public class Rating {
//    Map<name, Node> map
//    PriorityQueue<Node> pq = new PriorityQueue((n1, n2) -> n1.rating - n2.rating)) // minheap
//
//    updateRating(name, rating) {
//        if (map.contains(name)) {
//            Node n = map.get(name)
//            n.rating = rating
//            pq.remove(n) // O(logn)
//            pq.offer(n)
//        } else {
//            Node n = new Node(name, rating)
//            map.put(name, n)
//            pq.offer(n)
//            if (pq.size > k) pq.poll()
//        }
//    }
//
//    getTopK() {
//        return new ArrayList<>(pq);
//    }
}
