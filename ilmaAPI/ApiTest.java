package ilmaAPI;

import org.junit.Test;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

import static org.junit.Assert.assertEquals;

public class ApiTest {

    @Test
    public void testNetworkInterfaceIsUp() {
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
    public void testConnectionIsAvailable() {
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
    public void testWebsiteIsAvailable() {
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
    public void testApiIsAvailableForForecast() {
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
    public void testApiIsAvailableForCoordinates() {
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
    public void jsonIsNotEmpty() {
        WeatherApi api = new WeatherApi();
        String jsondata = api.jsonReader();
        assertEquals(jsondata.equals(""), false);
    }

    @Test
    public void jsonIsNotNull() {
        WeatherApi api = new WeatherApi();
        String jsondata = api.jsonReader();
        assertEquals(jsondata.equals(null), false);
    }

    @Test
    public void temperatureIsNotEmpty() {
        WeatherApi api = new WeatherApi();
        String jsondata = api.jsonReader();
        String currentTemperature = api.getTemperature(jsondata);
        assertEquals(currentTemperature.equals(""), false);
    }

    @Test
    public void temperatureIsNotNull() {
        WeatherApi api = new WeatherApi();
        String jsondata = api.jsonReader();
        String currentTemperature = api.getTemperature(jsondata);
        assertEquals(currentTemperature.equals(null), false);
    }

    @Test
    public void temperatureIsInFormatTrue() {
        WeatherApi api = new WeatherApi();
        assertEquals(api.temperatureStyleCheck("16 C"), true);
    }

    @Test
    public void temperatureIsInFormatWrongSystem() {
        WeatherApi api = new WeatherApi();
        assertEquals(api.temperatureStyleCheck("16 K"), false);
    }

    @Test
    public void temperatureFormatNoSpace() {
        WeatherApi api = new WeatherApi();
        assertEquals(api.temperatureStyleCheck("16C"), false);
    }

    @Test
    public void temperatureFormatWrongOrder() {
        WeatherApi api = new WeatherApi();
        assertEquals(api.temperatureStyleCheck("C 16"), false);
    }

    @Test
    public void temperatureFormatRoundDegreeFull() {
        WeatherApi api = new WeatherApi();
        assertEquals(api.temperatureStyleCheck("15,9 C"), false);
    }

    @Test
    public void maxTemperature() {
        WeatherApi api = new WeatherApi();
        String jsondata = api.jsonReader();
        String maximumTemperature = api.getMaximumTemperature(jsondata);
        assertEquals(api.temperatureStyleCheck(maximumTemperature), true);
    }

    @Test
    public void maxTemperatureNotNull() {
        WeatherApi api = new WeatherApi();
        String jsondata = api.jsonReader();
        String maximumTemperature = api.getMaximumTemperature(jsondata);
        assertEquals(maximumTemperature.equals(null), false);
    }

    @Test
    public void maxTemperatureNotEmpty() {
        WeatherApi api = new WeatherApi();
        String jsondata = api.jsonReader();
        String maximumTemperature = api.getMaximumTemperature(jsondata);
        assertEquals(maximumTemperature.equals(""), false);
    }

    @Test
    public void minTemperature() {
        WeatherApi api = new WeatherApi();
        String jsondata = api.jsonReader();
        String minimumTemperature = api.getMinimumTemperature(jsondata);
        assertEquals(api.temperatureStyleCheck(minimumTemperature), true);
    }

    @Test
    public void minTemperatureNotNull() {
        WeatherApi api = new WeatherApi();
        String jsondata = api.jsonReader();
        String minimumTemperature = api.getMinimumTemperature(jsondata);
        assertEquals(minimumTemperature.equals(null), false);
    }

    @Test
    public void minTemperatureNotEmpty() {
        WeatherApi api = new WeatherApi();
        String jsondata = api.jsonReader();
        String minimumTemperature = api.getMinimumTemperature(jsondata);
        assertEquals(minimumTemperature.equals(""), false);
    }

    @Test
    public void getCoordinatesTrue() {
        WeatherApi api = new WeatherApi();
        String coordinatedata = api.coordinateReader();
        String coordinates = api.getCoordinates(coordinatedata);
        assertEquals(coordinates.equals(""), false);
    }

    @Test
    public void coordinatesNotNull() {
        WeatherApi api = new WeatherApi();
        String coordinatedata = api.coordinateReader();
        String coordinates = api.getCoordinates(coordinatedata);
        assertEquals(coordinates.equals(null), false);
    }

    @Test
    public void coordinateStyleOk() {
        WeatherApi api = new WeatherApi();
        String coordinates = "123:212";
        assertEquals(api.CoordinateStyleCheck(coordinates), true);
    }

    @Test
    public void coordinateStyleWord() {
        WeatherApi api = new WeatherApi();
        String coordinates = "karu";
        assertEquals(api.CoordinateStyleCheck(coordinates), false);
    }

    @Test
    public void coordinateStyleNoColon() {
        WeatherApi api = new WeatherApi();
        String coordinates = "232312";
        assertEquals(api.CoordinateStyleCheck(coordinates), false);
    }

    @Test
    public void coordinateStyleShort() {
        WeatherApi api = new WeatherApi();
        String coordinates = "1231";
        assertEquals(api.CoordinateStyleCheck(coordinates), false);
    }

    @Test
    public void coordinateStyleLong() {
        WeatherApi api = new WeatherApi();
        String coordinates = "2322321:3131232";
        assertEquals(api.CoordinateStyleCheck(coordinates), false);
    }
}
