import ilmaAPI.Filereader;
import ilmaAPI.Filewritter;
import ilmaAPI.JSONgetter;
import org.json.JSONException;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class MockTests {
    private Filereader reader;
    private Filewritter writter;

    @Before
    public void initialize() throws IOException {
        reader = mock(Filereader.class);
        writter = mock(Filewritter.class);
    }

    public MockTests() throws JSONException {
    }

    @Test
    public void mockFileWriting() throws IOException {
        initialize();
        writter.write("5");
        Mockito.verify(writter).write("5");
    }

    @Test
    public void mockFileReading() throws IOException {
        initialize();
        List<String > list = reader.readline();
        Mockito.verify(reader, times(1)).readline();
    }

    @Test
    public void mockJsonGetter() throws IOException {
        initialize();
        JSONgetter jsoNgetter = mock(JSONgetter.class);
        String name = jsoNgetter.getCityname();
        Mockito.verify(jsoNgetter, times(1)).getCityname();
    }
}
