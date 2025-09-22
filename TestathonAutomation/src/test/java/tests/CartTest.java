package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductsPage;

public class CartTest extends BaseTest {
    private CartPage cartPage;
    private ProductsPage productsPage;

    @BeforeMethod
    public void navigateToCartWithItems() {
        loginWithValidCredentials();
        productsPage = new ProductsPage(driver);

        // Add products to cart
        productsPage.addMultipleProductsToCart(2);
        cartPage = productsPage.navigateToCart();
        cartPage.waitForPageLoad();
    }

    @Test(priority = 1, description = "Test cart page with items")
    public void testCartPageWithItems() {
        Assert.assertTrue(cartPage.isCartPageDisplayed(),
                "Cart page should load properly");
        Assert.assertTrue(cartPage.getCartItemsCount() > 0,
                "Cart should contain items");
        Assert.assertTrue(cartPage.getCartTotal() > 0,
                "Cart total should be positive");
    }

    @Test(priority = 2, description = "Test item removal from cart")
    public void testRemoveItemFromCart() {
        int initialItemCount = cartPage.getCartItemsCount();

        cartPage.removeItem(0);
        int finalItemCount = cartPage.getCartItemsCount();

        Assert.assertEquals(finalItemCount, initialItemCount - 1,
                "Cart should have one less item after removal");
    }

    @Test(priority = 3, description = "Test cart clearance")
    public void testClearCart() {
        cartPage.clearCart();

        Assert.assertTrue(cartPage.isCartEmpty(),
                "Cart should be empty after clearance");
        Assert.assertEquals(cartPage.getCartItemsCount(), 0,
                "Cart items count should be 0");
    }

    @Test(priority = 4, description = "Test continue shopping functionality")
    public void testContinueShopping() {
        ProductsPage returnedProductsPage = cartPage.continueShopping();

    }

    @Test(priority = 5, description = "Test proceed to checkout")
    public void testProceedToCheckout() {
        pages.CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.waitForPageLoad();

        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed(),
                "Should navigate to checkout page");
    }
}