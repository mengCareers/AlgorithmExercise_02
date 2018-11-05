package CompanyOriented.Google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AddBoldTaginString {
    public String addBoldTag(String s, String[] dict) {

        List<Interval> list = buildIntervalList(s, dict);

        if (list == null) {
            return s;
        }

        List<Interval> mergedList = mergeIntervalList(list);

        return buildAnswer(s, mergedList);

    }

    private static List<Interval> buildIntervalList(String s, String[] dict) {
        List<Interval> list = new ArrayList<>();
        for (String word : dict) {
            for (int i = 0; i < s.length(); i++) {
                if (word.charAt(0) == s.charAt(i)) {
                    if (i + word.length() <= s.length() && s.substring(i, i + word.length()).equals(word)) {
                        list.add(new Interval(i, i + word.length() - 1));
                    }
                }
            }
        }
        if (list.size() == 0) {
            return null;
        }
        Collections.sort(list, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.st == o2.st) {
                    return o1.en - o2.en;
                }
                return o1.st - o2.st;
            }
        });
        return list;
    }

    private static List<Interval> mergeIntervalList(List<Interval> list) {
        List<Interval> merged = new ArrayList<>();
        merged.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            Interval nxt = list.get(i);
            Interval cur = merged.remove(merged.size() - 1);
            if (cur.en >= nxt.st - 1) {
                merged.add(new Interval(Math.min(cur.st, nxt.st), Math.max(cur.en, nxt.en)));
            } else {
                merged.add(cur);
                merged.add(nxt);
            }
        }
        return merged;
    }

    private static String buildAnswer(String s, List<Interval> merged) {
        StringBuilder sb = new StringBuilder();
        int mi = 0;
        int si = 0;
        while (si < s.length() && mi < merged.size()) {
            while (si < merged.get(mi).st) {
                sb.append(s.charAt(si));
                si++;
            }
            sb.append("<b>");
            sb.append(s, si, merged.get(mi).en + 1);
            sb.append("</b>");
            si = merged.get(mi).en + 1;
            mi++;
        }
        while (si < s.length()) {
            sb.append(s.charAt(si));
            si++;
        }
        return sb.toString();
    }

}

class Interval {
    int st;
    int en;

    public Interval(int st, int en) {
        this.st = st;
        this.en = en;
    }
}