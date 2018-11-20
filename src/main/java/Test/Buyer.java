package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Buyer {
    static class Event {
        int x, y;
        int price;
        int id;
        public Event(int id, int x, int y, int price) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.price = price;
        }
    }

    static int manhatanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static class EventComparator implements Comparator<Event> {
        int x1, y1;

        public EventComparator(int x1, int y1) {
            this.x1 = x1;
            this.y1 = y1;
        }
        @Override
        public int compare(Event e1, Event e2) {
            int m1 = manhatanDistance(e1.x, e1.y, x1, y1), m2 = manhatanDistance(e2.x, e2.y, x1, y1);
            if (m1 != m2) return m1 - m2;
            if (e1.price != e2.price) return e1.price - e2.price;
            return e1.id - e2.id;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> data = Arrays.asList(
                Arrays.asList(1, 1, 1, 40, 60),
                Arrays.asList(2, 1, 4, 50)
        );
        List<Event> events = new ArrayList<>();
        for (List<Integer> list : data) {
            int id = list.get(0), x = list.get(1), y = list.get(2);
            for (int i = 3; i < list.size(); i++) {
                events.add(new Event(id, x, y, list.get(i)));
            }
        }

        int[][] buyers = new int[][]{{3, 3}, {3, 2}, {4, 3}};
        for (int i = 0; i < buyers.length; i++) {
            int x1 = buyers[i][0], y1 = buyers[i][1];
            EventComparator eventComparator = new EventComparator(x1, y1);
            int index = 0;
            Event minEvent = events.get(0);
            for (int j = 1; j < events.size(); j++) {
                Event thisEvent = events.get(j);
                if (eventComparator.compare(thisEvent, minEvent) < 0) {
                    minEvent = thisEvent;
                    index = j;
                }
            }
            System.out.println(minEvent.id + " " + minEvent.price);
            events.remove(index);
        }
    }
}
