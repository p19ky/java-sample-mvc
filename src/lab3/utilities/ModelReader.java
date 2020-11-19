package lab3.utilities;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ModelReader {
    public List<String> getLinesFromFile(String fileName) {
        String line;
        List<String> listOfLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {
                listOfLines.add(line);
            }

            return listOfLines;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfLines;
    }
}
