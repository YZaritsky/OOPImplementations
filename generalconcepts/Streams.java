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
        // in Java 7 + -> This auto closes the files:
        try (OutputStream output = new FileOutputStream("Output.txt");) {
            InputStream input = new FileInputStream("Input.txt");

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
        Reader inBuffer = new BufferdReader(inFile);

        Writer outFile = new FileWriter("output.txt");
        Writer outBuffer = new BufferdWriter(outFile);

    }
}
