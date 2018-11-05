package CompanyOriented.Twillio;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTransform {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat inputFormatter = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat outputFormatter2 = new SimpleDateFormat("yyyy-MM-dd");
        Pattern pattern = Pattern.compile("([0-9]+)[a-z]{2}\\s([a-zA-Z]{3})\\s([0-9]{4})");
        String[] input = new String[]{
                "20th Oct 2052", "6th Jun 1933", "26th May 1960", "20th Sep 1958",
                "16th Mar 2068"
        };
        String[] output = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            Matcher m = pattern.matcher(input[i]);
            if (m.matches()) {
                output[i] = outputFormatter2.format(
                        inputFormatter.parse(String.format("%s %s %s", m.group(1), m.group(2), m.group(3))));
            }
        }
        for (String s : output) System.out.println(s);
    }
}
