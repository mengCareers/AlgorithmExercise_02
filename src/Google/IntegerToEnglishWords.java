package Google;

public class IntegerToEnglishWords {

    public static void main(String[] args) {
        IntegerToEnglishWords inst = new IntegerToEnglishWords();
        inst.numberToWords(1234567891);
    }

    String[] ZERO_NINETEEN = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] TENS = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] DELIMITER = {"Thousand", "Million", "Billion"};

    public String numberToWords(int num) { // 99999


        if (num < 1000) {
            return numberNNNtoWords(num);
        }

        StringBuilder result = new StringBuilder();
        long delimiter = 1000;

        for (String strDelimiter : DELIMITER) {
            if (num >= delimiter && delimiter * 1000 > num) {
                int left = (int) (num / delimiter);
                int right = (int) (num % delimiter);
                result.append(numberNNNtoWords(left) + " " + strDelimiter);
                if (right > 0) {
                    result.append(" ").append(numberToWords(right));
                }
                break;
            }
            delimiter *= 1000;
        }

        return result.toString();
    }

    private String numberNNtoWords(int num) {

        StringBuilder result = new StringBuilder();
        if (num <= 19) {
            return ZERO_NINETEEN[num];
        }
        if (num % 10 == 0) {
            return TENS[num / 10 - 2];
        }
        result.append(TENS[num / 10 - 2]).append(" ").append(ZERO_NINETEEN[num % 10]);

        return result.toString();
    }

    private String numberNNNtoWords(int num) {

        StringBuilder result = new StringBuilder();
        if (num < 100) {
            return numberNNtoWords(num);
        }

        result.append(ZERO_NINETEEN[num / 100]).append(" Hundred");
        String right = numberNNtoWords(num % 100);
        if (!right.isEmpty())
            result.append(" ").append(right);

        return result.toString();
    }
}
