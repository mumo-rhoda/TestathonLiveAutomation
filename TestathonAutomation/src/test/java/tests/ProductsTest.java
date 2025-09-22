package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProductsPage;
import utils.TestDataProvider;

public class ProductsTest extends BaseTest {
    private ProductsPage productsPage;

    @BeforeMethod
    public void navigateToProductsPage() {
        loginWithValidCredentials();
        productsPage = new ProductsPage(driver);

    }

    @Test(priority = 1, description = "Test products page loading")
    public void testProductsPageLoading() {

        Assert.assertTrue(productsPage.getProductCount() > 0,
                "Should display at least one product");
    }

    @Test(priority = 2, dataProvider = "productSelection", dataProviderClass = TestDataProvider.class,
            description = "Test adding products to cart")
    public void testAddProductsToCart(int productCount, String description) {
        System.out.println("Testing: " + description);

        int initialCartCount = productsPage.getCartItemCount();
        productsPage.addMultipleProductsToCart(productCount);
        int finalCartCount = productsPage.getCartItemCount();

        Assert.assertEquals(finalCartCount, initialCartCount + productCount,
                "Cart count should increase by " + productCount);
    }

    @Test(priority = 3, description = "Test product information display")
    public void testProductInformation() {
        int productCount = Math.min(productsPage.getProductCount(), 3);

        for (int i = 0; i < productCount; i++) {
            String productName = productsPage.getProductName(i);
            double productPrice = productsPage.getProductPrice(i);

            Assert.assertFalse(productName.isEmpty(),
                    "Product name should not be empty for product " + (i + 1));
            Assert.assertTrue(productPrice > 0,
                    "Product price should be positive for product " + (i + 1));
        }
    }

    @Test(priority = 4, description = "Test navigation to cart from products page")
    public void testNavigationToCart() {
        // Add a product first
        productsPage.addProductToCart(0);

        pages.CartPage cartPage = productsPage.navigateToCart();
        cartPage.waitForPageLoad();

        Assert.assertTrue(cartPage.isCartPageDisplayed(),
                "Should navigate to cart page successfully");
        Assert.assertTrue(cartPage.getCartItemsCount() > 0,
                "Cart should contain the added product");
    }
}