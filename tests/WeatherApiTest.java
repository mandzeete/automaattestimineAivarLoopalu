import ilmaAPI.WeatherApi;
import org.junit.Test;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

import static org.junit.Assert.assertEquals;

public class WeatherApiTest {

    @Test
    public void testIfNetworkInterfaceIsUp() {
        Boolean networkInterface = false;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface interf = interfaces.nextElement();
                if (interf.isUp() && !interf.isLoopback())
                    networkInterface = true;
            }
        } catch (java.net.SocketException e) {
            networkInterface = false;
        }
        assertEquals(networkInterface, true);
    }

    @Test
    public void testIfConnectionIsAvailable() {
        String connectionResponse;
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection connection = url.openConnection();
            connection.connect();
            connectionResponse = "yes";
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            connectionResponse = "no";
        }
        assertEquals(connectionResponse, "yes");
    }

    @Test
    public void testIfWebsiteIsAvailable() {
        String connectionResponse;
        try {
            final URL url = new URL("https://openweathermap.org/");
            final URLConnection connection = url.openConnection();
            connection.connect();
            connectionResponse = "yes";
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            connectionResponse = "no";
        }
        assertEquals(connectionResponse, "yes");
    }

    @Test
    public void testIfApiIsAvailableForForecast() {
        String connectionResponse;
        try {
            final URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?q=Tallinn,EE&appid=8ed7afa5f56fcd9ca49db9e458e97128&unit=metric");
            final URLConnection connection = url.openConnection();
            connection.connect();
            connectionResponse = "yes";
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            connectionResponse = "no";
        }
        assertEquals(connectionResponse, "yes");
    }

    @Test
    public void testIfApiIsAvailableForCoordinates() {
        String connectionResponse;
        try {
            final URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Tallinn,EE&appid=8ed7afa5f56fcd9ca49db9e458e97128&unit=metric");
            final URLConnection connection = url.openConnection();
            connection.connect();
            connectionResponse = "yes";
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            connectionResponse = "no";
        }
        assertEquals(connectionResponse, "yes");
    }

    @Test
    public void testIfRequestedCityIsInParsedJson() throws IOException{
        WeatherApi api = new WeatherApi("Tallinn");
        String city = "Tallinn";
        assertEquals(api.cityGetter().equals(city), true);
    }

    @Test
    public void testIfWeatherJsonIsNotEmptyString() throws IOException{
        WeatherApi api = new WeatherApi("Tallinn");
        String jsondata = api.getWeatherDataWithCoordinates();
        assertEquals(jsondata.equals(""), false);
    }

    @Test
    public void testIfForecastJsonIsNotEmptyString() throws IOException{
        WeatherApi api = new WeatherApi("Tallinn");
        String jsondata = api.getForecastFor3Days();
        assertEquals(jsondata.equals(""), false);
    }

    @Test
    public void testIfWeatherJsonIsNotNull() throws IOException{
        WeatherApi api = new WeatherApi("Tallinn");
        String jsondata = api.getWeatherDataWithCoordinates();
        assertEquals(jsondata.equals(null), false);
    }

    @Test
    public void testIfForecastJsonIsNotNull() throws IOException{
        WeatherApi api = new WeatherApi("Tallinn");
        String jsondata = api.getForecastFor3Days();
        assertEquals(jsondata.equals(null), false);
    }

    @Test
    public void testIfTemperatureIsNotEmptyString() throws IOException{
        WeatherApi api = new WeatherApi("Tallinn");
        String currentTemperature = api.getTemperature();
        assertEquals(currentTemperature.equals(""), false);
    }

    @Test
    public void testIfTemperatureIsNotNull() throws IOException{
        WeatherApi api = new WeatherApi("Tallinn");
        String currentTemperature = api.getTemperature();
        System.out.println(currentTemperature);
        assertEquals(currentTemperature.equals(null), false);
    }

    @Test
    public void testIfMaxTemperatureNotNull() throws IOException{
        WeatherApi api = new WeatherApi("Tallinn");
        String jsondata = api.getForecastFor3Days();
        String maximumTemperature = api.getMaximumTemperature();
        assertEquals(maximumTemperature.equals(null), false);
    }

    @Test
    public void testIfMaxTemperatureNotEmptyString() throws IOException{
        WeatherApi api = new WeatherApi("Tallinn");
        String jsondata = api.getForecastFor3Days();
        String maximumTemperature = api.getMaximumTemperature();
        assertEquals(maximumTemperature.equals(""), false);
    }

    @Test
    public void testIfMinTemperatureNotNull() throws IOException{
        WeatherApi api = new WeatherApi("Tallinn");
        String jsondata = api.getForecastFor3Days();
        String minimumTemperature = api.getMinimumTemperature();
        assertEquals(minimumTemperature.equals(null), false);
    }

    @Test
    public void testIfMinTemperatureNotEmptyString() throws IOException{
        WeatherApi api = new WeatherApi("Tallinn");
        String jsondata = api.getForecastFor3Days();
        String minimumTemperature = api.getMinimumTemperature();
        assertEquals(minimumTemperature.equals(""), false);
    }
}
