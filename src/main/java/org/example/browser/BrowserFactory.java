package org.example.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

/**
 * BrowserFactory provides a minimal example of how to centralize browser
 * creation for UI tests using Microsoft Playwright. In a production framework
 * this class would manage configurations (browser type, headless mode, timeouts)
 * and lifecycle concerns.
 *
 * This project is intended for educational purposes, so the implementation is
 * intentionally simple and focuses on demonstrating structure.
 *
 * @see <a href="https://playwright.dev/java/docs/intro">Playwright Documentation</a>
 */
public class BrowserFactory {

    private static Playwright playwright;

    // Prevent instantiation
    private BrowserFactory() {
        playwright = Playwright.create();
    }

    /**
     * Create and launch a new Chromium browser instance.
     * @return a launched Playwright Browser
     */
    public static Browser createBrowser() {
        return playwright.chromium().launch();
    }

}
