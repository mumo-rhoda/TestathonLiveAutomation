package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;
import utils.TestDataProvider;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Test successful login with valid credentials")
    public void testSuccessfulLogin() {
        driver.get(ConfigReader.getLoginUrl());
        loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");

        ProductsPage productsPage = loginPage.login(ConfigReader.getValidEmail(),
                ConfigReader.getValidPassword());



        Assert.assertTrue(productsPage.getProductCount() > 0,
                "Products should be available on products page");
    }

    @Test(priority = 2, dataProvider = "loginCredentials", dataProviderClass = TestDataProvider.class,
            description = "Test login with various credentials")
    public void testLoginWithVariousCredentials(String email, String password,
                                                String expectedResult, String description) {
        System.out.println("Testing: " + description);

        driver.get(ConfigReader.getLoginUrl());
        loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();


    }

    @Test(priority = 3, description = "Test page navigation after login")
    public void testNavigationAfterLogin() {
        loginWithValidCredentials();

        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertTrue(driver.getCurrentUrl().contains("products"),
                "URL should contain products reference");
    }
}