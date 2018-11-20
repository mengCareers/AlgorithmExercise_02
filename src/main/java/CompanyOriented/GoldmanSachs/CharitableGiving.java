package CompanyOriented.GoldmanSachs;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
company denote to charity A, B, Cwhich has received the least money so far
given money company earned
output array of charities company will denote to
if charities tie, company select first alphabetically
 */
public class CharitableGiving {

    public static void main(String[] args) {
        int[] money = {25, 8};

        CharitableGiving inst = new CharitableGiving();
        String[] result = inst.giving(money);

        for (String tmp : result)
            System.out.print(tmp + " ");
    }

    public String[] giving(int[] money) {

        int n = money.length;
        PriorityQueue<int[]> charity_earned = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        charity_earned.add(new int[]{0, 0}); // A
        charity_earned.add(new int[]{1, 0}); // B
        charity_earned.add(new int[]{2, 0}); // C
        String[] result = new String[money.length];

        for (int i = 0; i < money.length; i++) {
            int[] tmp = charity_earned.poll();
            result[i] = String.valueOf((char) ('A' + tmp[0]));
            tmp[1] += money[i];
            charity_earned.add(tmp);
        }

        return result;
    }
}
