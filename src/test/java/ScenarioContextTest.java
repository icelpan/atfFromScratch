import org.example.context.ScenarioContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

class ScenarioContextTest {

    @Test
    @DisplayName("save/get basic operations")
    void saveAndGet() {
        ScenarioContext ctx = new ScenarioContext();
        ctx.save("username", "alice");
        assertEquals("alice", ctx.get("username"));
        assertTrue(ctx.contains("username"));
    }

    @Test
    @DisplayName("typed get success and failure")
    void typedGet() {
        ScenarioContext ctx = new ScenarioContext();
        ctx.save("num", 42);
        Integer val = ctx.get("num", Integer.class);
        assertEquals(42, val);
        ctx.save("text", "value");
        assertThrows(ClassCastException.class, () -> ctx.get("text", Integer.class));
    }

    @Test
    @DisplayName("optional retrieval present and absent")
    void optionalRetrieval() {
        ScenarioContext ctx = new ScenarioContext();
        ctx.save("present", "data");
        Optional<Object> o1 = ctx.getOptional("present");
        Optional<Object> o2 = ctx.getOptional("missing");
        assertTrue(o1.isPresent());
        assertFalse(o2.isPresent());
    }

    @Test
    @DisplayName("clear removes data")
    void clearRemovesData() {
        ScenarioContext ctx = new ScenarioContext();
        ctx.save("k", "v");
        ctx.clear();
        assertFalse(ctx.contains("k"));
        assertNull(ctx.get("k"));
    }

    @Test
    @DisplayName("validateKey rejects null/blank")
    void invalidKeys() {
        ScenarioContext ctx = new ScenarioContext();
        assertThrows(IllegalArgumentException.class, () -> ctx.save(null, "x"));
        assertThrows(IllegalArgumentException.class, () -> ctx.save("", "x"));
        assertThrows(IllegalArgumentException.class, () -> ctx.get(" "));
    }

    @Test
    @DisplayName("typed get absent throws")
    void typedGetAbsent() {
        ScenarioContext ctx = new ScenarioContext();
        assertThrows(IllegalStateException.class, () -> ctx.get("missing", String.class));
    }
}

