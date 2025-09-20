package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    // Dropdown button (when clicked, expands list of usernames)
    @FindBy(xpath = "//*[@id='username']/div/div[1]")
    private WebElement usernameDropdown;

    // Password input
    @FindBy(xpath = "//*[@id='password']/div/div[1]/input")
    private WebElement passwordField;

    // Login button
    @FindBy(xpath= "//*[@id='login-btn']")
    private WebElement loginButton;

    // Error message
    @FindBy(xpath = "//*[@id='__next']/div[2]/div/form/div[2]/h3")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Custom dropdown selection
    public void selectUsername(String username) {
        usernameDropdown.click(); // expand dropdown
        WebElement option = driver.findElement(By.xpath("//div[@id='username']//div[text()='" + username + "']"));
        option.click();
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public String getErrorMessage() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
