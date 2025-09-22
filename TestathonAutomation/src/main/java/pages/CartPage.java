package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CartPage extends BasePage {

    // Page Elements - Update these selectors based on actual website
    @FindBy(className = "cart-item")
    private List<WebElement> cartItems;

    @FindBy(className = "remove-item")
    private List<WebElement> removeButtons;

    @FindBy(xpath = "//button[contains(text(),'Checkout')]")
    private WebElement checkoutButton;

    @FindBy(xpath = "//button[contains(text(),'Continue Shopping')]")
    private WebElement continueShoppingButton;

    @FindBy(className = "empty-cart-message")
    private WebElement emptyCartMessage;

    @FindBy(className = "cart-total")
    private WebElement cartTotal;

    @FindBy(xpath = "//h1[contains(text(),'Cart')]")
    private WebElement pageTitle;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPageDisplayed() {
        return isDisplayed(pageTitle) || getCurrentUrl().contains("cart");
    }

    public int getCartItemsCount() {
        return cartItems.size();
    }

    public void removeItem(int index) {
        if (index < removeButtons.size()) {
            click(removeButtons.get(index));
            // Wait for item removal
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearCart() {
        for (int i = removeButtons.size() - 1; i >= 0; i--) {
            removeItem(i);
        }
    }

    public CheckoutPage proceedToCheckout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }

    public ProductsPage continueShopping() {
        click(continueShoppingButton);
        return new ProductsPage(driver);
    }

    public boolean isCartEmpty() {
        return isDisplayed(emptyCartMessage) || getCartItemsCount() == 0;
    }

    public double getCartTotal() {
        if (isDisplayed(cartTotal)) {
            String totalText = getText(cartTotal).replaceAll("[^0-9.]", "");
            return Double.parseDouble(totalText);
        }
        return 0.0;
    }

    public void waitForPageLoad() {
        waitForElementToBeVisible(pageTitle);
    }
}