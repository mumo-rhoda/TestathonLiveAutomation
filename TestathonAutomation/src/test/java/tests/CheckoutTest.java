package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.ProductsPage;
import utils.TestDataProvider;

public class CheckoutTest extends BaseTest {
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void navigateToCheckout() {
        loginWithValidCredentials();
        ProductsPage productsPage = new ProductsPage(driver);


        // Add product and navigate to checkout
        productsPage.addProductToCart(0);
        checkoutPage = productsPage.navigateToCart().proceedToCheckout();
        checkoutPage.waitForPageLoad();
    }

    @Test(priority = 1, description = "Test checkout page loading")
    public void testCheckoutPageLoading() {
        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed(),
                "Checkout page should load properly");
    }

    @Test(priority = 2, dataProvider = "checkoutData", dataProviderClass = TestDataProvider.class,
            description = "Test checkout process with valid data")
    public void testCompleteCheckoutProcess(String firstName, String lastName, String email,
                                            String address, String city, String state, String zipCode) {
        checkoutPage.enterShippingInformation(firstName, lastName, email, address, city, zipCode);
        checkoutPage.placeOrder();

        // Wait for order processing
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(checkoutPage.isOrderSuccessful(),
                "Order should be placed successfully");

        String statusMessage = checkoutPage.getOrderStatusMessage().toLowerCase();
        Assert.assertTrue(statusMessage.contains("success") ||
                        statusMessage.contains("thank") ||
                        statusMessage.contains("complete"),
                "Status message should indicate success");
    }

    @Test(priority = 3, description = "Test back to products navigation after checkout")
    public void testBackToProductsAfterCheckout() {
        // Complete checkout first
        checkoutPage.enterShippingInformation("John", "Doe", "john@test.com",
                "123 St", "City", "12345");
        checkoutPage.placeOrder();

        // Navigate back to products
        ProductsPage productsPage = checkoutPage.continueShopping();
    }
}