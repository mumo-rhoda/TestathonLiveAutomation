package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;

public class EndToEndTest extends BaseTest {

    @Test(priority = 1, description = "Complete end-to-end user journey")
    public void testCompleteUserJourney() {
        // 1. Login
        driver.get(ConfigReader.getLoginUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should load");

        ProductsPage productsPage = loginPage.login(ConfigReader.getValidEmail(),
                ConfigReader.getValidPassword());


        // 2. Add products to cart
        productsPage.addMultipleProductsToCart(2);
        int cartCount = productsPage.getCartItemCount();
        Assert.assertEquals(cartCount, 2, "Should have 2 items in cart");

        // 3. Navigate to cart
        CartPage cartPage = productsPage.navigateToCart();
        cartPage.waitForPageLoad();

        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page should load");
        Assert.assertEquals(cartPage.getCartItemsCount(), 2, "Cart should contain 2 items");

        // 4. Proceed to checkout
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.waitForPageLoad();

        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed(), "Checkout page should load");

        // 5. Complete checkout
        checkoutPage.enterShippingInformation("John", "Doe", "john.doe@test.com",
                "123 Main St", "New York", "10001");
        checkoutPage.placeOrder();

        // Wait for order processing
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(checkoutPage.isOrderSuccessful(), "Order should be successful");

        // 6. Verify cart is cleared after successful order
        ProductsPage postCheckoutProductsPage = checkoutPage.continueShopping();


        Assert.assertEquals(postCheckoutProductsPage.getCartItemCount(), 0,
                "Cart should be cleared after successful checkout");
    }

    @Test(priority = 2, description = "Test cart clearance flow")
    public void testCartClearanceFlow() {
        // Login and add products
        loginWithValidCredentials();
        ProductsPage productsPage = new ProductsPage(driver);


        productsPage.addMultipleProductsToCart(3);
        Assert.assertTrue(productsPage.getCartItemCount() >= 3, "Should have items in cart");

        // Clear cart
        CartPage cartPage = productsPage.navigateToCart();
        cartPage.waitForPageLoad();
        cartPage.clearCart();

        Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty after clearance");

        // Verify cart remains empty
        ProductsPage postClearanceProductsPage = cartPage.continueShopping();
        Assert.assertEquals(postClearanceProductsPage.getCartItemCount(), 0,
                "Cart should remain empty after clearance");
    }
}