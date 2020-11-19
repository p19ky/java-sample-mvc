package lab3.utilities;

import java.io.*;

public class DeleteSpecificFileLines {
    public void deleteLines(String fileName, String equalToCondition) {
        try {
            File inputFile = new File(fileName);
            if (!inputFile.isFile()) {
                throw new FileNotFoundException();
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line = null;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
                String[] words = line.split(", ");
                if (!(words[1].equals(equalToCondition))) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!inputFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inputFile))
                System.out.println("Could not rename file");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
