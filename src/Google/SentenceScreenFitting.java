package Google;

/**
 I would highly recommend you to solve it by Exhaustive Search independently and adjust your solution according to the output. That will be far easier than reading others' code in this case.
 Thinking Process
 si : the index of the word we are currently dealing with.
 times : the number of times the sentence can be fitted until now.
 For each row, (i, j) indicates the cell we are currently dealing with, if sentence[si] can be fitted, we move forward j and increase si as below:
 j += sentence[si].length() + 1;
 si++;
 Whenever the whole sentence has been fitted, we need to increase times and reset si as below:
 times++;
 si = 0;
 and then, we go on processing the rest following the same pattern.
 Thus,
 lenSentence: the length needed for a complete sentence to be fitted
 rowDefaultTimes: for each row, the number of times the complete sentence can be fitted.
 Obviously, rowDefaultTimes = (cols + 1) / lenSentence;
 For each row, initially,
 times += rowDefaultTimes;
 and then, we will do with the rest (starting from j)
 int j = rowDefaultTimes * lenSentence;
 in the way that we discussed above.


 */
public class SentenceScreenFitting {
    public int wordsTypingTLE(String[] sentence, int rows, int cols) {
        int si = 0;
        int times = 0;
        for (int i = 0; i < rows; i++) {
            int j = 0;
            while ((j < cols) && (si < sentence.length) && (j + sentence[si].length() - 1 < cols)) {
                j += sentence[si].length() + 1;
                si++;
            }
            while (si >= sentence.length) {
                times++;
                si = 0;
                while ((j < cols) && (si < sentence.length) && (j + sentence[si].length() - 1 < cols)) {
                    j += sentence[si].length() + 1;
                    si++;
                }
            }
        }
        return times;
    }

    // Why does it TLE
//    ["a","bcd"]
//            20000
//            20000
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int lenSentence = 0;
        for (String word : sentence) {
            lenSentence += word.length() + 1;
        }
        int rowDefaultTimes = (cols + 1) / lenSentence;
        int si = 0;
        int times = 0;
        for (int i = 0; i < rows; i++) {
            times += rowDefaultTimes;
            int j = rowDefaultTimes * lenSentence;
            while ((j < cols) && (si < sentence.length) && (j + sentence[si].length() - 1 < cols)) {
                j += sentence[si].length() + 1;
                si++;
            }
            while (si >= sentence.length) {
                times++;
                si = 0;
                while ((j < cols) && (si < sentence.length) && (j + sentence[si].length() - 1 < cols)) {
                    j += sentence[si].length() + 1;
                    si++;
                }
            }
        }
        return times;
    }
}
