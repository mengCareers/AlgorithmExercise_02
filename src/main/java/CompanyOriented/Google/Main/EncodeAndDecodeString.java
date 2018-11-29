package CompanyOriented.Google;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EncodeAndDecodeString {

    public String encode(List<String> strs) {

        StringBuilder encodedString = new StringBuilder();
        for (String str : strs) {
            encodedString.append(str.length()).append(":").append(str);
        }

        return encodedString.toString();
    }

    public List<String> decode(String s) {

        List<String> decodedStrings = new LinkedList<>();
        int i = 0;

        while (i < s.length()) {
            int slashIndex = s.indexOf(':', i);
            int stringLength = Integer.valueOf(s.substring(i, slashIndex));
            String str = s.substring(slashIndex + 1, slashIndex + 1 + stringLength);
            decodedStrings.add(str);
            i = slashIndex + 1 + stringLength;
        }

        return decodedStrings;
    }
}
