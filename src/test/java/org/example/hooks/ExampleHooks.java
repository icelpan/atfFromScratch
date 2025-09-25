package org.example.hooks;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ExampleHooks demonstrates how to use Cucumber lifecycle hooks within a
 * testing framework. Hooks can prepare test data, initialize drivers, or
 * perform cleanup after execution.
 */
public class ExampleHooks {

    private static final Logger logger = LoggerFactory.getLogger(ExampleHooks.class);

    @Before
    public void beforeScenario() {
        logger.info("Doing something before a scenario");
    }

    @AfterAll
    public static void inTheEnd() {
        logger.info("Run finished");
    }
}
