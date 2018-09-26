package CompanyOriented.Visa;

import java.util.PriorityQueue;

/*
 *
 * */
public class FetchItemsToDisplay {

    public static void main(String[] args) {
        FetchItemsToDisplay inst = new FetchItemsToDisplay();
        String[][] items = {
                {"item1", "10", "15"}, {"item2", "3", "4"}, {"item3", "17", "8"}
        };
        String[] ans = inst.fetchItemsToDisplay(items, 1, 0, 2, 1);
        for (String str : ans)
            System.out.println(str);
    }

    // item : {name, relevance, price}
    public String[] fetchItemsToDisplay(String[][] items, int sortParameter, int sortOrder, int x, int y) {
        PriorityQueue<String[]> priorityQueue = (sortOrder) == 0 ? new PriorityQueue<>((l1, l2) -> (Integer.parseInt(l1[sortParameter]) - Integer.parseInt(l2[sortParameter]))) :
                new PriorityQueue<>((l1, l2) -> (Integer.parseInt(l2[sortParameter]) - Integer.parseInt(l1[sortParameter])));
        for (String[] item : items) {
            priorityQueue.add(item);
        }
        String[] names = new String[x]; // not necessarily
        // from index x * y +
        int i = 0;
        while (i != x * y && !priorityQueue.isEmpty()) {
            priorityQueue.poll();
            i++;
        }
        i = 0;
        while (i <= x && !priorityQueue.isEmpty()) {
            names[i] = priorityQueue.poll()[0];
            i++;
        }
        return names;
    }
}
