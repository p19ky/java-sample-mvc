package lab3.utilities;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ModelWriter {
    public void writeToFile(String fileName, String stringToWriteToFile) {
        try {
            // create a writer
            BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileName),
                    StandardOpenOption.APPEND);

            // append text to file
            bw.write(stringToWriteToFile);
            bw.newLine();

            // close the writer
            bw.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
