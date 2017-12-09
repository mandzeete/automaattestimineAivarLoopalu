import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

import static org.junit.Assert.assertEquals;

public class ConnectionTests {
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
}
