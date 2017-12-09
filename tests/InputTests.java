import ilmaAPI.JSONgetter;
import ilmaAPI.WeatherApi;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
public class InputTests {
    @Test
    public void testForCityInput() throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream("Tartu".getBytes());
        System.setIn(in);
        JSONgetter jsoNgetter = new JSONgetter();
        WeatherApi api = new WeatherApi(jsoNgetter);
        System.setIn(System.in);
        assertEquals(api.cityGetter().equals("Tartu"), true);
    }
}
