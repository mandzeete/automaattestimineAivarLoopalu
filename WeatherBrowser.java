import ilmaAPI.WeatherApi;

import java.io.IOException;

public class WeatherBrowser {
    public static void main(String[] args) throws IOException {
        WeatherApi weatherApi = new WeatherApi("Tallinn");
        String forecast = weatherApi.getForecastFor3Days();
        String city = weatherApi.cityGetter();
        String temperature = weatherApi.getTemperature();
        System.out.println(city + " " + temperature);
        System.out.println("\n");
        System.out.println(forecast);
    }
}
