package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "loginCredentials")
    public static Object[][] getLoginCredentials() {
        return new Object[][] {
                // email, password, expectedResult, description
                {"", "", "failure", "Invalid credentials"},
                {"", "testingisfun99", "failure", "Invalid Username"},
                {"existing_orders_user", "", "failure", "Invalid Password"},
                {"locked_user","testingisfun99","failure","Your account has been locked."},
                {ConfigReader.getValidEmail(), ConfigReader.getValidPassword(), "success", "Valid credentials"}
        };
    }

    @DataProvider(name = "productSelection")
    public static Object[][] getProductSelection() {
        return new Object[][] {
                {1, "Single product"},
                {2, "Two products"},
                {3, "Three products"}
        };
    }

    @DataProvider(name = "checkoutData")
    public static Object[][] getCheckoutData() {
        return new Object[][] {
                {
                        ConfigReader.getProperty("checkout.firstname"),
                        ConfigReader.getProperty("checkout.lastname"),
                        ConfigReader.getProperty("checkout.email"),
                        ConfigReader.getProperty("checkout.address"),
                        ConfigReader.getProperty("checkout.city"),
                        ConfigReader.getProperty("checkout.state"),
                        ConfigReader.getProperty("checkout.zipcode")
                }
        };
    }
}