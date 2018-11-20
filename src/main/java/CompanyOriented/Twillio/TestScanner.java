package CompanyOriented.Twillio;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TestScanner {
    public static void main(String[] args) {
        String inputFileName = "log.txt";
        Map<String, Integer> map = new HashMap<>(); // Key: hostname, value: number of requests.

        try (Scanner scanner = new Scanner(new FileReader(inputFileName))) {
            String line;
            while ((line = scanner.nextLine()) != null) {
                int endIndex = line.indexOf(" ");

                String hostname = line.substring(0, endIndex);
                map.put(hostname, map.getOrDefault(hostname, 0) + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        String outputFileName = "records_" + inputFileName;
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName)))) {
            for (String hostname : map.keySet()) {
                out.println(hostname + " " + map.get(hostname));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
