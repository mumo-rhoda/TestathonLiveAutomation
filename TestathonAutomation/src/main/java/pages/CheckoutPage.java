package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    // Page Elements - Update these selectors based on actual website
    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "address")
    private WebElement addressField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "zipCode")
    private WebElement zipCodeField;

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrderButton;

    @FindBy(className = "order-success")
    private WebElement orderSuccessMessage;

    @FindBy(className = "order-error")
    private WebElement orderErrorMessage;

    @FindBy(xpath = "//a[contains(text(),'Continue Shopping')]")
    private WebElement continueShoppingLink;

    @FindBy(xpath = "//h1[contains(text(),'Checkout')]")
    private WebElement pageTitle;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutPageDisplayed() {
        return isDisplayed(pageTitle) || getCurrentUrl().contains("checkout");
    }

    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void enterAddress(String address) {
        type(addressField, address);
    }

    public void enterCity(String city) {
        type(cityField, city);
    }

    public void enterZipCode(String zipCode) {
        type(zipCodeField, zipCode);
    }

    public void enterShippingInformation(String firstName, String lastName, String email,
                                         String address, String city, String zipCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterAddress(address);
        enterCity(city);
        enterZipCode(zipCode);
    }

    public void placeOrder() {
        click(placeOrderButton);
    }

    public ProductsPage continueShopping() {
        click(continueShoppingLink);
        return new ProductsPage(driver);
    }

    public boolean isOrderSuccessful() {
        return isDisplayed(orderSuccessMessage);
    }

    public boolean isOrderError() {
        return isDisplayed(orderErrorMessage);
    }

    public String getOrderStatusMessage() {
        if (isOrderSuccessful()) {
            return getText(orderSuccessMessage);
        } else if (isOrderError()) {
            return getText(orderErrorMessage);
        }
        return "";
    }

    public void completeCheckout(String firstName, String lastName, String email,
                                 String address, String city, String zipCode) {
        enterShippingInformation(firstName, lastName, email, address, city, zipCode);
        placeOrder();
    }

    public void waitForPageLoad() {
        waitForElementToBeVisible(pageTitle);
    }
}