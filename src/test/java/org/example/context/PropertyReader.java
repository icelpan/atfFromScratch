package org.example.context;

import org.apache.commons.lang3.NotImplementedException;

/**
 * PropertyReader is a simple facade intended to provide read-only access to
 * configuration values for tests. In a typical setup it would load key/value
 * pairs from a properties source available on the classpath (for example,
 * src/test/resources/application.properties) and expose them via helper
 * methods such as getProperty(String).
 *
 * Note: The implementation is intentionally left out in this skeleton project
 * and should be completed to fit the needs of your test framework (e.g.,
 * caching values, supporting multiple sources, or environment overrides).
 */
public class PropertyReader {
    
    public String getProperty(String name){
        throw new NotImplementedException("Method not implemented");
    }
}
