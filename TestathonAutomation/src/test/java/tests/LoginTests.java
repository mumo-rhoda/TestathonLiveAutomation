package tests;

import base.BaseTest;
import utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;



public class LoginTests extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
    public void testLogin(String username, String password, boolean expectedSuccess) {
        driver.get("https://testathon.live/");

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.selectUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        if (expectedSuccess) {
            Assert.assertTrue(productsPage.isPageLoaded(),
                    "User should be logged in with: " + username);
            Assert.assertEquals(loginPage.getErrorMessage(), "",
                    "No error message should appear for valid login.");
        } else {
            Assert.assertFalse(productsPage.isPageLoaded(),
                    "User should NOT be logged in with: " + username);
            Assert.assertTrue(loginPage.getErrorMessage().length() > 0,
                    "Error message should appear for invalid login.");
        }
    }
}
