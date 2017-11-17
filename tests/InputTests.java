import ilmaAPI.WeatherApi;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
public class InputTests {
    @Test
    public void testForCityInput() throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream("Tallinn".getBytes());
        System.setIn(in);
        WeatherApi api = new WeatherApi();
        System.setIn(in);
        String jsondata = api.getForecastFor3Days();
        String minimumTemperature = api.getMinimumTemperature();
        System.setIn(System.in);
        assertEquals(minimumTemperature.equals(""), false);
    }
}
