package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import utils.BrowserStackSetup;
import utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod
    public void setUp() throws Exception {
        // Use BrowserStack for cloud testing or local driver for development
        try {
            driver = BrowserStackSetup.initializeDriver();
        } catch (Exception e) {
            // Fallback to local driver if BrowserStack fails
            driver = BrowserStackSetup.initializeLocalDriver();
        }

        driver.manage().window().maximize();
    }

    protected void loginWithValidCredentials() {
        driver.get(ConfigReader.getLoginUrl());
        loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();
        loginPage.login(ConfigReader.getValidEmail(), ConfigReader.getValidPassword());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            driver.quit();
        }
    }
}