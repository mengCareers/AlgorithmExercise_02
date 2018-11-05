package CompanyOriented.Yelp;

import java.util.*;
import java.util.stream.Collectors;

public class BusinessChain {
    public static void main(String[] args) {
        List<Business> businesses = new ArrayList<>();
        businesses.add(new Business("St", "Seattle", 101));
        businesses.add(new Business("PC", "SF", 102));
        businesses.add(new Business("WF", "A", 103));
        businesses.add(new Business("St", "SF", 104));
        businesses.add(new Business("PC", "A", 105));
        businesses.add(new Business("St", "A", 106));
        businesses.add(new Business("WF", "A", 103));
        businesses.add(new Business("WF", "A", 107));
        List<Chain> chains = getLocationBusiness(businesses, "A");
        for (Chain chain : chains) {
            System.out.println(chain.chain + " " + chain.freq);
        }
    }

    public static List<Chain> getLocationBusiness(List<Business> businesses, String location) {
        Set<Integer> visitedIds = new HashSet<>();
        Map<String, Integer> buinesisCounts = new HashMap<>();
        for (Business business : businesses) {
            if (visitedIds.contains(business.id) || !business.location.equals(location)) continue;
            visitedIds.add(business.id);
            buinesisCounts.put(business.name, buinesisCounts.getOrDefault(business.name, 0) + 1);
        }
        return buinesisCounts.entrySet().stream()
                .sorted((e1, e2) -> {
                    int count1 = e1.getValue(), count2 = e2.getValue();
                    if (count1 == count2) {
                        return e1.getKey().compareTo(e2.getKey());
                    } else {
                        return Integer.compare(e2.getValue(), e1.getValue());
                    }
                })
                .map(e -> new Chain(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

    }

    static class Chain {
        String chain;
        Integer freq;

        Chain(String chain, Integer freq) {
            this.chain = chain;
            this.freq = freq;
        }
    }

    static class Business {
        String name;
        String location;
        int id;

        public Business(String name, String location, int id) {
            this.name = name;
            this.location = location;
            this.id = id;
        }
    }
}
