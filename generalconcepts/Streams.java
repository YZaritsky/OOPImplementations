import java.io.*;

public class Streams {
    void writerExample() {
        try {
            Writer writer = new FileWriter("Somefile.txt");
            writer.write("a");
        } catch (IOException e) {
        }
        ;
    }

    void filestreamExample() {
        // We gotta remember to use "input.close(), output.close()"
        // AutoCloseable
        // in Java 7 + -> This auto closes the files:
        try (InputStream input = new FileInputStream("Input.txt");
                OutputStream output = new FileOutputStream("Output.txt");) {
            int result;
            while ((result = input.read()) != -1) {
                output.write(result);
            }
        } catch (IOException e) {
        }
        ;
    }

    void filestreamUsingDecoratorExample() {
        Reader inFile = new FileReader("input.txt");
        Reader inBuffer = new BufferedReader(inFile);

        Writer outFile = new FileWriter("output.txt");
        Writer outBuffer = new BufferedWriter(outFile);

    }
}
