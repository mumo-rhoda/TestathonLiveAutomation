package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {
    private WebDriver driver;

    // Cart icon (top nav)
    @FindBy(xpath = "//*[@id='cart']")
    private WebElement cartIcon;

    // Favourites icon (top nav, example locator)
    @FindBy(xpath = "//*[@id='favourites']")
    private WebElement favouritesIcon;

    // Page title
    @FindBy(css = "h1.page-title")
    private WebElement pageTitle;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ✅ Verify Products page is loaded
    public boolean isPageLoaded() {
        try {
            return pageTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Get all product names
    public List<WebElement> getAllProducts() {
        return driver.findElements(By.cssSelector(".product-item"));
    }

    // ✅ Add product to cart by product name
    public void addProductToCart(String productName) {
        WebElement product = driver.findElement(By.xpath("//div[contains(@class,'product-item')][.//h2[text()='" + productName + "']]"));
        WebElement addToCartBtn = product.findElement(By.xpath(".//button[contains(@class,'add-to-cart')]"));
        addToCartBtn.click();
    }

    // ✅ Mark product as favourite by product name
    public void addProductToFavourites(String productName) {
        WebElement product = driver.findElement(By.xpath("//div[contains(@class,'product-item')][.//h2[text()='" + productName + "']]"));
        WebElement favBtn = product.findElement(By.xpath(".//button[contains(@class,'add-to-fav')]"));
        favBtn.click();
    }

    // ✅ Open Cart
    public void openCart() {
        cartIcon.click();
    }

    // ✅ Open Favourites
    public void openFavourites() {
        favouritesIcon.click();
    }
}
