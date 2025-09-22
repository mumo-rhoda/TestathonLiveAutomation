package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;


public class BrowserStackSetup {

    public static WebDriver initializeDriver() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set BrowserStack capabilities
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "latest");
        capabilities.setCapability("os", "Windows");
        capabilities.setCapability("osVersion", "11");

        // BrowserStack specific capabilities
        capabilities.setCapability("resolution", "1920x1080");
        capabilities.setCapability("browserstack.local", "false");
        capabilities.setCapability("browserstack.debug", "true");
        capabilities.setCapability("browserstack.console", "info");
        capabilities.setCapability("browserstack.networkLogs", "true");
        capabilities.setCapability("browserstack.timezone", "UTC");

        String browserstackUrl = String.format("https://%s:%s@hub.browserstack.com/wd/hub",
                ConfigReader.getBrowserStackUsername(),
                ConfigReader.getBrowserStackAccessKey());

        return new RemoteWebDriver(new URL(browserstackUrl), capabilities);
    }

    public static WebDriver initializeLocalDriver() throws Exception {

        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        return new org.openqa.selenium.chrome.ChromeDriver(options);
    }
}