package ilmaAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

class Connection {
    private String cityname;
    Connection(String name) {
        this.cityname = name;
    }
    String jsonReader(String mode) throws IOException {
        String jsonText = "";
        String line;
        String url = "https://api.openweathermap.org/data/2.5/" + mode + "?q=" + this.cityname + ",EE&appid=8ed7afa5f56fcd9ca49db9e458e97128&unit=metric";
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            while ((line = rd.readLine()) != null) {
                jsonText += line;
            }
        }
        return jsonText;
    }
}
