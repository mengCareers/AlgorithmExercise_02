package CompanyOriented.BlackRock;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Share {
    private static double sumValue(Map<String, Property> map) {
        double sum = 0;
        for (Property property : map.values()) sum += property.value();
        return sum;
    }

    private static Map<String, Integer> calcDesiredValues(Map<String, Property> mapPort,
                                                          Map<String, Property> mapBench) {

        Map<String, Integer> desiredValues = new HashMap<>();
        double benchSum = sumValue(mapBench);
        double portSum = sumValue(mapPort);
        for (Map.Entry<String, Property> entry : mapBench.entrySet()) {
            desiredValues.put(entry.getKey(), (int) (portSum * entry.getValue().value() / benchSum));
        }
        return desiredValues;
    }

    private static void solution(String input) {
        String[] parts = input.split(":");
        String[] stocks0 = parts[0].split("\\|"), stocks1 = parts[1].split("\\|");
        Map<String, Property> mapPort = initializeMap(stocks0), mapBench = initializeMap(stocks1);
        Map<String, Integer> desiredValues = calcDesiredValues(mapPort, mapBench);
        Map<String, Integer> buys = new HashMap<>();
        Set<String> allNames = new HashSet<>(mapPort.keySet());
        allNames.addAll(mapBench.keySet());
        for (String name : allNames) {
            Property property = mapPort.getOrDefault(name, null);
            int desiredValue = desiredValues.getOrDefault(name, 0);
            int buy = 0;
            if (property.value() > desiredValue) {
                while (property.value() > desiredValue) {
                    property.share--;
                    buy--;
                }
            } else if (property.value() < desiredValue) {
                while (property.value() < desiredValue) {
                    property.share++;
                    buy++;
                }
            }
            buys.put(name, buy);
        }

        for (Map.Entry<String, Integer> entry : buys.entrySet()) {
            int buy = entry.getValue();
            if (buy > 0) {
                System.out.println("BUY," + entry.getKey() + "," + buy);
            } else if (buy < 0) {
                System.out.println("SELL," + entry.getKey() + "," + (-buy));
            }
        }
    }

    private static Map<String, Property> initializeMap(String[] stocks) {
        Map<String, Property> map = new HashMap<>();
        for (String stock : stocks) {
            String[] fields = stock.split(",");
            map.put(fields[0], new Property(Type.valueOf(fields[1]),
                    Integer.parseInt(fields[2]), Integer.parseInt(fields[3]),
                    Double.parseDouble(fields[4])));
        }
        return map;
    }

    enum Type {
        STOCK, BOND
    }

    static class Property {
        Type type;
        int share;
        int price;
        double interest;

        Property(Type type, int share, int price, double interest) {
            this.type = type;
            this.share = share;
            this.price = price;
            this.interest = interest;
        }

        int value() {
            if (type == Type.STOCK) return share * price;
            else return (int) (share * (price + interest) * 0.01);
        }
    }

    public static void main(String args[]) {
        solution("Vodafone,STOCK,10,50,0|CompanyOriented.Google,STOCK,15,50,0|Microsoft,BOND,15,100,0.05:Vodafone,STOCK,15,50,0|CompanyOriented.Google,STOCK,10,50,0|Microsoft,BOND,15,100,0.05");
    }
}
