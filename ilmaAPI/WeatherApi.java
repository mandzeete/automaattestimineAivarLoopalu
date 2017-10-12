package ilmaAPI;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

public class WeatherApi {
    private String city;
    private String weatherDataWithCoordinates;
    private String forecastFor3Days;

    public WeatherApi(String name) throws IOException{
        this.city = name;
        weatherDataWithCoordinates = jsonReader("weather");
        forecastFor3Days = jsonReader("forecast");
    }

    String getWeatherDataWithCoordinates() {
        return weatherDataWithCoordinates;
    }

    public String getForecastFor3Days() {
        return forecastFor3Days;
    }

    private String jsonReader(String mode) throws IOException {
        String jsonText = "";
        String line;
        String url = "https://api.openweathermap.org/data/2.5/" + mode + "?q=" + city + ",EE&appid=8ed7afa5f56fcd9ca49db9e458e97128&unit=metric";
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            while ((line = rd.readLine()) != null) {
                // buffer.append(line);
                jsonText += line;
            }
        }
        return jsonText;
    }

    public String getTemperature() {
        String[] temperatureString = weatherDataWithCoordinates.split("temp")[1].split("\"");
        String temperature = temperatureString[1].split(",")[0].substring(1);
        double temperatureInCelcius = Double.parseDouble(temperature);
        return Double.toString(temperatureInCelcius - 273.15) + " °C";
    }

    public String getMaximumTemperature(String text) {
        return "";
    }

    public String getMinimumTemperature(String text) {
        return "";
    }

    public String coordinateReader() {
        return "";
    }

    public String getCoordinatesFromJson(String text) {
        return "";
    }

    public boolean coordinateStyleCheck(String text) {
        return false;
    }

    public String cityGetter() {
        String[] city = weatherDataWithCoordinates.split("name")[1].split("\"");
        return city[2];
    }
}
