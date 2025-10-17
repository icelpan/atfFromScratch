package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.browser.BrowserManager;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.microsoft.playwright.Page;

/**
 * ExampleSteps provides sample Cucumber step definitions used in the test
 * suite to demonstrate a simple Given/When/Then flow (Wikipedia search in
 * this skeleton project).
 */
public class ExampleSteps {
    
    private final Logger logger = LoggerFactory.getLogger(ExampleSteps.class);

    @Given("user is on the Wikipedia home page")
    public void userIsOnTheWikipediaHomePage() {
        // Navigation is handled in the @Before hook, but we ensure page exists.
        Page page = BrowserManager.getPage();
        String url = page.url();
        logger.info("Current page URL: {}", url);
        Assertions.assertTrue(url.contains("wikipedia.org"), "Expected to be on Wikipedia but was: " + url);
    }

    @When("user searches for {string}")
    public void userSearchesFor(String term) {
        Page page = BrowserManager.getPage();
        logger.info("Searching for term: {}", term);
        // Wikipedia search input has id 'searchInput'
        page.fill("#searchInput", term);
        page.press("#searchInput", "Enter");
    }

    @Then("the article for {string} should be displayed")
    public void theArticleForShouldBeDisplayed(String term) {
        Page page = BrowserManager.getPage();
        page.waitForSelector("#firstHeading");
        String heading = page.textContent("#firstHeading");
        logger.info("Found heading: {}", heading);
        Assertions.assertTrue(heading != null && heading.trim().toLowerCase().contains(term.toLowerCase()),
                "Expected heading to contain term. Heading=" + heading + " term=" + term);
    }
}
