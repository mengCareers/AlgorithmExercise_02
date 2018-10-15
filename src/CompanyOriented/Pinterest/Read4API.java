package CompanyOriented.Pinterest;

import java.util.Random;

/*
What information do we expect from read4?
endOfFile
its output destination buf[] helps build our buf[]

numToRead
while (!endOfFile) {
new buf4
call read4(buf)

min = min(numToRead, buf4 len)
if (buf4 len < 4)
    endOfFile true
}
dump buf4 to buf (0..min)
}
 */
public class Read4API {


    // If we call read(n) multiple times with different n on the same file
    /*

     */

    private int buffPtr = 0;
    private int buffCnt = 0;
    private static char[] buff = new char[4];

    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr == 0) {
                buffCnt = read4(buff);
            }
            if (buffCnt == 0) break;
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
            if (buffPtr >= buffCnt) buffPtr = 0;
        }
        return ptr;
    }

    public int read_1(char[] buf, int n) {
        int ptr = 0, buffPtr = 0, buffAimCnt = 0;
        char[] buff = new char[4];
        int posLastSpace = -1; // The position of first non-space char - 1.

        while (ptr < n) {
            if (buffPtr == 0)
                buffAimCnt = read4(buff);
            if (buffAimCnt == 0)
                break;
            while (ptr < n && buffPtr < buffAimCnt) {
                if (buff[buffPtr] == ' ') {
                    for (int i = posLastSpace + 1; i < ptr; i++)
                        System.out.print(buf[i]);
                    System.out.println();
                    posLastSpace = ptr;
                }
                buf[ptr++] = buff[buffPtr++];
            }
            if (buffPtr == buffAimCnt) {
                buffPtr = 0;
            }
        }

        return ptr;
    }

    // If we call read(n) once.
    public int read_0(char[] buf, int n) {

        boolean endOfFile = false;
        int numToRead = n, numActualRead = 0, bufIndex = 0;
        char[] buf4;

        while (!endOfFile) {
            buf4 = new char[4];
            int returnValue = read4(buf4);
            if (returnValue < 4)
                endOfFile = true;
            numActualRead = Math.min(returnValue, numToRead);
            numToRead -= numActualRead;
            for (int i = 0; i < numActualRead; i++) {
                buf[bufIndex++] = buf4[i];
            }
        }

        return n - numToRead;
    }

    public static int read4(char[] buf) {
        int numToRead = new Random().nextInt(5);
        for (int i = 0; i < numToRead; i++) {
            buf[i] = (char) new Random().nextInt(100);
        }
        return numToRead;
    }


}
