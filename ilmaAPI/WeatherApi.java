package ilmaAPI;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public String getMaximumTemperature() {
        ArrayList<String> temperatures = new ArrayList<String>(Arrays.asList(forecastFor3Days.split("\"dt\":")));
        ArrayList<String> temperatures2 = new ArrayList<String>();
        for (int i = 0; i < 25; i++) {
            temperatures2.add(temperatures.get(i));
        }
        temperatures.clear();
        for (int i = 1; i < temperatures2.size(); i++) {
            int placement = temperatures2.get(i).indexOf("\"temp_max\":");
            temperatures.add(temperatures2.get(i).substring(placement + 11, placement + 18));
        }
        temperatures2.clear();
        for (int i = 0; i < temperatures.size(); i++) {
            String temperature = temperatures.get(i);
            while (temperature.contains(",")) {
                temperature = temperature.substring(0, temperature.indexOf(","));
            }
            temperatures2.add(temperature);
        }
        temperatures.clear();
        double maximumTemperature = Double.parseDouble(temperatures2.get(0));
        for (int i = 0; i < temperatures2.size(); i++) {
            if (Double.parseDouble(temperatures2.get(i)) > maximumTemperature) {
                maximumTemperature = Double.parseDouble(temperatures2.get(i));
            }
        }
        maximumTemperature = maximumTemperature - 273.15;
        double roundOff = (double) Math.round(maximumTemperature * 10) / 10;
        return Double.toString(roundOff) + " °C";
    }

    public String getMinimumTemperature() {
        ArrayList<String> temperatures = new ArrayList<String>(Arrays.asList(forecastFor3Days.split("\"dt\":")));
        ArrayList<String> temperatures2 = new ArrayList<String>();
        for (int i = 0; i < 25; i++) {
            temperatures2.add(temperatures.get(i));
        }
        temperatures.clear();
        for (int i = 1; i < temperatures2.size(); i++) {
            int placement = temperatures2.get(i).indexOf("\"temp_min\":");
            temperatures.add(temperatures2.get(i).substring(placement + 11, placement + 18));
        }
        temperatures2.clear();
        for (int i = 0; i < temperatures.size(); i++) {
            String temperature = temperatures.get(i);
            while (temperature.contains(",")) {
                temperature = temperature.substring(0, temperature.indexOf(","));
            }
            temperatures2.add(temperature);
        }
        temperatures.clear();
        double maximumTemperature = Double.parseDouble(temperatures2.get(0));
        for (int i = 0; i < temperatures2.size(); i++) {
            if (Double.parseDouble(temperatures2.get(i)) < maximumTemperature) {
                maximumTemperature = Double.parseDouble(temperatures2.get(i));
            }
        }
        maximumTemperature = maximumTemperature - 273.15;
        double roundOff = (double) Math.round(maximumTemperature * 10) / 10;
        return Double.toString(roundOff) + " °C";
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
