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
 * @deprecated Replaced by {@link BrowserManager} which adds configuration and lifecycle management.
 * @see <a href="https://playwright.dev/java/docs/intro">Playwright Documentation</a>
 */
@Deprecated
public class BrowserFactory {

    private static Playwright playwright;

    // Prevent instantiation
    private BrowserFactory() { }

    /**
     * Create and launch a new Chromium browser instance.
     * @return a launched Playwright Browser
     */
    public static synchronized Browser createBrowser() {
        if (playwright == null) {
            playwright = Playwright.create();
        }
        return playwright.chromium().launch();
    }

}
