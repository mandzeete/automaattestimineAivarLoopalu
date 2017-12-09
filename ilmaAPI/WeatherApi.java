package ilmaAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WeatherApi {
    private String weatherDataWithCoordinates;
    private String forecastFor3Days;

    public WeatherApi(JSONgetter getter) throws IOException {
        this.weatherDataWithCoordinates = getter.jsonReader("weather");
        this.forecastFor3Days = getter.jsonReader("forecast");
    }

    public String getWeatherDataWithCoordinates() {
        return weatherDataWithCoordinates;
    }

    public String getForecastFor3Days() {
        return forecastFor3Days;
    }

    public String getTemperature() {
        String[] temperatureString = weatherDataWithCoordinates.split("temp")[1].split("\"");
        String temperature = temperatureString[1].split(",")[0].substring(1);
        double temperatureInCelcius = Double.parseDouble(temperature);
        return Double.toString(temperatureInCelcius - 273.15) + " °C";
    }

    public String getMaximumTemperature() {
        ArrayList<String> temperatures = new ArrayList<>(Arrays.asList(forecastFor3Days.split("\"dt\":")));
        ArrayList<String> temperatures2 = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            temperatures2.add(temperatures.get(i));
        }
        temperatures.clear();
        for (int i = 1; i < temperatures2.size(); i++) {
            int placement = temperatures2.get(i).indexOf("\"temp_max\":");
            temperatures.add(temperatures2.get(i).substring(placement + 11, placement + 18));
        }
        temperatures2.clear();
        for (String temperature : temperatures) {
            while (temperature.contains(",")) {
                temperature = temperature.substring(0, temperature.indexOf(","));
            }
            temperatures2.add(temperature);
        }
        temperatures.clear();
        double maximumTemperature = Double.parseDouble(temperatures2.get(0));
        for (String temperature2 : temperatures2) {
            if (Double.parseDouble(temperature2) > maximumTemperature) {
                maximumTemperature = Double.parseDouble(temperature2);
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
        for (String temperature : temperatures) {
            while (temperature.contains(",")) {
                temperature = temperature.substring(0, temperature.indexOf(","));
            }
            temperatures2.add(temperature);
        }
        temperatures.clear();
        double maximumTemperature = Double.parseDouble(temperatures2.get(0));
        for (String aTemperatures2 : temperatures2) {
            if (Double.parseDouble(aTemperatures2) < maximumTemperature) {
                maximumTemperature = Double.parseDouble(aTemperatures2);
            }
        }
        maximumTemperature = maximumTemperature - 273.15;
        double roundOff = (double) Math.round(maximumTemperature * 10) / 10;
        return Double.toString(roundOff) + " °C";
    }

    public String getCoordinates() {
        String[] city = weatherDataWithCoordinates.split("weather")[0].split("\"");
        return "Longitude: " + city[4].substring(1, city[4].length() - 1) + " Latitude: " + city[6].substring(1, city[6].length() - 2);
    }

    public String cityGetter() {
        String[] city = weatherDataWithCoordinates.split("name")[1].split("\"");
        return city[2];
    }
}
