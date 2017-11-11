package ilmaAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

class JSONgetter {
    private String cityname;
    JSONgetter(String name) {
        if (name == null || name.equals("")) {
            this.cityname = InputScanner.getInput();
        } else {
            this.cityname = name;
        }
    }
    String jsonReader(String mode) throws IOException {
        String jsonText = "";
        String line;
        String url = "https://api.openweathermap.org/data/2.5/" + mode + "?q=" + this.cityname + ",EE&appid=8ed7afa5f56fcd9ca49db9e458e97128&unit=metric";
        try (InputStream stream = new URL(url).openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
            while ((line = reader.readLine()) != null) {
                jsonText += line;
            }
        }
        return jsonText;
    }
}
