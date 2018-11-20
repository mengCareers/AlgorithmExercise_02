package Test;

import java.io.*;

public class TestReaderWriter {
    public static void main(String[] args) {
        try {
            //Sample 01: Open the FileWriter, Buffered Writer
            FileWriter fw = new
                    FileWriter("testlog.txt");
            BufferedWriter WriteFileBuffer = new
                    BufferedWriter(fw);

            //Sample 02: Write Some Text to File
            // Using Buffered Writer)
            WriteFileBuffer.write("First Line");
            WriteFileBuffer.newLine();
            WriteFileBuffer.write("Second Line");
            WriteFileBuffer.newLine();
            WriteFileBuffer.write("Third Line");
            WriteFileBuffer.newLine();

            //Sample 03: Close both the Writers
            WriteFileBuffer.close();

            //Sample 04: Open the Readers Now
            FileReader fr = new
                    FileReader("testlog.txt");
            BufferedReader ReadFileBuffer = new
                    BufferedReader(fr);

            //Sample 05: Read the text Written
            // using BufferedWriter
            System.out.println(ReadFileBuffer.readLine());
            System.out.println(ReadFileBuffer.readLine());
            System.out.println(ReadFileBuffer.readLine());

            //Sample 06: Close the Readers
            ReadFileBuffer.close();

        } catch (IOException Ex) {
            System.out.println(Ex.getMessage());
        }

    }
}
