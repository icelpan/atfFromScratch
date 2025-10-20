package org.example.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * ScenarioContext is a simple storage used to share data between Cucumber step definitions
 * during a single scenario execution.
 * <p>
 * This basic implementation is NOT thread-safe and assumes scenarios run sequentially in a single thread.
 * For parallel execution, adapt using ThreadLocal or per-scenario dependency injection.
 */
public class ScenarioContext {

    // Internal storage for scenario data.
    private final Map<String, Object> data = new HashMap<>();

    /**
     * Save a value into the scenario context under the given key.
     * @param key non-null, non-empty identifier.
     * @param value value to store (may be null).
     */
    public void save(String key, Object value) {
        validateKey(key);
        data.put(key, value);
    }

    /**
     * Retrieve a value by key. Returns null if not present.
     * Prefer using {@link #getOptional(String)} or {@link #get(String, Class)} for safety.
     * @param key the key to look up.
     * @return stored value or null.
     */
    public Object get(String key) {
        validateKey(key);
        return data.get(key);
    }

    /**
     * Retrieve a typed value by key, throwing if absent or of wrong type.
     * @param key the key to look up.
     * @param clazz expected class.
     * @return the value cast to clazz.
     */
    public <T> T get(String key, Class<T> clazz) {
        Object value = get(key);
        if (value == null) {
            throw new IllegalStateException("No value stored for key: " + key);
        }
        if (!clazz.isInstance(value)) {
            throw new ClassCastException("Value for key '" + key + "' is not of type " + clazz.getName() + ", actual: " + value.getClass().getName());
        }
        return clazz.cast(value);
    }

    /**
     * Retrieve an Optional value by key.
     * @param key lookup key.
     * @return Optional wrapping the value if present.
     */
    public Optional<Object> getOptional(String key) {
        return Optional.ofNullable(get(key));
    }

    /**
     * Remove all stored entries (typically called at end of a scenario).
     */
    public void clear() {
        data.clear();
    }

    /**
     * Check if a key exists in the context.
     * @param key lookup key.
     * @return true if present.
     */
    public boolean contains(String key) {
        return data.containsKey(key);
    }

    private void validateKey(String key) {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key must be non-null and non-blank");
        }
    }
}
