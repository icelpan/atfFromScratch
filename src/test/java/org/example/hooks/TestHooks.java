package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import org.example.browser.BrowserManager;
import org.example.context.ScenarioContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ExampleHooks demonstrates how to use Cucumber lifecycle hooks within a
 * testing framework. Hooks can prepare test data, initialize drivers, or
 * perform cleanup after execution.
 */
public class TestHooks {

    private static final Logger logger = LoggerFactory.getLogger(TestHooks.class);

    public static ScenarioContext scenarioContext;

    @Before
    public void beforeScenario() {
        logger.info("Initializing browser & scenario context");
        scenarioContext = new ScenarioContext();
        BrowserManager.init();
        BrowserManager.newContextAndPage();
        BrowserManager.navigateToBaseUrl();
    }

    @After
    public void afterScenario() {
        logger.info("Closing scenario context & page");
        if (scenarioContext != null) {
            scenarioContext.clear();
        }
        BrowserManager.closeContextOnly();
    }

    @AfterAll
    public static void inTheEnd() {
        logger.info("Run finished - closing browser resources & nullifying context");
        BrowserManager.close();
        scenarioContext = null;
    }
}
