package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            InputStream inputStream = ConfigReader.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            properties.load(inputStream);

            // Load test data properties
            InputStream testDataStream = ConfigReader.class.getClassLoader()
                    .getResourceAsStream("testdata.properties");
            Properties testDataProps = new Properties();
            testDataProps.load(testDataStream);
            properties.putAll(testDataProps);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static String getLoginUrl() {
        return getProperty("login.url");
    }

    public static String getProductsUrl() {
        return getProperty("products.url");
    }

    public static String getBrowserStackUsername() {
        return getProperty("browserstack.username");
    }

    public static String getBrowserStackAccessKey() {
        return getProperty("browserstack.accesskey");
    }

    public static String getValidEmail() {
        return getProperty("valid.email");
    }

    public static String getValidPassword() {
        return getProperty("valid.password");
    }
}