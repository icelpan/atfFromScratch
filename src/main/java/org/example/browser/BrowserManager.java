package org.example.browser;

import com.microsoft.playwright.*;
import org.example.utils.PropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BrowserManager centralizes Playwright lifecycle and configuration handling.
 * It reads settings from application.properties (loaded via {@link PropertyReader})
 * and exposes convenience methods for obtaining a configured {@link Page}.
 *
 * Usage pattern (single-threaded / educational skeleton):
 *   BrowserManager.init();
 *   BrowserManager.navigateToBaseUrl();
 *   Page page = BrowserManager.getPage();
 *   // ... test actions ...
 *   BrowserManager.close();
 *
 * This implementation is intentionally simple. For production use consider:
 *  - Separating per-scenario context vs. global objects.
 *  - Thread safety for parallel execution.
 *  - Conditional tracing / video / network logging.
 *  - Reusing Browser across contexts to improve performance.
 */
public final class BrowserManager {

    private static final Logger logger = LoggerFactory.getLogger(BrowserManager.class);
    private static final PropertyReader props = PropertyReader.getInstance();

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    private BrowserManager() { /* utility class */ }

    /**
     * Initialize Playwright and launch the Browser if not already done.
     * Safe to call multiple times; subsequent calls are ignored.
     */
    public static synchronized void init() {
        if (playwright != null) {
            return; // already initialized
        }
        playwright = Playwright.create();
        String browserType = readString("browser.type", props.getProperty("browser.type"));
        boolean headless = readBoolean("browser.headless", props.getBooleanProperty("browser.headless"));
        int slowMo = readInt("browser.slow.mo", props.getIntProperty("browser.slow.mo"));

        BrowserType type;
        switch (browserType.toLowerCase()) {
            case "firefox":
                type = playwright.firefox();
                break;
            case "webkit":
                type = playwright.webkit();
                break;
            case "chromium":
            default:
                if (!browserType.equalsIgnoreCase("chromium")) {
                    logger.warn("Unsupported browser.type '" + browserType + "'. Falling back to chromium.");
                }
                type = playwright.chromium();
        }

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setSlowMo(slowMo);

        browser = type.launch(launchOptions);
        logger.info("Launched browser: type={} headless={} slowMo={}", browserType, headless, slowMo);
        // Create initial context & page lazily only when requested.
    }

    /**
     * Obtain a Page, creating a fresh BrowserContext if necessary.
     * @return active Page instance.
     */
    public static synchronized Page getPage() {
        if (browser == null) {
            init();
        }
        if (context == null || page == null) {
            newContextAndPage();
        }
        return page;
    }

    /**
     * Create a new BrowserContext & Page using configured viewport and timeout.
     * Disposes any existing context first.
     */
    public static synchronized void newContextAndPage() {
        if (browser == null) {
            init();
        }
        closeContextOnly();
        int width = readInt("browser.viewport.size.width", 1280);
        int height = readInt("browser.viewport.size.height", 720);
        int defaultTimeout = readInt("timeout.default.ms", 30000);

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setViewportSize(width, height);

        context = browser.newContext(contextOptions);
        page = context.newPage();
        page.setDefaultTimeout(defaultTimeout);
        logger.info("Created new context & page: viewport={}x{} defaultTimeout={}ms", width, height, defaultTimeout);
    }

    /**
     * Navigate the current page to the configured base application URL.
     */
    public static void navigateToBaseUrl() {
        String baseUrl = readString("app.url", null);
        if (baseUrl == null) {
            logger.warn("No app.url configured; skipping navigation.");
            return;
        }
        getPage().navigate(baseUrl);
        logger.info("Navigated to base URL: {}", baseUrl);
    }

    /**
     * Close all Playwright resources (Page, Context, Browser, Playwright).
     * Safe to call multiple times.
     */
    public static synchronized void close() {
        closeContextOnly();
        if (browser != null) {
            try { browser.close(); } catch (Exception e) { logger.warn("Error closing browser: {}", e.getMessage()); }
            browser = null;
        }
        if (playwright != null) {
            try { playwright.close(); } catch (Exception e) { logger.warn("Error closing Playwright: {}", e.getMessage()); }
            playwright = null;
        }
        logger.info("BrowserManager resources closed.");
    }

    /**
     * Close only the current context & page (used when starting a fresh scenario).
     */
    public static synchronized void closeContextOnly() {
        if (page != null) {
            try { page.close(); } catch (Exception e) { logger.debug("Error closing page: {}", e.getMessage()); }
            page = null;
        }
        if (context != null) {
            try { context.close(); } catch (Exception e) { logger.debug("Error closing context: {}", e.getMessage()); }
            context = null;
        }
    }

    // ------------------------ Helper property readers ------------------------

    private static String readString(String key, String defaultValue) {
        try {
            return props.getProperty(key);
        } catch (Exception e) {
            if (defaultValue != null) {
                logger.debug("Using default for {}: {} (reason: {})", key, defaultValue, e.getMessage());
            } else {
                logger.debug("Property {} not found and no default provided.", key);
            }
            return defaultValue;
        }
    }

    private static int readInt(String key, int defaultValue) {
        try {
            return props.getIntProperty(key);
        } catch (Exception e) {
            logger.debug("Using default int for {}: {} (reason: {})", key, defaultValue, e.getMessage());
            return defaultValue;
        }
    }

    private static boolean readBoolean(String key, boolean defaultValue) {
        try {
            return props.getBooleanProperty(key);
        } catch (Exception e) {
            logger.debug("Using default boolean for {}: {} (reason: {})", key, defaultValue, e.getMessage());
            return defaultValue;
        }
    }
}

