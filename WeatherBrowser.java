import ilmaAPI.WeatherApi;

import java.io.IOException;

public class WeatherBrowser {
    public static void main(String[] args) throws IOException {
        WeatherApi weatherApi = new WeatherApi("Tallinn");
        String forecast = weatherApi.getForecastFor3Days();
        String city = weatherApi.cityGetter();
        String temperature = weatherApi.getTemperature();
        String maximumTemp = weatherApi.getMaximumTemperature();
        String minimumTemp = weatherApi.getMinimumTemperature();
        System.out.println(city + ": " + temperature);
        System.out.println("Maximum temperature for 3 days: " + maximumTemp);
        System.out.println("Minimum temperature for 3 days: " + minimumTemp);
    }
}
