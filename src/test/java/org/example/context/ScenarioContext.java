package org.example.context;

import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * ScenarioContext is a simple storage used to share data between Cucumber step definitions
 * during a single scenario execution.
 */
public class ScenarioContext {
    
    public void save(String key, Object data){
        throw new NotImplementedException("Method not implemented");
    }
    
    public void get(String key){
        throw new NotImplementedException("Method not implemented");
    }
    
}
