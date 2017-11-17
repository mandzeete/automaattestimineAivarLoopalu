package ilmaAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Filereader {
    private String fileName;

    public Filereader(String name) {
        this.fileName = name;
    }

    public List<String> readline() throws IOException {
        List<String> lines = new ArrayList<String>();
        URL path = Filereader.class.getResource(fileName);
        File file = new File(path.getFile());
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null)
        {
            lines.add(line);
        }
        reader.close();
        return lines;
    }
}
