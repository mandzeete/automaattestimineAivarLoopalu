package ilmaAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

public class JSONgetter {
    private String cityname;
    public JSONgetter(String name) {
        this.cityname = name;
    }
    public JSONgetter() {
        System.out.println("Please enter the city name:");
        Scanner scanner = new Scanner(System.in);
        this.cityname = scanner.next();
        scanner.close();
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
    public String getCityname() {
        return cityname;
    }
}
