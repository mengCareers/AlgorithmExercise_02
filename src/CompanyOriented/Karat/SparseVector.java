package CompanyOriented.Karat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SparseVector {

    public static void main(String[] args) {
        SparseVector v1 = new SparseVector(5);
        v1.set(0, 4.0);
        v1.set(1, 5.0);
        System.out.println("v1 " + v1);
        SparseVector v2 = new SparseVector(5);
        v2.set(1, 2.0);
        v2.set(3, 3.0);
        System.out.println("v2 " + v2);
        SparseVector v3 = new SparseVector(2);

        System.out.println(v1.add(v2));
//        System.out.println(v1.add(v3));

        System.out.println(v1.dot(v2));
//        System.out.println(v1.dot(v3));

        System.out.println(v1.cos(v2));
//        System.out.println(v1.cos(v3));

    }

    public void checkLengthEqual(SparseVector v) {
        if (maxKey != v.maxKey)
            throw new Error("Vector lengths don’t match");
    }

    public Map<Integer, Double> sparseMap;
    public int maxKey;

    public SparseVector(int size) {
        sparseMap = new HashMap<>();
        maxKey = size;
    }

    public void set(int index, double value) {
        if (index >= maxKey) {
            throw new IllegalArgumentException("Exceed vector's size.");
        }
        sparseMap.put(index, value);
    }

    public double get(int index) {
        if (index >= maxKey) {
            throw new IllegalArgumentException("Exceed vector's size.");
        }
        return sparseMap.getOrDefault(index, 0.0);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maxKey; i++) {
            result.append(get(i) + ",");
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    public SparseVector add(SparseVector v) {
        if (v.maxKey != maxKey)
            throw new Error("Vector lengths don't match");
        SparseVector result = new SparseVector(maxKey);
        for (Map.Entry<Integer, Double> entry : sparseMap.entrySet()) {
            int key = entry.getKey();
            result.sparseMap.put(key, result.sparseMap.getOrDefault(key, 0.0) + entry.getValue());
        }
        for (Map.Entry<Integer, Double> entry : v.sparseMap.entrySet()) {
            int key = entry.getKey();
            result.sparseMap.put(key, result.sparseMap.getOrDefault(key, 0.0) + entry.getValue());
        }
        return result;
    }

    public double dot(SparseVector v) {
        checkLengthEqual(v);
        double resultVal = 0;
        for (Map.Entry<Integer, Double> entry : sparseMap.entrySet()) {
            int key = entry.getKey();
            if (v.sparseMap.keySet().contains(key))
                resultVal += entry.getValue() * v.sparseMap.get(key);
        }
        return resultVal;
    }

    public double cos(SparseVector v) {
        checkLengthEqual(v);
        double dotVal = dot(v);
        return dotVal / (norm(sparseMap) * norm(v.sparseMap));
    }

    private double norm(Map<Integer, Double> sparseMap) {
        double sum = 0;
        for (double val : sparseMap.values()) {
            sum += val * val;
        }
        return Math.sqrt(sum);
    }
}

