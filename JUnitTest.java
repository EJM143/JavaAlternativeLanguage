import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitTest {

    // Test for parseLine
    @Test
    public void parseLineTest() {
        String line = "Samsung,Galaxy S21,2021,Released 2021,151.7 x 71.2 x 7.9 mm,169g,Dual SIM,AMOLED,6.2,1080x2400,Accelerometer,Android 11";
        Cell result = DataProcessing.parseLine(line);

        assertEquals("Samsung", result.getOem());
        assertEquals("Galaxy S21", result.getModel());
        assertEquals(2021, result.getLaunchAnnounced());
        assertEquals("Released 2021", result.getLaunchStatus());
        assertEquals("151.7 x 71.2 x 7.9 mm", result.getBodyDimensions());
        assertEquals(169f, result.getBodyWeight(), 0.001);
        assertEquals("Dual SIM", result.getBodySim());
        assertEquals("AMOLED", result.getDisplayType());
        assertEquals("1080x2400", result.getDisplayResolution());
        assertEquals("Accelerometer", result.getFeaturesSensors());
        assertEquals("Android 11", result.getPlatformOs());
    }

}
