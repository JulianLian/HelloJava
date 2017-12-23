package subway.test;

import org.junit.Before;
import org.junit.Test;
import subway.Subway;
import subway.SubwayLoader;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class SubwayLoaderTest {
    private SubwayLoader loader;

    @Before
    public void setUp() {
        loader = new SubwayLoader();
    }

    @Test
    public void testLoadFromFile() throws IOException {
        Subway objectVilleSubway = loader.loadFromFile(new File("HeadFirstOOAD\\src\\subway\\test\\ObjectvilleSubway.txt"));
        assertTrue(objectVilleSubway.hasStation("DRY Drive"));
        assertTrue(objectVilleSubway.hasStation("Weather-O-Rama, Inc."));
        assertTrue(objectVilleSubway.hasStation("Boards 'R' Us"));

        assertTrue(objectVilleSubway.hasConnection("DRY Drive", "Head First Theater", "Meyer Line"));
        assertTrue(objectVilleSubway.hasConnection("Weather-O-Rama, Inc.", "XHTML Expressway", "Wirfs-Brock Line"));
        assertTrue(objectVilleSubway.hasConnection("Head First Theater", "Infinite Circle", "Rumbaugh Line"));
    }

}