package utility;

import java.util.HashMap;
import java.util.Map;

// A simple logger; helpful for debugging GUI
public class Logger {
    private static Map<String, String> log = new HashMap<>();
    
    // MODIFIES: this
    // EFFECTS: prints the key + message to console
    public static void log(String key, String message) {
        log.put(key, message);
        System.out.println(key + " : " + message);
    }
}