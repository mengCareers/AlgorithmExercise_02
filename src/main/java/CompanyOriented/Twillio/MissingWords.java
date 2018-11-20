package CompanyOriented.Twillio;

import java.util.ArrayList;
import java.util.List;

public class MissingWords {
    public static void main(String[] args) {
        String s = "I am using HackerRank to improve programming";
        String t = "am HackerRank to improve";

        String[] arr1 = s.split(" ");
        String[] arr2 = t.split(" ");
        List<String> res = new ArrayList<>();
        int index1 = 0, index2 = 0;
        while (index1 < arr1.length) {
            if (index2 < arr2.length && arr1[index1].equals(arr2[index2])) {
                index1++;
                index2++;
            } else {
                res.add(arr1[index1++]);
            }
        }
        System.out.println(res);
    }
}
