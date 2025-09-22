package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // Page Elements - Update these selectors based on actual website
    @FindBy(xpath = "//*[@id=\"username\"]/div/div[1]")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id=\"password\"]/div/div[1]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"login-btn\"]")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id=\"__next\"]/div[2]/div/form/div[2]/h3")
    private WebElement errorMessage;


    @FindBy(xpath = "//*[@id=\"__next\"]/div/div/div[1]/div/div/div[1]/a/svg")
    private WebElement pageTitle;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickSignIn() {
        click(signInButton);
    }

    public ProductsPage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
        return new ProductsPage(driver);
    }

    public boolean isLoginPageDisplayed() {
        return isDisplayed(pageTitle) || getCurrentUrl().contains("?signin=true");
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }


    public String getErrorMessage() {
        if (isErrorMessageDisplayed()) {
            return getText(errorMessage);
        }
        return "";
    }

    public void waitForPageLoad() {
        waitForElementToBeVisible(emailField);
    }
}