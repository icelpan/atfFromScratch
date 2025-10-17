import org.example.utils.PropertyReader;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a sandbox to play around with Java core and unit tests.
 * It is an example, normally NOT included in End-2-End testing frameworks
 */
public class UnitSandboxTest {

    private final Logger log = LoggerFactory.getLogger(UnitSandboxTest.class);

    @Test
    public void exampleUnitTest() {
        log.info("Hello world");
    }

    @Test
    void verifyPropertiesLoaded() {
        PropertyReader propertyReader = PropertyReader.getInstance();

        assertEquals("https://wikipedia.org", propertyReader.getProperty("app.url"));
        assertEquals("chromium", propertyReader.getProperty("browser.type"));
        assertEquals(1366, propertyReader.getIntProperty("browser.viewport.size.width"));
        assertEquals(768, propertyReader.getIntProperty("browser.viewport.size.height"));
        assertEquals(2500, propertyReader.getIntProperty("browser.slow.mo"));
        assertFalse(propertyReader.getBooleanProperty("browser.headless"));

        assertEquals(10000, propertyReader.getIntProperty("timeout.default.ms"));
    }
}
