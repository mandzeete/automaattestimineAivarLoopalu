import ilmaAPI.Filereader;
import ilmaAPI.Filewritter;
import ilmaAPI.JSONgetter;
import ilmaAPI.WeatherApi;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WeatherApiTests {
    private WeatherApi api;

    @Before
    public void initialize() throws IOException {
        JSONgetter jsoNgetter = new JSONgetter("Tallinn");
        api = new WeatherApi(jsoNgetter);
    }

    @Test
    public void testIfRequestedCityIsInParsedJson() throws IOException{
        String city = "Tallinn";
        assertEquals(api.cityGetter().equals(city), true);
    }

    @Test
    public void testIfWeatherJsonIsNotEmptyString() throws IOException{
        String jsondata = api.getWeatherDataWithCoordinates();
        assertEquals(jsondata.equals(""), false);
    }

    @Test
    public void testIfForecastJsonIsNotEmptyString() throws IOException{
        String jsondata = api.getForecastFor3Days();
        assertEquals(jsondata.equals(""), false);
    }

    @Test
    public void testIfWeatherJsonIsNotNull() throws IOException{
        String jsondata = api.getWeatherDataWithCoordinates();
        assertEquals(jsondata.length() < 1, false);
    }

    @Test
    public void testIfForecastJsonIsNotNull() throws IOException{
        String jsondata = api.getForecastFor3Days();
        assertEquals(jsondata.length() < 1, false);
    }

    @Test
    public void testIfTemperatureIsNotEmptyString() throws IOException{
        String currentTemperature = api.getTemperature();
        assertEquals(currentTemperature.equals(""), false);
    }

    @Test
    public void testIfTemperatureIsNotNull() throws IOException{
        String currentTemperature = api.getTemperature();
        System.out.println(currentTemperature);
        assertEquals(currentTemperature.equals(null), false);
    }

    @Test
    public void testIfMaxTemperatureNotNull() throws IOException{
        String maximumTemperature = api.getMaximumTemperature();
        assertEquals(maximumTemperature.equals(null), false);
    }

    @Test
    public void testIfMaxTemperatureNotEmptyString() throws IOException{
        String maximumTemperature = api.getMaximumTemperature();
        assertEquals(maximumTemperature.equals(""), false);
    }

    @Test
    public void testIfMinTemperatureNotNull() throws IOException{
        String minimumTemperature = api.getMinimumTemperature();
        assertEquals(minimumTemperature.equals(null), false);
    }

    @Test
    public void testIfMinTemperatureNotEmptyString() throws IOException{
        String minimumTemperature = api.getMinimumTemperature();
        assertEquals(minimumTemperature.equals(""), false);
    }

    @Test
    public void testIfTallinnCoordinatesAreCorrect() throws IOException{
        String coordinates = api.getCoordinates();
        assertEquals(coordinates.equals("Longitude: 24.75 Latitude: 59.44"), true);
    }

    @Test
    public void testIfFilereaderReadsNormally() throws IOException{
        Filereader reader = new Filereader("CityData.txt");
        List<String > list = reader.readline();
        assertEquals(list.size(), 2);
    }

    @Test
    public void testIfWritterWritesNormally() throws IOException{
        Filewritter filewritter = new Filewritter("test");
        filewritter.write("tere");
        filewritter.close();
        Filereader reader = new Filereader("test.txt");
        List<String > list = reader.readline();
        assertEquals(list.size(), 1);
    }

    @Test
    public void testCityNameOfJsonGetter() {
        JSONgetter getter = new JSONgetter("Tallinn");
        assertEquals(getter.getCityname(), "Tallinn");
    }

    @Test
    public void testWeatherJsonValidity() {
        assertEquals(api.getWeatherDataWithCoordinates().length() > 50, true);
    }

    @Test
    public void testForecastJsonValidity() {
        assertEquals(api.getForecastFor3Days().length() > 50, true);
    }
}
