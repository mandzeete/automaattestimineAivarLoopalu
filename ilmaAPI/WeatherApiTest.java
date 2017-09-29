package ilmaAPI;

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
    public void testIfRequestedCityIsInParsedJson() {
        WeatherApi api = new WeatherApi();
        String city = "Tallinn";
        String jsondata = api.jsonReader(city);
        assertEquals(api.cityGetter(jsondata).equals(city), true);
    }

    @Test
    public void testIfJsonIsNotEmptyString() {
        WeatherApi api = new WeatherApi();
        String city = "Tallinn";
        String jsondata = api.jsonReader(city);
        assertEquals(jsondata.equals(""), false);
    }

    @Test
    public void testIfJsonIsNotNull() {
        WeatherApi api = new WeatherApi();
        String city = "Tallinn";
        String jsondata = api.jsonReader(city);
        assertEquals(jsondata.equals(null), false);
    }

    @Test
    public void testIfTemperatureIsNotEmptyString() {
        WeatherApi api = new WeatherApi();
        String city = "Tallinn";
        String jsondata = api.jsonReader(city);
        String currentTemperature = api.getTemperature(jsondata);
        assertEquals(currentTemperature.equals(""), false);
    }

    @Test
    public void testIfTemperatureIsNotNull() {
        WeatherApi api = new WeatherApi();
        String city = "Tallinn";
        String jsondata = api.jsonReader(city);
        String currentTemperature = api.getTemperature(jsondata);
        assertEquals(currentTemperature.equals(null), false);
    }

    @Test
    public void testIfTemperatureIsInCelsius() {
        WeatherApi api = new WeatherApi();
        assertEquals(api.temperatureStyleCheck("16 C"), true);
    }

    @Test
    public void testIfTemperatureIsNotInCelcius() {
        WeatherApi api = new WeatherApi();
        assertEquals(api.temperatureStyleCheck("16 K"), false);
    }

    @Test
    public void testIfTemperatureFormatHasNoSpace() {
        WeatherApi api = new WeatherApi();
        assertEquals(api.temperatureStyleCheck("16C"), false);
    }

    @Test
    public void testIfTemperatureFormaIsIntWrongOrder() {
        WeatherApi api = new WeatherApi();
        assertEquals(api.temperatureStyleCheck("C 16"), false);
    }

    @Test
    public void testIfTemperatureRoundedFullDigit() {
        WeatherApi api = new WeatherApi();
        assertEquals(api.temperatureStyleCheck("15,9 C"), false);
    }

    @Test
    public void testIfGetMaxTemperature() {
        WeatherApi api = new WeatherApi();
        String city = "Tallinn";
        String jsondata = api.jsonReader(city);
        String maximumTemperature = api.getMaximumTemperature(jsondata);
        assertEquals(api.temperatureStyleCheck(maximumTemperature), true);
    }

    @Test
    public void testIfMaxTemperatureNotNull() {
        WeatherApi api = new WeatherApi();
        String city = "Tallinn";
        String jsondata = api.jsonReader(city);
        String maximumTemperature = api.getMaximumTemperature(jsondata);
        assertEquals(maximumTemperature.equals(null), false);
    }

    @Test
    public void testIfMaxTemperatureNotEmptyString() {
        WeatherApi api = new WeatherApi();
        String city = "Tallinn";
        String jsondata = api.jsonReader(city);
        String maximumTemperature = api.getMaximumTemperature(jsondata);
        assertEquals(maximumTemperature.equals(""), false);
    }

    @Test
    public void testIfGetMinTemperature() {
        WeatherApi api = new WeatherApi();
        String city = "Tallinn";
        String jsondata = api.jsonReader(city);
        String minimumTemperature = api.getMinimumTemperature(jsondata);
        assertEquals(api.temperatureStyleCheck(minimumTemperature), true);
    }

    @Test
    public void testIfMinTemperatureNotNull() {
        WeatherApi api = new WeatherApi();
        String city = "Tallinn";
        String jsondata = api.jsonReader(city);
        String minimumTemperature = api.getMinimumTemperature(jsondata);
        assertEquals(minimumTemperature.equals(null), false);
    }

    @Test
    public void testIfMinTemperatureNotEmptyString() {
        WeatherApi api = new WeatherApi();
        String city = "Tallinn";
        String jsondata = api.jsonReader(city);
        String minimumTemperature = api.getMinimumTemperature(jsondata);
        assertEquals(minimumTemperature.equals(""), false);
    }

    @Test
    public void testIfCoordinatesNotEmptyString() {
        WeatherApi api = new WeatherApi();
        String coordinatedata = api.coordinateReader();
        String coordinates = api.getCoordinatesFromJson(coordinatedata);
        assertEquals(coordinates.equals(""), false);
    }

    @Test
    public void testIfCoordinatesNotNull() {
        WeatherApi api = new WeatherApi();
        String coordinatedata = api.coordinateReader();
        String coordinates = api.getCoordinatesFromJson(coordinatedata);
        assertEquals(coordinates.equals(null), false);
    }

    @Test
    public void testIfCoordinateStyleIsXxxYyyFormat() {
        WeatherApi api = new WeatherApi();
        String coordinates = "123:212";
        assertEquals(api.coordinateStyleCheck(coordinates), true);
    }

    @Test
    public void testIfCoordinatesIsRandomWord() {
        WeatherApi api = new WeatherApi();
        String coordinates = "karu";
        assertEquals(api.coordinateStyleCheck(coordinates), false);
    }

    @Test
    public void testIfCoordinateStringHasNoColon() {
        WeatherApi api = new WeatherApi();
        String coordinates = "232312";
        assertEquals(api.coordinateStyleCheck(coordinates), false);
    }

    @Test
    public void testIfCoordinateStringIsShort() {
        WeatherApi api = new WeatherApi();
        String coordinates = "1231";
        assertEquals(api.coordinateStyleCheck(coordinates), false);
    }

    @Test
    public void testIfCoordinateStringIsLong() {
        WeatherApi api = new WeatherApi();
        String coordinates = "2322321:3131232";
        assertEquals(api.coordinateStyleCheck(coordinates), false);
    }
}
