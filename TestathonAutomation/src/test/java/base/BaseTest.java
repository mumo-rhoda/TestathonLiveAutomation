package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    // Use your BrowserStack credentials
    public static final String USERNAME = System.getProperty("bs.user", "rhodamumo_zjwFnd");
    public static final String ACCESS_KEY = System.getProperty("bs.key", "YP5pnWwzwzNgnDN4qZRG");
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub.browserstack.com/wd/hub";

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        // Example capabilities (you can extend from browserstack.yml later)
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("browserVersion", "latest");
        caps.setCapability("os", "Windows");
        caps.setCapability("osVersion", "10");

        // Test Management build & project names
        caps.setCapability("projectName", "Testathon Project");
        caps.setCapability("buildName", "Testathon-Build-1");
        caps.setCapability("sessionName", "Login Test");

        driver = new RemoteWebDriver(new URL(URL), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(config.configManager.get("app.url"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
