package CompanyOriented.Google.PhoneInterview;

public class AddBinary {
    public static void main(String[] args) {
        AddBinary inst = new AddBinary();
        System.out.println(inst.addBinary("1", "111"));
    }

    public String addBinary(String a, String b) {

        char[] aArray = a.toCharArray(), bArray = b.toCharArray();
        int carry = 0, alen = a.length(), blen = b.length(), i = alen - 1, j = blen - 1;
        StringBuilder result = new StringBuilder();
        while (i >= 0 && j >= 0) {
            int digit = carry + Character.getNumericValue(aArray[i--]) + Character.getNumericValue(bArray[j--]);
            carry = 0;
            if (digit == 2) {
                carry++;
                digit = 0;
            }
            result.append(digit);
        }
        while (i >= 0) {
            int digit = carry + Character.getNumericValue(aArray[i--]);
            carry = 0;
            if (digit == 2) {
                carry++;
                digit = 0;
            }
            result.append(digit);
        }
        while (j >= 0) {
            int digit = carry + Character.getNumericValue(bArray[j--]);
            carry = 0;
            if (digit == 2) {
                carry++;
                digit = 0;
            }
            result.append(digit);
        }
        if (carry != 0) {
            result.append(carry);
        }

        return result.reverse().toString();
    }
}
