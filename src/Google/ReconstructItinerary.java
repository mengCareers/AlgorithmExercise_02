package Google;

import java.util.*;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order, if multiple, return the smallest lexical order
 * Thus, the itinerary must begin with JFK.
 * TP: topological, smallest lexical orde
 * directed
 * <p>
 * <p>
 * All the airports are vertices and tickets are directed edges.
 * Then all these tickets form a directed graph.
 * Thus, start from "JFK",
 * we can apply the Hierholzer's algorithm to find a Eulerian path in the graph which is a valid reconstruction.
 * <p>
 * Since the problem asks for lexical order smallest solution, we can put the neighbors in a min-heap. In this way, we always visit the smallest possible neighbor first in our trip.
 */
public class ReconstructItinerary {
    List<String> result;

    public List<String> findItinerary(String[][] tickets) {
        result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String[] ticket : tickets) {
            if (!map.containsKey(ticket[0])) {
                map.put(ticket[0], new ArrayList<>());
            }
            map.get(ticket[0]).add(ticket[1]);
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            Collections.sort(entry.getValue()); // 3.
        }
        findItineraryFrom("JFK", map, new ArrayList<>(), tickets.length);
        result.add(0, "JFK");
        return result;
    }

    private void findItineraryFrom(String start, Map<String, List<String>> map, List<String> curRes, int numTickets) {
        if (curRes.size() == numTickets) { // 1.all tickets are used up
            result.addAll(curRes);
            return;
        }
        if (map.get(start) == null) { // 2.the path can not use up all tickets
            return;
        }
        for (int i = 0; i < map.get(start).size(); i++) {
            String dest = map.get(start).get(i);
            map.get(start).remove(dest);
            curRes.add(dest);
            findItineraryFrom(dest, map, curRes, numTickets);
            if (result.size() > 0) { // 4.the first valid path is the final answer
                return;
            }
            map.get(start).add(i, dest);
            curRes.remove(curRes.size() - 1);
        }
    }
}
