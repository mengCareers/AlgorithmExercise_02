package Array;

public class ToBinary {
    public int bitwiseComplement(int N) {
        // Get binary representation
        String binary = Integer.toBinaryString(N);
        // Get complement
        String binaryComplement = getComplement(binary);
        return Integer.parseInt(binaryComplement, 2);
    }

    private String getComplement(String binary) {
        StringBuilder sb = new StringBuilder();
        for (char ch : binary.toCharArray()) {
            if (ch == '1') sb.append('0');
            else sb.append('1');
        }
        return sb.toString();
    }
}
