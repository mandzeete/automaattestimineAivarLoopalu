import ilmaAPI.*;

import java.io.IOException;
import java.util.List;

public class WeatherBrowser {
    public static void main(String[] args) throws IOException {
        Filereader reader = new Filereader("CityData.txt");
        List<String > list = reader.readline();
        for (String name:list) {
            JSONgetter getter = new JSONgetter(name);
            WeatherApi weatherApi = new WeatherApi(getter);
            String city = weatherApi.cityGetter();
            Filewritter filewritter = new Filewritter(city);

            String temperature = weatherApi.getTemperature();
            String maximumTemp = weatherApi.getMaximumTemperature();
            String minimumTemp = weatherApi.getMinimumTemperature();

            filewritter.write(city);
            filewritter.write(weatherApi.getCoordinates());
            filewritter.write("Maximum temperature for 3 days: " + maximumTemp);
            filewritter.write("Minimum temperature for 3 days: " + minimumTemp);
            filewritter.write("The temperature at the moment: " + temperature);
            filewritter.close();

            System.out.println(weatherApi.getCoordinates());
            System.out.println(city + ": " + temperature);
            System.out.println("Maximum temperature for 3 days: " + maximumTemp);
            System.out.println("Minimum temperature for 3 days: " + minimumTemp);
        }
    }
}
