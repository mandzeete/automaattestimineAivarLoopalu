package ilmaAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Filereader {
    public List<String> readline() {
        List<String> lines = new ArrayList<String>();
        try
        {
            URL path = Filereader.class.getResource("CityData.txt");
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
        catch (Exception e)
        {
            String name = InputScanner.getInput();
            lines.add(name);
            return lines;
        }
    }

}
