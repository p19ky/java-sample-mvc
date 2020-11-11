package lab3.utilities;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ModelReader {
    public List<String> getLinesFromFile(String fileName) {
        String line;
        List<String> listOfLines = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("the_file_name"), Charset.forName("UTF-8")))) {
            while ((line = br.readLine()) != null) {
                listOfLines.add(line);
            }

            return listOfLines;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return listOfLines;
    }
}
