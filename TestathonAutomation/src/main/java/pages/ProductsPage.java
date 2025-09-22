package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"__next\"]/div/div/main/div[2]")
    private List<WebElement> productCards;

    @FindBy(xpath = "//*[@id=\"1\"]/div[4]")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//*[@id=\"__next\"]/div/div/div[2]/span")
    private WebElement cartIcon;

    @FindBy(xpath = "//*[@id=\"__next\"]/div/div/div[2]/span/span")
    private WebElement cartCount;

    @FindBy(xpath = "//*[@id=\"11\"]/p")
    private List<WebElement> productNames;

    @FindBy(xpath = "//*[@id=\"12\"]/div[3]/div[1]/b")
    private List<WebElement> productPrices;



    public ProductsPage(WebDriver driver) {
        super(driver);
    }


    public int getProductCount() {
        return productCards.size();
    }

    public void addProductToCart(int index) {
        if (index < addToCartButtons.size()) {
            click(addToCartButtons.get(index));
            // Wait for cart to update
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addMultipleProductsToCart(int count) {
        for (int i = 0; i < Math.min(count, getProductCount()); i++) {
            addProductToCart(i);
        }
    }

    public CartPage navigateToCart() {
        click(cartIcon);
        return new CartPage(driver);
    }

    public int getCartItemCount() {
        try {
            if (isDisplayed(cartCount)) {
                String countText = getText(cartCount);
                return Integer.parseInt(countText);
            }
        } catch (NumberFormatException e) {
            // If cart is empty, count might not be displayed
        }
        return 0;
    }

    public String getProductName(int index) {
        if (index < productNames.size()) {
            return getText(productNames.get(index));
        }
        return "";
    }

    public double getProductPrice(int index) {
        if (index < productPrices.size()) {
            String priceText = getText(productPrices.get(index)).replaceAll("[^0-9.]", "");
            return Double.parseDouble(priceText);
        }
        return 0.0;
    }

}