import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a sandbox to play around with Java core and unit tests.
 * It is an example, normally NOT included in End-2-End testing frameworks
 */
public class UnitTestSandbox {

    private final Logger log = LoggerFactory.getLogger(UnitTestSandbox.class);

    @Test
    public void exampleUnitTest() {
        log.info("Hello world");
    }
}
