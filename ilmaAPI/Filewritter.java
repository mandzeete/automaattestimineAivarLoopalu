package ilmaAPI;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Filewritter{
    private BufferedWriter bufferedWriter = null;
    private FileWriter fileWriter = null;
    public Filewritter(String string) {
        try {
            String filename = string + ".txt";
            fileWriter = new FileWriter(filename);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void write(String output) {
        try {
            bufferedWriter.write(output + "\n");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void close() {
        try {
            if (bufferedWriter != null)
                bufferedWriter.close();
            if (fileWriter != null)
                fileWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
