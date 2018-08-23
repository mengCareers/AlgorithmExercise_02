package Amazon;

import java.util.concurrent.ConcurrentHashMap;

public class EncodeAndDecodeTinyURL {

    private static final ConcurrentHashMap<String, String> encodedToUrl = new ConcurrentHashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        StringBuffer stringBuffer = new StringBuffer();
        String encoded = "http://tinyurl.com/" + longUrl.hashCode();
        stringBuffer.append(encoded);
        encodedToUrl.put(encoded, longUrl);
        return stringBuffer.toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return encodedToUrl.get(shortUrl);
    }
}

