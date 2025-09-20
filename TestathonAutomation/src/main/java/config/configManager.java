package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configManager {
    private static Properties properties = new Properties();
    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
